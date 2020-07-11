package br.com.devjony.firstprojectrefactoring.init;

import br.com.devjony.firstprojectrefactoring.domain.Permission;
import br.com.devjony.firstprojectrefactoring.domain.Role;
import br.com.devjony.firstprojectrefactoring.domain.UserDomain;
import br.com.devjony.firstprojectrefactoring.repository.PermissionRepository;
import br.com.devjony.firstprojectrefactoring.repository.RoleRepository;
import br.com.devjony.firstprojectrefactoring.repository.UserRepository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    	UserDomain userDomain = new UserDomain();
    	userDomain.setLogin("admin");
    	userDomain.setName("Administrator");
    	userDomain.setUserPassword(new BCryptPasswordEncoder().encode("1234"));
    	
    	Role roleAdmin = new Role();
    	roleAdmin.setRoleName("ROLE_ADMIN");
    	roleRepository.save(roleAdmin);
    	
    	userDomain.setRoles(Arrays.asList(roleAdmin));
    	userRepository.save(userDomain);
    	
    	UserDomain user2 = new UserDomain();
    	user2.setLogin("user");
    	user2.setName("User");
    	user2.setUserPassword(new BCryptPasswordEncoder().encode("1234"));
    	
    	Role roleUser = new Role();
    	roleUser.setRoleName("ROLE_USER");
    	
    	Permission userPermission = new Permission();
    	userPermission.setPermissionName("INSERT");
    	
    	permissionRepository.save(userPermission);
    	roleRepository.save(roleUser);

    	user2.setRoles(Arrays.asList(roleUser));
    	user2.setPermissions(Arrays.asList(userPermission));
    	userRepository.save(user2);
    	
    	UserDomain user3 = new UserDomain();
    	user3.setLogin("salvato");
    	user3.setName("Sara");
    	user3.setUserPassword(new BCryptPasswordEncoder().encode("1234"));
    	
    	user3.setRoles(Arrays.asList(roleUser, roleAdmin));
    	
    	userRepository.save(user3);
    }
}
