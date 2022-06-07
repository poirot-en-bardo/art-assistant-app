package ie.licenta.artassistant.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AuthUrls {
    @Value("${urls.security.baseUrl}")
    private String baseUrl;
}
