package org.givenkind.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.givenkind.model.UserLogon;
import org.givenkind.model.UserRole;
import org.givenkind.repository.UserLogonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
@Service
public class CustomUserDetailsService implements UserDetailsService {

	Logger log = LoggerFactory.getLogger(getClass());
	@Inject UserLogonRepository userLogonRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		log.info("username was "+arg0);
		if (arg0 == null || arg0.length() == 0) {
			return null;
		}
		UserLogon user = userLogonRepository.findByLoginId(arg0);
		log.info("userLogonObject null? "+(user == null));
		UserRole role = user.getRole();
		log.info("role "+role);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		log.info("Granted Role: " + role.getName());
		
		String roleName = role.getName();
		if(!roleName.startsWith("ROLE_")) {
			roleName = "ROLE_"+roleName;
		}
		
		authorities.add(new SimpleGrantedAuthority(roleName.toUpperCase()));
	
		log.info("Authorities added");
		
		String loginId = user.getLoginId();
		String passwordHash = user.getPasswordHash();
		log.info("loginId="+loginId+" passwordHash="+passwordHash);
		return new org.springframework.security.core.userdetails.User(loginId, passwordHash, authorities);
	}

}
