package ie.licenta.artassistant.dto;

import ie.licenta.artassistant.constants.Role;
import ie.licenta.artassistant.models.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Role> roles;
}
