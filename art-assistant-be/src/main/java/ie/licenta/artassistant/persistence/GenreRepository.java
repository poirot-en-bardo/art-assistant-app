package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
}
