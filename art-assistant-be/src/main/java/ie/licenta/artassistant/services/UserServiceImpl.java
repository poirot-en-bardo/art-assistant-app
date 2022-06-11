package ie.licenta.artassistant.services;

import ie.licenta.artassistant.dto.UserResponseDTO;
import ie.licenta.artassistant.mappers.ArtMapper;
import ie.licenta.artassistant.persistence.RoleRepository;
import ie.licenta.artassistant.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ArtMapper artMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserResponseDTO getUserById(int id) {
        return null;
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        return null;
    }

//    public UserEntity saveUser(UserEntity user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setEnabled(true);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        return userRepository.save(user);
//    }

}
