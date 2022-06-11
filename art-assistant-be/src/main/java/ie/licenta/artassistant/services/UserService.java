package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.AuthorizeRequestDTO;
import ie.licenta.artassistant.dto.AuthorizeResponseDTO;
import ie.licenta.artassistant.dto.UserRequestDTO;
import ie.licenta.artassistant.dto.UserResponseDTO;
import ie.licenta.artassistant.models.UserEntity;

import java.util.List;

public interface UserService {

    UserResponseDTO getUserById(int id);

    List<UserResponseDTO> getAllUsers();

//    public UserEntity saveUser(UserEntity user);
}
