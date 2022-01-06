package fr.gamedev.question;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.Tag;
import fr.gamedev.question.data.User;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.UserRepository;

/**
 * @author Swan
 *
 */
@RestController
public class QuestionController {

    //TODO grp2 by DJE : JavaDoc : La première ligne de la JavaDoc est la description. UNE phrase est attendue, d'ou la remarque de PMD qui demande un point à la fin de cette phrase.
    //TODO grp2 by DJE : JavaDoc : Il est possible d'écrire la JavaDoc sur une seul ligne lorsqu'il n'y a pas de paramètre JavaDoc (@xxxx).
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

    //TODO grp2 by DJE : JavaDoc : Il manque la Description
    //TODO grp2 by DJE : JavaDoc : les paramètres ne sont pas documentées.
    /**
     * @param userId
     * @return Response
     */
    //TODO grp2 by DJE : REST : devrait renvoyer du "HAL" (paramètre produces = "application/hal+json")
    @GetMapping("/question/next")
    public ResponseEntity answer(@RequestParam final long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User does not exist");
        }
        Set<Tag> tags = user.get().getTags();
        if (tags.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "User has no tags");
        }
        Optional<Question> randomQuestion = questionRepository.getRandomQuestion(tags);

        if (randomQuestion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "There are no questions matching the corresponding tags");
        }

        return ResponseEntity.accepted().body(randomQuestion);
    }

}
