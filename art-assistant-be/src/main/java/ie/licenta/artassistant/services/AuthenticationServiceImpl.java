//package ie.licenta.artassistant.services;
//
//import ie.licenta.artassistant.common.ArtBadRequestException;
//import ie.licenta.artassistant.common.ArtNotFoundException;
//import ie.licenta.artassistant.common.ErrorCode;
//import ie.licenta.artassistant.dto.SignInRequestDTO;
//import ie.licenta.artassistant.dto.SignInResponseDTO;
//import ie.licenta.artassistant.dto.SignUpRequestDTO;
//import ie.licenta.artassistant.dto.SignUpResponseDTO;
//import ie.licenta.artassistant.mappers.ArtMapper;
//import ie.licenta.artassistant.models.RoleEntity;
//import ie.licenta.artassistant.models.SessionEntity;
//import ie.licenta.artassistant.models.UserEntity;
//import ie.licenta.artassistant.persistence.RoleRepository;
//import ie.licenta.artassistant.persistence.SessionRepository;
//import ie.licenta.artassistant.persistence.UserRepository;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//@Data
//@AllArgsConstructor
//public class AuthenticationServiceImpl implements AuthenticationService {
//
//    private final RoleRepository roleRepository;
//    private final SessionRepository sessionRepository;
//    private final UserRepository userRepository;
//    private final ArtMapper artMapper;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
//
//    @Override
//    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
//        if (userRepository.findByEmail(signUpRequestDTO.getEmail()).isPresent()) {
//            throw new ArtBadRequestException(ErrorCode.ERR_09_USER_EMAIL_TAKEN);
//        }
//        String hashedPass = bCryptPasswordEncoder.encode(signUpRequestDTO.getPassword());
//        signUpRequestDTO.setPassword(hashedPass);
//
//        UserEntity newUser = userRepository.save(
//                artMapper.signUpRequestDTOToUserEntity(signUpRequestDTO));
//        signUpRequestDTO.getRoles().forEach((role) -> {
//                    RoleEntity roleEntity = new RoleEntity();
//                    roleEntity.setRole(role);
//                    roleEntity.setUser(newUser);
//                    roleRepository.save(roleEntity);
//                }
//        );
//        SessionEntity savedSession = sessionRepository.save(new SessionEntity(null, newUser));
//
//        return new SignUpResponseDTO(savedSession.getId());
//    }
//
//    @Transactional
//    @Override
//    public SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
//        UserEntity user = userRepository.findByEmail(
//                signInRequestDTO.getEmail())
//                .orElseThrow(() -> new ArtNotFoundException(ErrorCode.ERR_08_USER_NOT_FOUND));
//        if(bCryptPasswordEncoder.matches(signInRequestDTO.getPassword(), user.getPassword())) {
//            SessionEntity savedSession = sessionRepository.save(new SessionEntity(null, user));
//            return new SignInResponseDTO(savedSession.getId());
//        }
//        else {
//            return null;
//        }
//    }
//}
