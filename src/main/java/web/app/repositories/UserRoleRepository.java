package web.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.app.entities.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
