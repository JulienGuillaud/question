package fr.gamedev;

import fr.gamedev.data.Question;
import fr.gamedev.dto.NextQuestionDTO;
import fr.gamedev.dto.QuestionDTO;
import fr.gamedev.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Swan
 *
 */
@RestController
public class QuestionController {

    /**.
     * questionService
     */
    @Autowired
    private QuestionService questionService;

    /**
     * @param nextQuestionDTO
     * @return Response
     */
    @PostMapping("/question/next")
    public ResponseEntity nextQuestion(@RequestBody NextQuestionDTO nextQuestionDTO) {
        Question question = questionService.nextQuestion(nextQuestionDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There are no questions matching the corresponding tags"));

        return ResponseEntity
                .accepted()
                .body(question);
    }

    @PostMapping("/question")
    public ResponseEntity addQuestion(@RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.accepted()
                .body(questionService.addQuestion(questionDTO));
    }

    @GetMapping("/question/{id}")
    public ResponseEntity getQuestion(@PathVariable String id) {
       return questionService.getQuestion(id)
               .map(ResponseEntity::ok)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));
    }

}
