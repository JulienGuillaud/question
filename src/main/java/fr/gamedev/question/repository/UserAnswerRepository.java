package fr.gamedev.question.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.gamedev.question.data.Question;
import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;

/**
 * @author djer1
 *
 */
@RepositoryRestResource()
public interface UserAnswerRepository extends PagingAndSortingRepository<UserAnswer, Long> {

	Optional<UserAnswer> findFirstUserAnswerByUserAndQuestionAndCorrectOrderByDate(User user, Question question);

}
