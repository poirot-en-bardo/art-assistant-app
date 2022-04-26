package ie.licenta.artassistant.service;

import ie.licenta.artassistant.dto.UserRequestDTO;
import ie.licenta.artassistant.dto.UserResponseDTO;
import ie.licenta.artassistant.models.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserResponseDTO getUserById(UserEntity UserIdEntity) {
        return null;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO UserRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO updateUser(int userId, UserRequestDTO UserRequestDTO) {
        return null;
    }

    @Override
    public void deleteUserById(int userId) {

    }
}
