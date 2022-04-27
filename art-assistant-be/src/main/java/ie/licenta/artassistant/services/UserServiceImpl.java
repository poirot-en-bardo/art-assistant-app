package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.UserRequestDTO;
import ie.licenta.artassistant.dto.UserResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.UserEntity;
import ie.licenta.artassistant.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ArtMapper artMapper;

    @Override
    public UserResponseDTO getUserById(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_08_USER_NOT_FOUND));
        return artMapper.userEntityToUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        return artMapper.userEntityToUserResponseDTO(userRepository.save(
                artMapper.userRequestDTOToUserEntity(userRequestDTO)
        ));
    }

    @Override
    public UserResponseDTO updateUser(int userId, UserRequestDTO UserRequestDTO) {
        return null;
    }

    @Override
    public void deleteUserById(int userId) {

    }
}
