package ie.licenta.artassistant.security;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomApiResponse {

    private Boolean success;
    private String message;

}
