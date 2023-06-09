package fr.gamedev.service;

import fr.gamedev.domain.ScoringRule;
import fr.gamedev.domain.UserAnswer;
import fr.gamedev.dto.PendingUserAnswerDTO;
import fr.gamedev.dto.ScoringRuleDto;
import fr.gamedev.dto.UserAnswerDTO;
import fr.gamedev.repository.ScoringRuleRepository;
import fr.gamedev.repository.UserAnswerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;

public class ScoringService {

    /**
     * . userAnwserRepository
     */
    private final UserAnswerRepository userAnswerRepository;

    /**
     * . scroginRuleRepository
     */
    private final ScoringRuleRepository scoringRuleRepository;

    public ScoringService(UserAnswerRepository userAnswerRepository, ScoringRuleRepository scoringRuleRepository) {
        this.userAnswerRepository = userAnswerRepository;
        this.scoringRuleRepository = scoringRuleRepository;
    }

    public UserAnswer submit_response(UserAnswerDTO userAnswerDTO) {
        long questionId = userAnswerDTO.questionId();
        long userId = userAnswerDTO.userId();

        UserAnswer waitingForAnswer = this.userAnswerRepository.findFirstUserAnswerByUserIdAndCorrectIsNull(userId)
                .filter(userAnswer -> userAnswer.getQuestionId() == questionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There are no waiting for response question matching the provided question"));

        boolean isCorrect = isResponseCorrect(questionId, userAnswerDTO.response());

        waitingForAnswer.setDate(LocalDateTime.now());
        waitingForAnswer.setResponse(userAnswerDTO.response());
        waitingForAnswer.setCorrect(isCorrect);
        waitingForAnswer.setNbPoints(isCorrect ? getEarnedPoint(userId, questionId) : 0);

        return userAnswerRepository.save(waitingForAnswer);
    }

    private int getEarnedPoint(long userId, long questionId) {
        Integer scoringRulePoint = scoringRuleRepository.findById(questionId)
                .map(ScoringRule::getNbPoints)
                .orElse(0);

        return this.userAnswerRepository
                .findFirstUserAnswerByUserIdAndQuestionIdAndAndCorrectIsTrueOrderByDateDesc(userId, questionId)
                .map(this::getPoints)
                .orElse(scoringRulePoint);
    }

    private int getPoints(UserAnswer userAnswer) {
        return (int) Math.floor(userAnswer.getNbPoints() / 2.0);
    }

    private boolean isResponseCorrect(long questionId, String userAnswer) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:8082")
                .path("/question/" + questionId)
                .queryParam("response", userAnswer)
                .build()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RestClientException(e.getMessage());
        }

        return Boolean.parseBoolean(httpResponse.body());
    }

    public ScoringRule upsetScoringRule(ScoringRuleDto scoringRuleDto) {
        ScoringRule scoringRule = scoringRuleRepository.findById(scoringRuleDto.questionId())
                .orElse(new ScoringRule());

        scoringRule.setNbPoints(scoringRuleDto.nbPoint());

        return scoringRule;
    }

    public UserAnswer pendingUserAnswer(PendingUserAnswerDTO pendingUserAnswerDTO) {
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserId(pendingUserAnswerDTO.userId());
        userAnswer.setQuestionId(pendingUserAnswerDTO.questionId());
        userAnswerRepository.save(userAnswer);

        return userAnswer;
    }

    public Optional<Long> hasPendingUserAnswer(long userId) {
        return userAnswerRepository.findFirstUserAnswerByUserIdAndCorrectIsNull(userId)
                .map(UserAnswer::getQuestionId);
    }
}
