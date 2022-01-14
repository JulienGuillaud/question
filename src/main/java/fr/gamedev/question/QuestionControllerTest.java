package fr.gamedev.question;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    /**Permet de simuler le client Http sans faire de vrai appelle http.
     * MockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    /** Permet de transfomer le json en une Objet Entity.
     * ObjectMapper
     */
    @Autowired
    private ObjectMapper mapper;

    @Test
    void useCanGetNewQuestion() {
//        Given I am a user
//        When I ask a new question to answer
//        Then Then i get a new question
        Assertions.assertTrue(true);
    }

    @Test
    void useCannotGetNewQuestionIfNotAnswered() {
//        Given I am a user
//        When I ask a new question to answer
//        And A question is waiting for answer
//        And i ask for a new question
//        Then i don't get a new question
        Assertions.assertTrue(true);
    }

    @Test
    void useCanAnswerQuestion() {
//        Given I am a user
//        When I ask a new question to answer
//        And A question is waiting for answer
//        And i submit a valid response
//        Then i recieve the correctness of the response
//        And i get the question's points
        Assertions.assertTrue(true);
    }

}
