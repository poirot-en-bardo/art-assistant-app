package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
