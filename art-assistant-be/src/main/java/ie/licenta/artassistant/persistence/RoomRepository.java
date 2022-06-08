package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.GalleryEntity;
import ie.licenta.artassistant.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    List<RoomEntity> findRoomEntitiesByGalleryIdOrderById(int galleryId);
}
