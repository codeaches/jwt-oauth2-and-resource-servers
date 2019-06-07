package com.codeaches.jwtoauth2server;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource ds;

	@Override
	@Bean(BeanIds.USER_DETAILS_SERVICE)
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Override
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean("userPasswordEncoder")
	PasswordEncoder userPasswordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// BCryptPasswordEncoder(4) is used for users.password column
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> cfg = auth.jdbcAuthentication()
				.passwordEncoder(userPasswordEncoder()).dataSource(ds);

		cfg.getUserDetailsService().setEnableGroups(true);
		cfg.getUserDetailsService().setEnableAuthorities(false);
	}
}
