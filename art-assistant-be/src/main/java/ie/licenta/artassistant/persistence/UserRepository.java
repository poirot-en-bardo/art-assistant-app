package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
