package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Integer> {

    List<GalleryEntity> findGalleryEntitiesByMuseumId(int museumId);
}
