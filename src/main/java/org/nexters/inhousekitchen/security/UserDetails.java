package org.nexters.inhousekitchen.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetails extends User {

	private int memberId;
	private String username;
	private String password;
	private String email;
	
	private static Collection<? extends GrantedAuthority> authorities(User user) { 
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
		user.getAuthorities().forEach(a -> { 
			authorities.add(new SimpleGrantedAuthority(a.getAuthority())); 
		}); 
		return authorities; 
		 
	}
	
	
	public int getMemberId() {
		return memberId;
	}


	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	

	public UserDetails(String username, String password, 
			boolean enabled, boolean accountNonExpired, 
			boolean credentialsNonExpired, boolean accountNonLocked, 
			Collection<? extends GrantedAuthority> authorities) { 
		super(username, password, authorities); 
		// TODO Auto-generated constructor stub 
	}
	
}
