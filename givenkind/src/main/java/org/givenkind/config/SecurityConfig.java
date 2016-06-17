package org.givenkind.config;

import org.givenkind.security.XSSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		return encoder;
	}
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(getPasswordEncoder());
		return provider;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider()).userDetailsService(userDetailsService());//.passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(getAuthenticationProvider())
		.userDetailsService(userDetailsService())
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				.and()
			.authorizeRequests()
				/*.antMatchers("/*").permitAll()*/
				.antMatchers("/donor").hasRole("DONOR")
				.antMatchers("/donor/**").hasRole("DONOR")
				.antMatchers("/donorlist").hasRole("DONOR")
				.antMatchers("/donorlist/**").hasRole("DONOR")
				.antMatchers("/nonprofit").hasRole("NONPROFIT")
				.antMatchers("/nonprofit/**").hasRole("NONPROFIT")
				.antMatchers("/wishlist").hasAnyRole("DONOR", "NONPROFIT")
				.antMatchers("/wishlist/**").hasAnyRole("DONOR", "NONPROFIT")
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/resetPassword/*").permitAll()
				.antMatchers("/itemDetails/*").permitAll()
				.antMatchers("/*").permitAll()
				.anyRequest().authenticated()
				.and()
			.logout()
				.logoutSuccessUrl("/home")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.csrf()
				.and()
			.addFilterAfter(new XSSFilter(), SwitchUserFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/resources/**",
				"/css/**",
				"/fonts/**",
				"/img/**",
				"/js/**");
		
		
	}
	
	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	
}
