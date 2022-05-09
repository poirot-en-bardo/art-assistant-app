package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtForbiddenSessionException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.constants.Role;
import ie.licenta.artassistant.dto.AuthorizeRequestDTO;
import ie.licenta.artassistant.dto.AuthorizeResponseDTO;
import ie.licenta.artassistant.dto.UserRequestDTO;
import ie.licenta.artassistant.dto.UserResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.RoleEntity;
import ie.licenta.artassistant.models.SessionEntity;
import ie.licenta.artassistant.models.UserEntity;
import ie.licenta.artassistant.persistence.RoleRepository;
import ie.licenta.artassistant.persistence.SessionRepository;
import ie.licenta.artassistant.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final RoleRepository roleRepository;
    private final ArtMapper artMapper;

    @Override
    public UserResponseDTO getUserById(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_08_USER_NOT_FOUND));
        List<Role> roles = roleRepository.findAllByUser(user).stream().map(RoleEntity::getRole).toList();
        return artMapper.userEntityAndRolesToUserReponseDTO(user, roles);
    }

    @Override
    public UserResponseDTO getUserBySessionId(String sessionId) {
        SessionEntity sessionEntity = sessionRepository.findById(sessionId).orElseThrow(() ->
                new ArtNotFoundException(ErrorCode.ERR_08_USER_NOT_FOUND));
        UserEntity user = sessionEntity.getUser();
        List<Role> roles = roleRepository.findAllByUser(user).stream().map(RoleEntity::getRole).toList();
        return artMapper.userEntityAndRolesToUserReponseDTO(user, roles);
    }

    @Override
    public AuthorizeResponseDTO authorizeUser(AuthorizeRequestDTO authorizeRequestDTO) {
        SessionEntity sessionEntity = sessionRepository.findById(authorizeRequestDTO.getSessionId()).orElseThrow(() ->
                new ArtForbiddenSessionException(ErrorCode.ERR_10_SESSION_NOT_FOUND));
        UserEntity user = sessionEntity.getUser();
        List<Role> userRoles = roleRepository.findAllByUser(user).stream().map(RoleEntity::getRole).toList();
        List<Role> authorizedRoles = authorizeRequestDTO.getRoles();
        for (Role userRole : userRoles) {
            if (authorizedRoles.contains(userRole)) {
                return new AuthorizeResponseDTO(user.getId());
            }
        }
        throw new ArtForbiddenSessionException(ErrorCode.ERR_11_SESSION_FORBIDDEN);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return null;
    }
}
