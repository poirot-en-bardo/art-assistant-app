package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.dto.ArtworkResponseDTO;
import ie.licenta.artassistant.models.ArtworkEntity;
import ie.licenta.artassistant.models.FavouriteArtworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteArtworkRepository extends JpaRepository<FavouriteArtworkEntity, Integer> {

    List<FavouriteArtworkEntity> findAllByUserId(int userId);

    FavouriteArtworkEntity findFavouriteArtworkEntityByUserIdAndArtworkId(int userId, int artworkId);

    void deleteFavouriteArtworkEntityByUserIdAndArtworkId(int userId, int artworkId);
}

