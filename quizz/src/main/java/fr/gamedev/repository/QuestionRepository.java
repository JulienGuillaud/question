package fr.gamedev.repository;

import fr.gamedev.data.Question;
import fr.gamedev.data.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.*;

/**
 * @author Swan
 *
 */
@RepositoryRestResource(collectionResourceRel = "question", path = "question")
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

    default Optional<Question> getRandomQuestion(Set<Tag> tags) {
        if (tags.isEmpty()) {
            return Optional.empty();
        }

        ArrayList<Tag> tagList = new ArrayList<>(tags);
        Collections.shuffle(tagList);
        Iterator<Tag> iterator = tagList.iterator();
        Optional<Question> randomQuestion = Optional.empty();
        while (randomQuestion.isEmpty() && iterator.hasNext()) {
            Tag tag = iterator.next();
            List<Question> questionByTag = this.findQuestionByTags(tag);
            Collections.shuffle(questionByTag);
            randomQuestion = questionByTag.stream().findFirst();
        }
        return randomQuestion;
    }

    List<Question> findQuestionByTags(Tag tag);
}
