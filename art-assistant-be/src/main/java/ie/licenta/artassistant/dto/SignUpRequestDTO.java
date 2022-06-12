package ie.licenta.artassistant.dto;

import ie.licenta.artassistant.models.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {

    private String firstName;
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
 }
