package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.dto.AuthorizeRequestDTO;
import ie.licenta.artassistant.dto.AuthorizeResponseDTO;
import ie.licenta.artassistant.services.AuthenticationService;
import ie.licenta.artassistant.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Data
@AllArgsConstructor
@RequestMapping("/api")
@Tag(name = "Authorization management")
public class AuthorizationController {

    private final UserService userService;

    @Operation(summary = "Authorize a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authorization"),
            @ApiResponse(responseCode = "403", description = "Forbidden")}
    )
    @PostMapping("/authorize")
    public ResponseEntity<AuthorizeResponseDTO> authorizeUser(@RequestBody AuthorizeRequestDTO authorizeRequestDTO) {
        return new ResponseEntity<>(userService.authorizeUser(authorizeRequestDTO), HttpStatus.OK);
    }


}
