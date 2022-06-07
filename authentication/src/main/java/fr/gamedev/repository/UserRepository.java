package fr.gamedev.repository;

import fr.gamedev.data.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author Swan
 *
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  Optional<User> findFirstByLastName(@Param("name") String name);

}
