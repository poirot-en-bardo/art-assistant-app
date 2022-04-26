package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.UserRequestDTO;
import ie.licenta.artassistant.dto.UserResponseDTO;
import ie.licenta.artassistant.models.UserEntity;

import java.util.List;

public interface UserService {

    UserResponseDTO getUserById(UserEntity UserIdEntity);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO addUser(UserRequestDTO UserRequestDTO);

    UserResponseDTO updateUser(int userId, UserRequestDTO UserRequestDTO);

    void deleteUserById(int userId);
}
