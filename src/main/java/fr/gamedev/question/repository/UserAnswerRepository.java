package fr.gamedev.question.repository;

import fr.gamedev.question.data.UserAnswer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author djer1
 *
 */
@RepositoryRestResource()
public interface UserAnswerRepository extends PagingAndSortingRepository<UserAnswer, Long> {

}
