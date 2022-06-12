package ie.licenta.artassistant.persistence;

import ie.licenta.artassistant.models.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    TokenEntity findByToken(String token);
}
