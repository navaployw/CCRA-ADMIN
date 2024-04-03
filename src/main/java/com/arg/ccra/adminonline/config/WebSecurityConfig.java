package com.arg.ccra.adminonline.config;


import ch.qos.logback.classic.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;

/**
*
* @author sittichaim
* 
*/
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtRequestFilter jwtRequestFilter;
        private final String DEFAULT_PATH = "/home/kumpeep/Project/ccra-api-admin/ccra-api-admin/ccraapidb.properties";//fix to local path; 
        private final Logger logger = (Logger) LoggerFactory.getLogger(WebSecurityConfig.class);
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        // We don't need CSRF for this example         
        
        httpSecurity
        .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
        .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/get_ccra_report"
                                    ,"/api/admin/checkLogin"
                                    ,"/api/admin/logout"
                                    ,"/api/admin/getVersionNo"
                                    ,"/api/admin/**"
                                    ,"/api/user/**"
                                    ,"/api/report/**"
                                    ,"/v3/**"
                                    ,"/ccraapiauth/registerUser"
                                    ,"/error"
                                    ,"/index.html"
                                    , "/assets/**"
                                    , "/main.*"
                                    , "/polyfills.*"
                                    , "/runtime.*"
                                    , "/styles.*"
                                    , "/favicon.ico"
                                    ,"/swagger-ui.html"
                                    ,"/swagger-ui/**"
                                    ,"/api-docs/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()                
        )
        
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .permitAll()
        )
        
        // make sure we use stateless session; session won't be used to
        // store user's state.
        .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
            .authenticationEntryPoint(jwtAuthenticationEntryPoint))
            // .and()
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        
        httpSecurity.cors(Customizer.withDefaults());
        
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
//        corsConfiguration.setAllowedOrigins(List.of(aProp.getProperty("api.alloworigin"),aProp.getProperty("api.allowadminorigin"),aProp.getProperty("api.allowauthorigin")));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE","OPTIONS", "PUT", "PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        
        return httpSecurity.build();
    }
    
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
	    config.addAllowedMethod("PUT");
//	     config.addAllowedMethod("POST");
	    //allow Authorization to be exposed
	    config.setExposedHeaders(Arrays.asList("Authorization", "cookieName", "headerName"));



	    return source;
	}


}
