package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.AuthorizeRequestDTO;
import ie.licenta.artassistant.dto.AuthorizeResponseDTO;
import ie.licenta.artassistant.dto.UserRequestDTO;
import ie.licenta.artassistant.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO getUserById(int id);

    UserResponseDTO getUserBySessionId(String sessionId);

    AuthorizeResponseDTO authorizeUser(AuthorizeRequestDTO authorizeRequestDTO);

    List<UserResponseDTO> getAllUsers();

}
