package in.org.dig.induslockbox.security.config;

import static org.springframework.security.config.Customizer.withDefaults;

import in.org.dig.induslockbox.entity.Role;
import in.org.dig.induslockbox.service.JwtServiceImpl;
import in.org.dig.induslockbox.service.UserDetailsImp;
import lombok.RequiredArgsConstructor;
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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsImp userDetailsService;
    private final JwtServiceImpl jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        AuthenticationFilter authenticationFilter =
                new AuthenticationFilter(jwtService, userDetailsService);

        http
                .csrf(AbstractHttpConfigurer::disable)

                .cors(withDefaults())

                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/files/get/**").permitAll()
                        .requestMatchers("/api/admin/**")
                        .hasAnyAuthority(Role.ROLE_ADMIN.name())
                        .requestMatchers("/api/user/**")
                        .hasAnyAuthority(Role.ROLE_USER.name())
                        .anyRequest()
                        .authenticated()
                )

                .sessionManagement(manager ->
                        manager.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authenticationProvider(authenticationProvider())

                .addFilterBefore(
                        authenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager(
            AuthenticationConfiguration authConfig)
            throws Exception {

        return authConfig.getAuthenticationManager();
    }
}