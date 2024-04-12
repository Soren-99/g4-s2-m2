package sorenrahimi.g4s2m2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sorenrahimi.g4s2m2.entities.Author;

import java.util.Optional;
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);
}
