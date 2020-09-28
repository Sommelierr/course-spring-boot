package by.s0mmelier.service;

import by.s0mmelier.models.ERole;
import by.s0mmelier.models.Role;
import by.s0mmelier.models.User;
import by.s0mmelier.payload.request.LoginRequest;
import by.s0mmelier.payload.request.SignupRequest;
import by.s0mmelier.payload.response.JwtResponse;
import by.s0mmelier.payload.response.MessageResponse;
import by.s0mmelier.repository.RoleRepository;
import by.s0mmelier.repository.UserRepository;
import by.s0mmelier.security.jwt.JwtUtils;
import by.s0mmelier.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

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

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(),
                roles,userDetails.isBlocked()));
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        initRoles();
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        createUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
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
