package br.com.devjony.firstprojectrefactoring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UserDomain implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4260447475889917665L;
	
	@Id
	private String login;
	private String name;
	private String userPassword;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "login"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "roleName")
			)
	private List<Role> roles;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinTable(
			name = "users_permissions",
			joinColumns = @JoinColumn(
					name = "Puser_id", referencedColumnName = "login"),
			inverseJoinColumns = @JoinColumn(
					name = "Prole_id", referencedColumnName = "permissionName"))
	private List<Permission> permissions;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserPassword() {
		return this.userPassword;
	}
	
	public void setUserPassword(String password) {
		this.userPassword = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<Permission> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// Extract list of permissions (name)
		this.permissions.forEach(p -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(p.getPermissionName());
			authorities.add(authority);
		});
		
		// Extract list of roles (ROLE_NAME)
		this.roles.forEach(r -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(r.getRoleName());
			authorities.add(authority);
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.userPassword;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}