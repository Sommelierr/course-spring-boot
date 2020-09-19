package by.s0mmelier.service;

import by.s0mmelier.models.ERole;
import by.s0mmelier.models.Role;
import by.s0mmelier.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role getRoleAdmin(){
        Optional<Role> roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN);
        return  roleAdmin.get();
    }
}
