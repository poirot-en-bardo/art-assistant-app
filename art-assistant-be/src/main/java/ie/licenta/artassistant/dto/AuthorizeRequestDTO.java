package ie.licenta.artassistant.dto;

import ie.licenta.artassistant.models.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeRequestDTO {

    private String sessionId;
    private List<RoleName> roles;
}
