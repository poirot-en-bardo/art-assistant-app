package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.ArtworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<ArtworkEntity, Integer> {

    List<ArtworkEntity> findAllByArtistIdOrderByTitle(int artistId);

    List<ArtworkEntity> findAllByGenreIdOrderByTitle(int genreId);

    List<ArtworkEntity> findAllByGalleryIdOrderByTitle(int galleryId);

    List<ArtworkEntity> findAllByRoomNumberAndGalleryIdOrderByPosition(int roomNumber, int galleryId);
}
