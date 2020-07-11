package br.com.devjony.firstprojectrefactoring.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8941041442553514124L;

	@Id
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	private List<UserDomain> users;
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public List<UserDomain> getUsers() {
		return users;
	}
	
	public void setUsers(List<UserDomain> users) {
		this.users = users;
	}
	
	@Override
	public String getAuthority() {
		return this.roleName;
	}
}
