package fr.gamedev.repository;

import fr.gamedev.data.ScoringRule;
import fr.gamedev.data.UserAnswer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author Swan
 *
 */
@RepositoryRestResource()
public interface ScoringRuleRepository extends PagingAndSortingRepository<ScoringRule, Long> {

}
