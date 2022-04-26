package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.MuseumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MuseumRepository extends JpaRepository<MuseumEntity, Integer> {

    List<MuseumEntity> findAllByCountryId(int countryId);
}
