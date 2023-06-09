package fr.gamedev.repository;

import fr.gamedev.domain.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Swan
 *
 */
@RepositoryRestResource(collectionResourceRel = "skill", path = "skill")
public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {

}
