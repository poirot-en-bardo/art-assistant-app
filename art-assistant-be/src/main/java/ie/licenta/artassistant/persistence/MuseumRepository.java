package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.MuseumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuseumRepository extends JpaRepository<MuseumEntity, Integer> {
}
