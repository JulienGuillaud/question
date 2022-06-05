package fr.gamedev.question.data;

import fr.gamedev.question.repository.CategoryRepository;
import fr.gamedev.question.repository.QuestionRepository;
import fr.gamedev.question.repository.TagRepository;
import fr.gamedev.question.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {
    /**.
     * userRepository
     */
    private UserRepository userRepository;
    /**.
     * questionRepository
     */
    private QuestionRepository questionRepository;
    /**.
     * tagRepository
     */
    private TagRepository tagRepository;
    /**.
     * categoryRepository
     */
    private CategoryRepository categoryRepository;
    /**.
     * Dataloader
     * @param newUserRepository
     * @param newQuestionrepository
     * @param newTagRepository
     * @param newCategoryRepository
     */
    @Autowired
    public DataLoader(final UserRepository newUserRepository,
                      final QuestionRepository newQuestionrepository,
                      final TagRepository newTagRepository,
                      final CategoryRepository newCategoryRepository) {
        this.userRepository = newUserRepository;
        this.questionRepository = newQuestionrepository;
        this.tagRepository = newTagRepository;
        this.categoryRepository = newCategoryRepository;
    }

    /**.
     * Dataloader
     * @param args
     */
    public void run(final ApplicationArguments args) {
        Category category1 = new Category();
        category1.setName("language");
        this.categoryRepository.save(category1);
        Tag tag1 = new Tag();
        tag1.setName("java");
        tag1.setCategory(category1);
        this.tagRepository.save(tag1);
        Tag tag2 = new Tag();
        tag2.setName("python");
        tag2.setCategory(category1);
        this.tagRepository.save(tag2);
        Tag tag3 = new Tag();
        tag3.setName("php");
        tag3.setCategory(category1);
        this.tagRepository.save(tag3);
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
}
