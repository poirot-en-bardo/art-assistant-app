package ie.licenta.artassistant.dto;

import ie.licenta.artassistant.models.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleName> roles;
}
