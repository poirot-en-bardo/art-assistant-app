package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.RoleEntity;
import ie.licenta.artassistant.models.RoleName;
import ie.licenta.artassistant.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(RoleName roleName);
}
