package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.FavouriteArtworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteArtworkRepository extends JpaRepository<FavouriteArtworkEntity, Integer> {

    List<FavouriteArtworkEntity> findAllByUserId(int userId);
}

