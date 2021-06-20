package com.binh.motel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.binh.motel.security.MyCustomLoginSuccessHandler;
import com.binh.motel.service.impl.MotelUserDetailService;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MotelUserDetailService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    return new MyCustomLoginSuccessHandler("/administrator/dashboard");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/**").permitAll();
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/chat").permitAll()
				.antMatchers("/setup").permitAll()
				.antMatchers("/lib/bootstrap/**", "/css/**", "/img/**", "/js/**").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/api/**").permitAll()
				.antMatchers("/dang-tin").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
				.antMatchers("/administrator/dashboard").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/profile/me").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
				.antMatchers("/administrator/user/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/administrator/category/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/administrator/room/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/administrator/comment/**").access("hasRole('ROLE_ADMIN')")
				.and().csrf().disable()
				.formLogin().loginPage("/login").failureUrl("/login?error=true")
//				.defaultSuccessUrl("/administrator/dashboard", true)
				.usernameParameter("user_name")
				.passwordParameter("password")
				.successHandler(successHandler())
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/dist/**", "/plugins/**", "/resources/**", "/public/**", "/static/**", "/css/**", "/js/**",
				"/images/**");
	}
}