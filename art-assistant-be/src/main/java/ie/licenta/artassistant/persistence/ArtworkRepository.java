package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.ArtworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtworkRepository extends JpaRepository<ArtworkEntity, Integer> {
}
