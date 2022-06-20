package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findAllByArtworkIdOrderByCreatedAtDesc(int artworkId);
}
