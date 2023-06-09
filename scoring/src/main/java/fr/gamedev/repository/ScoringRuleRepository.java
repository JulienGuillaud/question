package fr.gamedev.repository;

import fr.gamedev.domain.ScoringRule;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Swan
 *
 */
@RepositoryRestResource()
public interface ScoringRuleRepository extends PagingAndSortingRepository<ScoringRule, Long> {

}
