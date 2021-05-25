package com.binh.motel.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MotelUserDetails implements UserDetails {

	private static final long serialVersionUID = -6081995750186714642L;

	private Collection<? extends GrantedAuthority> authorities;

	private String email;

	private String displayName;

	private String password;

	private String username;

	private Boolean enabled;

	private Boolean accountNonExpired;

	private Boolean accountNonLocked;

	private boolean credentialsNonExpired;

	public MotelUserDetails(String email, String displayName, String username,
			String password, Boolean enabled, Collection<? extends GrantedAuthority> authorities) {
		this.email = email;
		this.displayName = displayName;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.authorities = authorities;
	}

	public MotelUserDetails(String email, String displayName, String password,
			String username, Boolean enabled, Boolean accountNonExpired, Boolean accountNonLocked,
			boolean credentialsNonExpired, Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
		this.email = email;
		this.displayName = displayName;
		this.password = password;
		this.username = username;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void eraseCredentials() {
		this.password = null;
	}
}