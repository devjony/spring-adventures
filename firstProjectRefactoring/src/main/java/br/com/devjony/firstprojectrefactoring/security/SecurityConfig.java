package br.com.devjony.firstprojectrefactoring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String[] PUBLIC_MATCHERS = {"/h2-console/**"};
	private static String[] PUBLIC_MATCHERS_GET = {};
	
	@Autowired
	private CurrentUserDetailsService userDetailsService;
	
	// define confuguration permissions
	protected void configure (HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll() // enable access to all end points in array
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() // enable GET requests
			.anyRequest().authenticated() //enable everything else
			.and().formLogin().permitAll()
			// redirect to logout page on logoff
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
