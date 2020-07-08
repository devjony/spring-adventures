package br.com.devjony.firstprojectrefactoring.security;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.devjony.firstprojectrefactoring.domain.User;
import br.com.devjony.firstprojectrefactoring.repository.UserRepository;

@Component
public class CurrentUserDetailsService implements UserDetails {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
		User user = userRepository.findByUserLogin(userLogin);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found!");
		
		return new User(user.getUsername(), user.getPassword(), true, true, true, true,
				user.getAuthorities());
	}
}
