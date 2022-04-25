package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.FavouriteArtworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteArtworkRepository extends JpaRepository<FavouriteArtworkEntity, Integer> {
}
