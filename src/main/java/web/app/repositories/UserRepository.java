package web.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.app.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findDistinctFirstByUsernameIgnoreCase(String username);
    List<User> findAll();

}
