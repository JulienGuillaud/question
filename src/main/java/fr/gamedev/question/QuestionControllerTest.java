package fr.gamedev.question;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.gamedev.question.data.*;
import fr.gamedev.question.repository.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    /**Permet de simuler le client Http sans faire de vrai appel http.
     * MockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    /** Handle user saving.
     * Userepository
     */
    @Autowired
    private UserRepository userRepository;

    /** Handle category saving.
     * CategoryRepository
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /** Handle tag saving.
     * tagRepository
     */
    @Autowired
    private TagRepository tagRepository;

    /** Handle userAnswer saving.
     * UserAnswerRepository
     */
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    /** Handle question saving.
     * QuestionRepository
     */
    @Autowired
    private QuestionRepository questionRepository;

    /** Permet de transfomer le json en une Objet Entity.
     * ObjectMapper
     */
    @Autowired
    private ObjectMapper mapper;

    @Before("")
    public void setUp() throws Exception {
        Category category1 = new Category();
        category1.setName("language");
        categoryRepository.save(category1);
        Tag tag1 = new Tag();
        tag1.setName("java");
        tag1.setCategory(category1);
        tagRepository.save(tag1);
        Tag tag2 = new Tag();
        tag2.setName("python");
        tag2.setCategory(category1);
        tagRepository.save(tag2);
        Tag tag3 = new Tag();
        tag3.setName("php");
        tag3.setCategory(category1);
        tagRepository.save(tag3);
        User marco = new User();
        marco.setLastName("Marco");
        marco.setLogin("marco");
        marco.setTags(Set.of(tag1, tag3));
        userRepository.save(marco);
        User polo = new User();
        polo.setLastName("Polo");
        polo.setLogin("polo");
        polo.setTags(Set.of(tag2, tag3));
        userRepository.save(polo);
        Question question1 = new Question();
        question1.setAnswer("42");
        question1.setContent("What is the universal answer of everything ?");
        final int point = 4;
        question1.setPoint(point);
        question1.setTags(Set.of(tag1));
        questionRepository.save(question1);
        Question question2 = new Question();
        question2.setAnswer("43");
        question2.setContent("What is the otheruniversal answer of everything ?");
        final int point2 = 1;
        question2.setPoint(point2);
        question2.setTags(Set.of(tag1));
        questionRepository.save(question2);
        Question question3 = new Question();
        question3.setAnswer("44");
        question3.setContent("What is antoher universal answer of everything ?");
        final int point3 = 1;
        question3.setPoint(point3);
        question3.setTags(Set.of(tag2));
        questionRepository.save(question3);
    }

    private User getPolo() {
        return userRepository.findByLastName("Polo").stream().findFirst().get();
    }

    private ResultActions getNewQuestion(final User user) throws Exception {
        return mockMvc.perform(get("/question/next")
                .queryParam("userId", Long.toString(user.getId())));
    }

    private ResultActions answerQuestion(final User user, final String answer) throws Exception {
        return mockMvc.perform(get("/response")
                .queryParam("userId", Long.toString(user.getId()))
                .queryParam("answer", answer));
    }

    private UserAnswer addNonRespondUserAnswer(final User user, final Question question) {
        var userAnswer = new UserAnswer();
        userAnswer.setQuestion(question);
        userAnswer.setUser(user);
        userAnswerRepository.save(userAnswer);
        return userAnswer;
    }

    private Question getReturnedQuestionFromResponse(final MockHttpServletResponse response) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {
        return mapper.readValue(response.getContentAsString(), Question.class);
    }

    private UserAnswer getReturnedUserAnswerFromResponse(final MockHttpServletResponse response) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {
        return mapper.readValue(response.getContentAsString(), UserAnswer.class);
    }

    /*
     * BDD
     * */
    @Test
    void userCanGetNewQuestion() throws Exception {
//        Given I am a user
        var user = getPolo();
//        When I ask a new question to answer
        var response = getNewQuestion(user)
                .andReturn()
                .getResponse();
//        Then Then i get a new question
        var returnedQuestion = mapper.readValue(response.getContentAsString(), Question.class);
        Assertions.assertTrue(returnedQuestion.getContent() instanceof String);
    }

    /*
     * BDD
     * */
    @Test
    void userCannotGetNewQuestionIfNotAnswered() throws Exception {
//        Given I am a user
        var user = getPolo();
//        And A question is waiting for answer
        var userAnswer = addNonRespondUserAnswer(user, questionRepository.findAll().iterator().next());
//        When I ask a new question to answer
        var response = getNewQuestion(user).andReturn().getResponse();
        var returnedQuestion = getReturnedQuestionFromResponse(response);
//        Then i don't get a new question
        Assertions.assertEquals(returnedQuestion.getId(), userAnswer.getQuestion().getId());
    }

    /*
     * BDD
     * */
    @Test
    void userCanAnswerQuestion() throws Exception {
//        Given I am a user
        var user = getPolo();
//        When I ask a new question to answer
        var returnedQuestion = getReturnedQuestionFromResponse(getNewQuestion(user)
                .andReturn().getResponse());
//        And i submit a valid response
        var returnedUserAnswer = validResponse(user, returnedQuestion);
//        Then i recieve the correctness of the response
        Assertions.assertTrue(returnedUserAnswer.getCorrect());
//        And i get the question's points
        Assertions.assertEquals(returnedUserAnswer.getPoints(), returnedQuestion.getPoint());
    }

    private UserAnswer validResponse(User user, Question returnedQuestion) throws Exception {
        return getReturnedUserAnswerFromResponse(answerQuestion(user, returnedQuestion.getAnswer())
                .andReturn().getResponse());
    }

    /*
     * ATDD
     * */
    @Test
    void userAnswerIsCreatedOnResponse() throws Exception {
        var user = getPolo();
        var returnedQuestion = getReturnedQuestionFromResponse(getNewQuestion(user)
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn().getResponse());
        Assertions.assertTrue(returnedQuestion instanceof Question);
        var returnedUserAnswer = getReturnedUserAnswerFromResponse(answerQuestion(user, returnedQuestion.getAnswer())
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn().getResponse());
        Assertions.assertTrue(returnedUserAnswer instanceof UserAnswer);
        Assertions.assertTrue(userAnswerRepository.findById(returnedUserAnswer.getId()).isPresent());
    }

    /*
     * ATDD
     * */
    @Test
    void gainedPointsDecreaseWhenTheQuestionIsAnsweredSeveralTime() throws Exception {
        var polo = getPolo();
        var question = getReturnedQuestionFromResponse(getNewQuestion(polo)
                .andReturn().getResponse());
        var questionPoint = question.getPoint();
        validResponse(polo, question);
        var userAnswer = validResponse(polo, question);
        Assertions.assertEquals((int) Math.floor(questionPoint / 2.0), userAnswer.getPoints());
    }

    /*
     * ATDD
     * */
    @Test
    void nextQuestionTagIsOneOfTheUsersTags() {
//        if user POST /question/next
//        A question object is returned
//        user.tags.contain(question.tag)
    }

}
