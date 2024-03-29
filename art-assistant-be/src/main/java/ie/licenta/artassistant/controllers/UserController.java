package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.UserResponseDTO;
import ie.licenta.artassistant.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@ApiOperation("User API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "User management")
@Data
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)))
    })
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> getUserInfoById(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }


//    @GetMapping("/user/users")
//    public ResponseEntity<UserResponseDTO> getUserByIdOrSessionId(@RequestParam(required = false) Optional<Integer> userId,
//                                                                  @RequestParam(required = false) Optional<String> sessionId) {
//        if (userId.isPresent()) {
//            return new ResponseEntity<>(userService.getUserById(userId.get()), HttpStatus.OK);
//        } else if (sessionId.isPresent()) {
//            return new ResponseEntity<>(userService.getUserBySessionId(sessionId.get()), HttpStatus.OK);
//        }
//        throw new ArtNotFoundException(ErrorCode.ERR_08_USER_NOT_FOUND);
//    }


//    @GetMapping("/users/{userId}")
//    public ResponseEntity<UserResponseDTO> getUserInfoById(@PathVariable int userId) {
//        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
//    }
//

    @Operation(summary = "Get logged user by jwt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user id",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)))
    })
    @GetMapping("/user/{jwt}")
    public ResponseEntity<UserResponseDTO> getLoggedUser(@PathVariable String jwt) {
        return new ResponseEntity<>(userService.getUserByToken(jwt), HttpStatus.OK);
    }

}
