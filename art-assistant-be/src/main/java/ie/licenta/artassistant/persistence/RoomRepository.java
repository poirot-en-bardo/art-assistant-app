package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
}
