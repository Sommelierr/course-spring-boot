package by.s0mmelier.service;

import by.s0mmelier.models.ERole;
import by.s0mmelier.models.Role;
import by.s0mmelier.models.User;
import by.s0mmelier.payload.request.SignupRequest;
import by.s0mmelier.repository.RoleRepository;
import by.s0mmelier.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    public void initRoles(){
        if(!roleRepository.existsByName(ERole.ROLE_USER)){
            Role roleUser = new Role(ERole.ROLE_USER);
            roleRepository.save(roleUser);
        }
        if(!roleRepository.existsByName(ERole.ROLE_ADMIN)){
            Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            roleRepository.save(roleAdmin);
        }
    }

    public void createUser(SignupRequest signUpRequest){
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        if(signUpRequest.getUsername().equals("admin")){
            Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
            roles.add(adminRole.get());
        }
        user.setRoles(roles);
        userRepository.save(user);
    }
}
