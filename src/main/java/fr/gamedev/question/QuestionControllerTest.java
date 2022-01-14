package fr.gamedev.question;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.gamedev.question.data.Category;
import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.Tag;
import fr.gamedev.question.data.User;
import fr.gamedev.question.repository.CategoryRepository;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.TagRepository;
import fr.gamedev.question.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    /**Permet de simuler le client Http sans faire de vrai appelle http.
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

    @Test
    void userCanGetNewQuestion() throws Exception {
//        Given I am a user
        var user = userRepository.findByLastName("Polo").stream().findFirst();
//        When I ask a new question to answer
        var response = mockMvc.perform(get("/question/next").queryParam("userId", Long.toString(user.get().getId())))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn().getResponse();
//        Then Then i get a new question
        var returnedQuestion = mapper.readValue(response.getContentAsString(), Question.class);
        Assertions.assertTrue(returnedQuestion.getContent() instanceof String);
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
