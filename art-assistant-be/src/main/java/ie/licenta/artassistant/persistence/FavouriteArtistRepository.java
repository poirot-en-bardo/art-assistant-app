package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.FavouriteArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteArtistRepository extends JpaRepository<FavouriteArtistEntity, Integer> {
}
