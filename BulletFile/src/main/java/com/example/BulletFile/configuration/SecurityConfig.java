package com.example.BulletFile.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(
                    "/files",               // your custom login page
                    "/files/download/{id}",
                    "/files/share/{id}",
                    "/style/**",
                    "/logout",
                    "/logout-success"
            ).permitAll();
            auth.anyRequest().authenticated();
        })
       .oauth2Login(oauth2Login -> oauth2Login
        .loginPage("/files") // Your login page
        .successHandler(customSuccessHandler())
)
.logout(logout -> logout
    .logoutUrl("/logout")
    .logoutSuccessHandler((request, response, authentication) -> {
    if (request.getSession(false) != null) {
        request.getSession().invalidate();
    }
    SecurityContextHolder.clearContext();
    response.addHeader("Set-Cookie", "JSESSIONID=; Path=/; Max-Age=0; HttpOnly; SameSite=Lax");
    response.sendRedirect("/files"); // back to login page
})

    .permitAll()
)

        .csrf(csrf -> csrf.disable()); // ⚠️ Enable in production

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        SimpleUrlAuthenticationSuccessHandler successHandler =
                new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/files/home"); // Redirect after successful login
        return successHandler;
    }
}
