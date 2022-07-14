package fr.gamedev.repository;

import fr.gamedev.data.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Swan
 *
 */
@RepositoryRestResource(collectionResourceRel = "skill", path = "skill")
public interface skillRepository extends PagingAndSortingRepository<Skill, Long> {

}
