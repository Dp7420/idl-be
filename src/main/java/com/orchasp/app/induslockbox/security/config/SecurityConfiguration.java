package com.orchasp.app.induslockbox.security.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.orchasp.app.induslockbox.entity.Role;
import com.orchasp.app.induslockbox.service.UserDetailsImp;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{

    @Autowired
    private AuthenticationFilter authenticationFilter;
    @Autowired
    private UserDetailsImp adminService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.cors(withDefaults())
				.authorizeHttpRequests(request -> request
						.antMatchers("/api/auth/**").permitAll()
						.antMatchers("/files/get/**").permitAll()
						.antMatchers("/api/admin/**").hasAnyAuthority(Role.ROLE_ADMIN.name())
						.antMatchers("/api/user/**").hasAnyAuthority(Role.ROLE_USER.name())
						.anyRequest().authenticated())
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}



    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(adminService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
