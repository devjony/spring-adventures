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
	
	private static String[] PUBLIC_MATCHERS = {"/h2-console/**", "/student/create", "/question/create"};
	private static String[] PUBLIC_MATCHERS_GET = {"/welcome"};
	
	@Autowired
	private CurrentUserDetailsService userDetailsService;
	
	// define confuguration permissions
	protected void configure (HttpSecurity http) throws Exception {

		// define confuguration permissions	
		http.authorizeRequests()
			// enable GET requests
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			
			// enable everything to users has this role
			.antMatchers(PUBLIC_MATCHERS).hasRole("ADMIN")
			
			// enable these endpoints to users has this role
			.antMatchers("/answer").hasRole("USER")
			.antMatchers("/answer").hasAnyAuthority("INSERT")
			
			// everything there aren't in PUBLIC_MATCHERS needs to be authenticated
			.anyRequest().authenticated()
			
				.and().formLogin().permitAll()
				.loginProcessingUrl("/signin")
				.loginPage("/welcome").permitAll()
				.usernameParameter("userNameField")
				.passwordParameter("passwordField")
				.defaultSuccessUrl("/index", true)
				.permitAll()
			
			// redirect to logout page on logoff
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/welcome");
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
