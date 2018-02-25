package com.mj.bundes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mj.bundes.service.impl.UserSecurityService;
import com.mj.bundes.utility.SecurityUtility;
import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;
	@Autowired
	DataSource dataSource;
	@Autowired
	private UserSecurityService userSecurityService;

	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}

	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/img/**",
			"/image/**",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/image/**",
			"/admin/js/**",
			"/plugins/**",
			"/options/**",
			"/",
			"/newUser",
			"/forgetPassword",
			"/login",
			"/fonts/**",
			"/productshelf",
			"/search/**",
			"/productDetail/**",
			"/hours",
			"/contact",
			"/searchByCategory",
			"/searchProduct"
			
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().
		/*	antMatchers("/**").*/
			antMatchers(PUBLIC_MATCHERS).
			permitAll().anyRequest().authenticated();

		http
		.authorizeRequests()
        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        .and()
			.csrf().disable().cors().disable()
			.formLogin().failureUrl("/login?error")
			/*.defaultSuccessUrl("/")*/
			.loginPage("/login").permitAll()
			.and()
			
		
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
			.and()
			.rememberMe();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
    	.usersByUsernameQuery("SELECT email, password FROM user WHERE email = ?")
    	.authoritiesByUsernameQuery("SELECT email, authority FROM role WHERE email = ?");
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}

}
