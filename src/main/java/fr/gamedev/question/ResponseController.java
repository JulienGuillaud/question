package fr.gamedev.question;

import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.UserAnswerRepository;
import fr.gamedev.question.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author djer1
 *
 */
@RestController
public class ResponseController {

    /**
     * . userRepository
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * . questionRepository
     */
    @Autowired
    private QuestionRepository questionRepository;
    /**
     * . userAnwserRepository
     */
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    /**
     * @param answer
     * @param userId
     * @return String
     */
    @GetMapping("/response")
    public ResponseEntity answer(@RequestParam final String answer,
                                 @RequestParam final long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User does not exist");
        }
        Optional<UserAnswer> waitingForAnswer = this.userAnswerRepository.findFirstUserAnswerByUserAndCorrectIsNull(user.get());
        if (waitingForAnswer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There are no waiting for response question");
        }

        Question question = waitingForAnswer.get().getQuestion();

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(user.get());
        userAnswer.setQuestion(question);
        userAnswer.setText(answer);
        userAnswer.setDate(LocalDateTime.now());

        if (answer.equals(question.getAnswer())) {
            userAnswer.setCorrect(true);
            userAnswer.setPoints(getEarnedPoint(user.get(), question));
        } else {
            userAnswer.setCorrect(false);
        }
        userAnswerRepository.save(userAnswer);

        return ResponseEntity.accepted().body(userAnswer);
    }

    private int getEarnedPoint(final User user, final Question question) {
        Optional<UserAnswer> lastUserAnswer = this.userAnswerRepository.findFirstUserAnswerByUserAndQuestionAndAndCorrectIsTrueOrderByDateDesc(user, question);

        int point = question.getPoint();
        if (lastUserAnswer.isPresent()) {
            point = (int) Math.floor(lastUserAnswer.get().getPoints() / 2.0);
        }
        return point;
    }

}
