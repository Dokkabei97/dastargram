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

    /**
     * Spring Security Kotlin DSL 작성방법
     * import org.springframework.security.config.annotation.web.invoke 해당 패키지를 import 해야함
     * 공식문서에서는 다른 패키지로 표기되어 있으니 조심
     * 공식문서에도 IDE가 import를 자동으로 해주지 않음
     */
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

    /**
     * Spring Security Kotlin 작성방법
     * Kotlin에서 기존 Java 처럼 http.csrf() 사용시 depreacted로 표시됨
     */

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