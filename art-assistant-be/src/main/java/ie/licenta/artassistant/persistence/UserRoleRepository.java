package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.TokenEntity;
import ie.licenta.artassistant.models.UserEntity;
import ie.licenta.artassistant.models.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {

    List<UserRoleEntity> findAllByUser(UserEntity user);

}
