package fr.gamedev.service;

import fr.gamedev.data.ScoringRule;
import fr.gamedev.data.UserAnswer;
import fr.gamedev.dto.ScoringRuleDto;
import fr.gamedev.dto.UserAnswerDTO;
import fr.gamedev.repository.ScoringRuleRepository;
import fr.gamedev.repository.UserAnswerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

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
//        TODO implement me when Response micro service is done
        return true;
    }

    public ScoringRule upsetScoringRule(ScoringRuleDto scoringRuleDto) {
        ScoringRule scoringRule = scoringRuleRepository.findById(scoringRuleDto.questionId())
                .orElse(new ScoringRule());

        scoringRule.setNbPoints(scoringRuleDto.nbPoint());

        return scoringRule;
    }
}
