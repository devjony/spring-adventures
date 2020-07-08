package br.com.devjony.firstprojectrefactoring.init;

import br.com.devjony.firstprojectrefactoring.domain.Role;
import br.com.devjony.firstprojectrefactoring.domain.UserDomain;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    	UserDomain userDomain = new UserDomain();
    	userDomain.setLogin("Alberto");
    	userDomain.setName("Fernando Alberto");
    	userDomain.setUserPassword(new BCryptPasswordEncoder().encode("123"));
    	
    	Role roleAdmin = new Role();
    	roleAdmin.setRoleName("ROLE_ADMIN");
    	roleRepository.save(roleAdmin);
    	
    	userDomain.setRoles(Arrays.asList(roleAdmin));
    	userRepository.save(userDomain);
    }
}
