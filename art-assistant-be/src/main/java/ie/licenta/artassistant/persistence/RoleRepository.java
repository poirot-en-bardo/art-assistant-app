package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.RoleEntity;
import ie.licenta.artassistant.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    List<RoleEntity> findAllByUser(UserEntity userEntity);
}
