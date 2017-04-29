package main.java.spring.boot.oauth2.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import main.java.spring.boot.oauth2.bo.IUserBo;
import main.java.spring.boot.oauth2.model.User;
import main.java.spring.boot.oauth2.model.UserRole;


@Service("userDetailsService")
public class CustUserDetailsService implements UserDetailsService{

	
	@Autowired
	IUserBo userBo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userBo.findByUsername(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		return buildUserForAuthentication(user, authorities);
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities){
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}
