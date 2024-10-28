package com.swcontest.somding.common.config

import com.swcontest.somding.jwt.JwtAuthenticationFilter
import com.swcontest.somding.jwt.JwtExceptionFilter
import com.swcontest.somding.jwt.JwtManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SpringSecurityConfig(
        private val authenticationConfiguration: AuthenticationConfiguration,
        private val jwtManager: JwtManager,
        private val jwtExceptionFilter: JwtExceptionFilter,
) {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web ->
                web.ignoring().requestMatchers(
                        "/swagger-ui/**", "/swagger/**", "/swagger-resources/**", "/swagger-ui.html",
                        "/configuration/ui", "/v3/api-docs/**", "/h2-console/**",  "/api/auth/login",
                        "/api/auth/signup"
                )
        }
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .csrf { csrf -> csrf.disable() }
                .formLogin { formLogin -> formLogin.disable() }
                .sessionManagement { sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                }
                .addFilter(JwtAuthenticationFilter(authenticationManager(), jwtManager))
                .authorizeHttpRequests { authorizeRequests ->
                    authorizeRequests
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth/**")).authenticated()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                            .anyRequest().authenticated()
                }
                .headers { headersConfigurer ->
                    headersConfigurer.frameOptions { it.sameOrigin() }
                }
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter::class.java)
        return http.build()
    }
}
