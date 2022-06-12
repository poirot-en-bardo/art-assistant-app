package ie.licenta.artassistant.services;

import ie.licenta.artassistant.common.ArtForbiddenSessionException;
import ie.licenta.artassistant.common.ArtNotFoundException;
import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.dto.UserResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.models.*;
import ie.licenta.artassistant.persistence.RoleRepository;
import ie.licenta.artassistant.persistence.TokenRepository;
import ie.licenta.artassistant.persistence.UserRepository;
import ie.licenta.artassistant.persistence.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final TokenRepository tokenRepository;
    private final ArtMapper artMapper;


    @Override
    public UserResponseDTO getUserById(int userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ArtNotFoundException(ErrorCode.ERR_08_USER_NOT_FOUND));
        List<RoleName> roleList = userRoleRepository.findAllByUser(user).stream().map(UserRoleEntity::getRole).toList();
        return artMapper.userEntityAndRolesToUserResponseDTO(user, roleList);
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserResponseDTO getUserByToken(String jwt) {
        Optional<TokenEntity> token = Optional.ofNullable(tokenRepository.findByToken(jwt));
        if(token.isEmpty()) {
            throw new ArtForbiddenSessionException(ErrorCode.ERR_08_USER_NOT_FOUND);
        }
        UserEntity user = token.get().getUser();
        List<RoleName> roleList = userRoleRepository.findAllByUser(user).stream().map(UserRoleEntity::getRole).toList();
        return artMapper.userEntityAndRolesToUserResponseDTO(user, roleList);
    }



//    public UserEntity saveUser(UserEntity user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setEnabled(true);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        return userRepository.save(user);
//    }

}
