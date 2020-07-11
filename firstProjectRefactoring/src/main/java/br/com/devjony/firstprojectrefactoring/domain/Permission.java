package br.com.devjony.firstprojectrefactoring.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permission implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 299248315814952079L;

	@Id
	String permissionName;
	
	@ManyToMany(mappedBy = "permissions")
	private List<UserDomain> users;
	
	public String getPermissionName() {
		return permissionName;
	}
	
	public void setPermissionName(String permissionaName) {
		this.permissionName = permissionaName;
	}
	
	public List<UserDomain> getUsers() {
		return users;
	}
	
	public void setUsers(List<UserDomain> users) {
		this.users = users;
	}
	
	@Override
	public String getAuthority() {
		return this.permissionName;
	}
}
