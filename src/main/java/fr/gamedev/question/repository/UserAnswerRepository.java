package fr.gamedev.question.repository;

import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author djer1
 *
 */
@RepositoryRestResource()
public interface UserAnswerRepository extends PagingAndSortingRepository<UserAnswer, Long> {

    Optional<UserAnswer> findFirstUserAnswerByUserAndQuestionAndAndCorrectIsTrueOrderByDateDesc(User user, Question question);

    Optional<UserAnswer> findFirstUserAnswerByUserAndCorrectIsNull(User user);
}
