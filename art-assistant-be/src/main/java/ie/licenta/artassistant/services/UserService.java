package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.UserRequestDTO;
import ie.licenta.artassistant.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO getUserById(int id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO addUser(UserRequestDTO UserRequestDTO);

    UserResponseDTO updateUser(int userId, UserRequestDTO UserRequestDTO);

    void deleteUserById(int userId);
}
