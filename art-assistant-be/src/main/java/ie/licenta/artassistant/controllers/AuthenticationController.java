package ie.licenta.artassistant.controllers;

import ie.licenta.artassistant.common.ArtBadRequestException;
import ie.licenta.artassistant.common.ArtInternalServerErrorException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.*;
import ie.licenta.artassistant.models.RoleEntity;
import ie.licenta.artassistant.models.RoleName;
import ie.licenta.artassistant.models.TokenEntity;
import ie.licenta.artassistant.models.UserEntity;
import ie.licenta.artassistant.persistence.RoleRepository;
import ie.licenta.artassistant.persistence.TokenRepository;
import ie.licenta.artassistant.persistence.UserRepository;
import ie.licenta.artassistant.security.CustomApiResponse;
import ie.licenta.artassistant.security.JwtTokenProvider;
import ie.licenta.artassistant.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@CrossOrigin("*")
@Data
@AllArgsConstructor
@RequestMapping("/api")
@Tag(name = "Authentication management")
public class AuthenticationController {

    private final UserRepository userRepository;

    private final UserService userService;

    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "User Sign-Up")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Sign-up successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SignUpResponseDTO.class)))
    })
    @PostMapping("/authentication/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        if(userRepository.existsByEmail(signUpRequestDTO.getEmail())) {
            return new ResponseEntity(new CustomApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity(signUpRequestDTO.getFirstName(), signUpRequestDTO.getLastName(),
                signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() ->
                new ArtBadRequestException(ErrorCode.ERR_16_ROLE_NOT_SET));
        user.setRoles(Collections.singleton(userRole));

        UserEntity result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("api/users/{username}")
                .buildAndExpand(result.getEmail()).toUri();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        TokenEntity tokenEntity = new TokenEntity(jwt, result);
        tokenRepository.save(tokenEntity);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));

//        return ResponseEntity.created(location).body(
//                new CustomApiResponse(true, "User registered successfully"));
    }

    @Operation(summary = "User Sign-In")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtInternalServerErrorException.class))),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtBadRequestException.class))),
            @ApiResponse(responseCode = "200", description = "Sign-in successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SignInResponseDTO.class)))
    })
    @PostMapping("/authentication/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequestDTO signInRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequestDTO.getEmail(), signInRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        UserEntity user = userRepository.findByEmail(signInRequestDTO.getEmail()).get();
        TokenEntity tokenEntity = new TokenEntity(jwt, user);
        tokenRepository.save(tokenEntity);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

}
