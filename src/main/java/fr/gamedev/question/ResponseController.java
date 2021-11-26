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

import java.util.Optional;

/**
 * @author djer1
 *
 */
@RestController
public class ResponseController {

  /**.
   * userRepository
   */
  @Autowired
  private UserRepository userRepository;
  /**.
   * questionRepository
   */
  @Autowired
  private QuestionRepository questionRepository;
  /**.
   * userAnwserRepository
   */
  @Autowired
  private UserAnswerRepository userAnswerRepository;

  /**
   * @param questionId
   * @param answer
   * @param userId
   * @return String
   */
  @GetMapping("/response")
  public ResponseEntity answer(
      @RequestParam final long questionId,
      @RequestParam final String answer,
      @RequestParam final long userId) {
    Optional<User> user = userRepository.findById(userId);
    Optional<Question> question = questionRepository.findById(questionId);
    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User does not exist");
    }
    if (question.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Question does not exist");
    }
    UserAnswer userAnswer = new UserAnswer();
    userAnswer.setUser(user.get());
    userAnswer.setQuestion(question.get());
    userAnswer.setText(answer);

    if (answer.equals(question.get().getAnswer())) {
      userAnswer.setCorrect(true);
    } else {
      userAnswer.setCorrect(false);
    }
    userAnswerRepository.save(userAnswer);

    return ResponseEntity.accepted().body(userAnswer);
  }

}
