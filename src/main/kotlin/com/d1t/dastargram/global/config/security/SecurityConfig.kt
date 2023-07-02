package com.d1t.dastargram.global.config.security

import com.d1t.dastargram.global.common.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            httpBasic { disable() }
            formLogin { disable() }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            exceptionHandling {
                authenticationEntryPoint
                accessDeniedHandler
            }
            authorizeRequests {
                authorize(anyRequest, permitAll)
            }
            headers {
                frameOptions { sameOrigin }
            }
        }
        http.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }


//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//                .csrf { it.disable() }
//                .httpBasic { it.disable() }
//                .formLogin { it.disable() }
//                .addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
//                .sessionManagement {
//                    it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                }
//                .exceptionHandling {
//                    it.authenticationEntryPoint()
//                    it.accessDeniedHandler()
//                }
//                .authorizeHttpRequests {
//                    it.requestMatchers("/api/auth/**").authenticated()
//                    it.anyRequest().permitAll()
//                }
//                .headers {
//                    it.frameOptions { fo ->
//                        fo.sameOrigin()
//                    }
//                }
//
//        return http.build()
//    }
}