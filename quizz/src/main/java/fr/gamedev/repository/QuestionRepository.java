package fr.gamedev.repository;

import fr.gamedev.domain.Question;
import fr.gamedev.domain.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.*;

/**
 * @author Swan
 *
 */
@RepositoryRestResource(collectionResourceRel = "question", path = "question")
public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

    default Optional<Question> getRandomQuestion(Set<Skill> skills) {
        if (skills.isEmpty()) {
            return Optional.empty();
        }

        ArrayList<Skill> tagList = new ArrayList<>(skills);
        Collections.shuffle(tagList);
        Iterator<Skill> iterator = tagList.iterator();
        Optional<Question> randomQuestion = Optional.empty();
        while (randomQuestion.isEmpty() && iterator.hasNext()) {
            Skill tag = iterator.next();
            List<Question> questionByTag = this.findQuestionByTags(tag);
            Collections.shuffle(questionByTag);
            randomQuestion = questionByTag.stream().findFirst();
        }
        return randomQuestion;
    }

    List<Question> findQuestionByTags(Skill skill);
}
