package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Integer> {
}
