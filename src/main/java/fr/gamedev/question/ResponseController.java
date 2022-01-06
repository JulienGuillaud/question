package fr.gamedev.question;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.UserAnswerRepository;
import fr.gamedev.question.repository.UserRepository;

/**
 * @author djer1
 *
 */
@RestController
public class ResponseController {

    //TODO grp2 by DJE : JavaDoc : La première ligne de la JavaDoc est la description. UNE phrase est attendue, d'ou la remarque de PMD qui demande un point à la fin de cette phrase.
    //TODO grp2 by DJE : JavaDoc : Il est possible d'écrire la JavaDoc sur une seul ligne lorsqu'il n'y a pas de paramètre JavaDoc (@xxxx).
    /** userRepository. */
    @Autowired
    private UserRepository userRepository;
    /** questionRepository. */
    @Autowired
    private QuestionRepository questionRepository;
    /** userAnwserRepository. */
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    //TODO grp2 by DJE : JavaDoc : Il manque la Description
    //TODO grp2 by DJE : JavaDoc : les paramètres ne sont pas documentées.
    /**
     * @param questionId
     * @param answer
     * @param userId
     * @return String
     */
    //TODO grp2 by DJE : REST : devrait renvoyer du "HAL" (paramètre produces = "application/hal+json")
    @GetMapping("/response")
    public ResponseEntity answer(@RequestParam final long questionId, @RequestParam final String answer,
            @RequestParam final long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Question> question = questionRepository.findById(questionId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User does not exist");
        }
        if (question.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Question does not exist");
        }
        //TODO grp2 by DJE : Besoin : il n'y a pas de controle de réponse multiple à la même question. Un Utilisateur ne peut répondre qu'a unequestion qui lui à été posée.
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
