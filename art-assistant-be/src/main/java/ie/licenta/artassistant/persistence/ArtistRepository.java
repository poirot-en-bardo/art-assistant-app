package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.dto.ArtistResponseDTO;
import ie.licenta.artassistant.models.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Integer> {
}
