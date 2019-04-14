package web.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.app.entities.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}
