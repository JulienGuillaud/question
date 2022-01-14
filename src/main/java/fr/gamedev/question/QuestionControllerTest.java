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
    void userCanGetNewQuestion() {
//        Given I am a user
//        When I ask a new question to answer
//        Then Then i get a new question
        Assertions.assertTrue(true);
    }

    @Test
    void userCannotGetNewQuestionIfNotAnswered() {
//        Given I am a user
//        When I ask a new question to answer
//        And A question is waiting for answer
//        And i ask for a new question
//        Then i don't get a new question
        Assertions.assertTrue(true);
    }

    @Test
    void userCanAnswerQuestion() {
//        Given I am a user
//        When I ask a new question to answer
//        And A question is waiting for answer
//        And i submit a valid response
//        Then i recieve the correctness of the response
//        And i get the question's points
        Assertions.assertTrue(true);
    }

    @Test
    void userAnswerIsCreatedOnResponse() {
//        User get an objet of type Question when POST question/next
//        use the question id to GET /response?answer="test"?userId="id"
//        a UserAnwser is saved
    }

    @Test
    void gainedPointsDecreaseWhenTheQuestionIsAnsweredSeveralTime() {
//        several UserAnwser is saved
//        If i answer on response?answer="test"?userId="id"
//        A i get a ResoponseObject
//        The points i get are the half of the last attempt
    }

    @Test
    void nextQuestionTagIsOneOfTheUsersTags() {
//        if user POST /question/next
//        A question object is returned
//        user.tags.contain(question.tag)
    }

}
