package com.bishe.home.entity;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6800117436475943905L;
	private Integer rid;
	private String roleName;
	private Set<User> users;
	private Set<Privilege> privileges;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Role(Integer rid, String roleName, Set<User> users,
			Set<Privilege> privileges) {
		super();
		this.rid = rid;
		this.roleName = roleName;
		this.users = users;
		this.privileges = privileges;
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", roleName=" + roleName + ", users="
				+ users + ", privileges=" + privileges + "]";
	}
	 
	
	
}
