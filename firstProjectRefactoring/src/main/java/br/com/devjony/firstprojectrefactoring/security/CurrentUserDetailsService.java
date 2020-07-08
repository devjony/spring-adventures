package br.com.devjony.firstprojectrefactoring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.devjony.firstprojectrefactoring.domain.UserDomain;
import br.com.devjony.firstprojectrefactoring.repository.UserRepository;

@Component
public class CurrentUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserDomain userDomain = userRepository.findByLogin(login);
		
		if(userDomain == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		
		return new User(userDomain.getUsername(), userDomain.getPassword(),
				true, true, true, true, userDomain.getAuthorities());
	}
}