package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.ArtworkEntity;
import ie.licenta.artassistant.models.RoomIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<ArtworkEntity, Integer> {

    List<ArtworkEntity> findAllByArtistId(int artistId);

    List<ArtworkEntity> findAllByGenreId(int genreId);

    List<ArtworkEntity> findAllByGalleryId(int galleryId);

    List<ArtworkEntity> findAllByRoomNumberAndGalleryId(int roomNumber, int galleryId);
}
