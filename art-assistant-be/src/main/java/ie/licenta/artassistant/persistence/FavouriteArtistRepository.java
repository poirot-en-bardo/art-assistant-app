package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.FavouriteArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteArtistRepository extends JpaRepository<FavouriteArtistEntity, Integer> {
    List<FavouriteArtistEntity> findAllByUserId(int userId);

    FavouriteArtistEntity findFavouriteArtistEntityByUserIdAndArtistId(int userId, int artistId);

    void deleteFavouriteArtistEntityByUserIdAndArtistId(int userId, int artistId);
}
