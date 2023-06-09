package fr.gamedev.service;

import com.google.gson.Gson;
import fr.gamedev.domain.Question;
import fr.gamedev.domain.Skill;
import fr.gamedev.dto.NextQuestionDTO;
import fr.gamedev.dto.PendingUserAnswerDTO;
import fr.gamedev.dto.QuestionDTO;
import fr.gamedev.repository.QuestionRepository;
import fr.gamedev.repository.SkillRepository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionService {

    /**
     * . questionRepository
     */
    private final QuestionRepository questionRepository;

    /**
     * . tagRepository
     */
    private final SkillRepository skillRepository;


    public QuestionService(QuestionRepository questionRepository, SkillRepository skillRepository) {
        this.questionRepository = questionRepository;
        this.skillRepository = skillRepository;
    }

    public Optional<Question> nextQuestion(NextQuestionDTO nextQuestionDTO) {
        Question question = getPendingUseranswer(nextQuestionDTO)
                .orElse(questionRepository
                        .getRandomQuestion(collectSkills(nextQuestionDTO))
                        .map(question1 -> createPendingUserAnswer(question1, nextQuestionDTO))
                        .orElse(null));

        return Optional.ofNullable(question);
    }

    private Question createPendingUserAnswer(Question question1, NextQuestionDTO nextQuestionDTO) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:8081")
                .path("/pending-user-answer")
                .build()
                .toUri();

        Gson gson = new Gson();
        PendingUserAnswerDTO pendingUserAnswerDTO = new PendingUserAnswerDTO(question1.getId(), nextQuestionDTO.userId());
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(gson.toJson(pendingUserAnswerDTO));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(bodyPublisher)
                .GET()
                .build();

        HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString());

        return question1;
    }


    private Set<Skill> collectSkills(NextQuestionDTO nextQuestionDTO) {
        return Arrays.stream(nextQuestionDTO.skillIds())
                .mapToObj(skillRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    private Optional<Question> getPendingUseranswer(NextQuestionDTO nextQuestionDTO) {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:8081")
                .path("/pending-user-answer")
                .queryParam("userId", nextQuestionDTO.userId())
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

        return Optional.ofNullable(httpResponse)
                .filter(response -> response.statusCode() == 200)
                .map(HttpResponse::body)
                .map(Long::parseLong)
                .flatMap(questionRepository::findById);
    }

    public Question addQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setContent(questionDTO.content());
        question.setAnswer(questionDTO.answer());

        questionRepository.save(question);

        return question;
    }

    public Optional<Question> getQuestion(String id) {
        return questionRepository.findById(Long.parseLong(id));
    }

    public boolean isResponseCorrect(String id, String response) {
        return questionRepository.findById(Long.parseLong(id))
                .map(question -> question.getAnswer().equals(response))
                .orElse(false);
    }

}
