package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
}
