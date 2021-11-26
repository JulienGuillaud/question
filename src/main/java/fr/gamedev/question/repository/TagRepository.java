package fr.gamedev.question.repository;

import fr.gamedev.question.data.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Swan
 *
 */
@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {

}
