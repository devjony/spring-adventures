package br.com.devjony.firstprojectrefactoring.security;

import java.util.concurrent.ExecutionException;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String[] PUBLIC_MATCHERS = {"/h2-console/**"};
	private static String[] PUBLIC_MATCHERS_GET = {};
	
	// definindo configurações de permissão
	protected void configure (HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll() // autorização para todos endpoints estiverem no array
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() // autorização para requests GET
			.anyRequest().authenticated() //autentica todo o restante
			.and().formLogin().permitAll()
			// ao sair direciona para logout
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser("admin").password(encoder.encode("123")).roles("ADMIN");
	}
}
