package ie.licenta.artassistant.security;

import ie.licenta.artassistant.models.UserEntity;
import ie.licenta.artassistant.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByEmail(email).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id: "+id));
        return UserPrincipal.create(user);
    }

}
