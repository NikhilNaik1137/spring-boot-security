package com.nik.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(configfuration -> {
            configfuration.disable();
        }).authorizeHttpRequests(reg -> {
            reg.requestMatchers("api/v1/devices").permitAll();
//            reg.requestMatchers("api/v1/devices").authenticated();
            reg.requestMatchers("api/v1/users/**", "api/v1/login", "api/v1/logout", "/error").permitAll();
            reg.requestMatchers("swagger-ui/**", "v3/api-docs/**").authenticated();
        }).logout((logout)-> logout.logoutUrl("/api/v1/logout").permitAll())
                .requestCache(cache -> cache.requestCache(new NullRequestCache()))
//                .securityContext(securityContext -> securityContext.
//                securityContextRepository(new HttpSessionSecurityContextRepository())
//        )
        ;
//                .authorizeHttpRequests(matcherReg -> matcherReg.requestMatchers());     // NOT RECOMMENDED, need to properly set csrf configs
//        return http.formLogin(Customizer.withDefaults()).build();
        return http.build();
    }

//    @Bean
//    UserDetailsService setupInMemoryUserDetails(PasswordEncoder passwordEncoder) {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
////        User.withUsername().password().roles()
//        UserDetails userDetails = new User("test_user",passwordEncoder.encode("testpass"), List.of(new SimpleGrantedAuthority("enduser")));
//        userDetailsManager.createUser(userDetails);
//        return userDetailsManager;
//    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
//    AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }

}
