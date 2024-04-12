package sorenrahimi.g4s2m2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sorenrahimi.g4s2m2.entities.Author;
import sorenrahimi.g4s2m2.entities.BlogPost;

import java.util.List;

@Repository
public interface BlogsRepository extends JpaRepository<BlogPost, Integer> {
    List<BlogPost> findByAuthor(Author author);
}
