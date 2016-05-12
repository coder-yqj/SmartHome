package com.bishe.home.entity;

import java.io.Serializable;
import java.util.Set;

public class Privilege implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8087396390039495722L;
	private Integer pid;
	private String privilegeName;
	private Set<Role> roles;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result
				+ ((privilegeName == null) ? 0 : privilegeName.hashCode());
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
		Privilege other = (Privilege) obj;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (privilegeName == null) {
			if (other.privilegeName != null)
				return false;
		} else if (!privilegeName.equals(other.privilegeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Privilege [pid=" + pid + ", privilegeName=" + privilegeName
				+ ", roles=" + roles + "]";
	}
	
	
	
}
