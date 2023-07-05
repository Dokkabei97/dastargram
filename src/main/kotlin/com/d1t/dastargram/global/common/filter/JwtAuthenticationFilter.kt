package com.d1t.dastargram.global.common.filter

import com.d1t.dastargram.global.common.utils.AccessTokenProvider
import com.d1t.dastargram.global.common.utils.RefreshTokenProvider
import io.jsonwebtoken.IncorrectClaimException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
        val accessTokenProvider: AccessTokenProvider,
        val refreshTokenProvider: RefreshTokenProvider
) : OncePerRequestFilter() {

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        val accessToken = resolveToken(request)

        runCatching {
            if (accessToken != null && accessTokenProvider.validateToken(accessToken)) {
                val authentication = accessTokenProvider.getAuthentication(accessToken)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }.getOrElse { e ->
            when (e) {
                is IncorrectClaimException -> {
                    SecurityContextHolder.clearContext()
                    response.sendError(403)
                }
                is UsernameNotFoundException -> {
                    SecurityContextHolder.clearContext()
                    response.sendError(403)
                }
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(httpServletRequest: HttpServletRequest): String? {
        val bearerToken = httpServletRequest.getHeader("Authorization")
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }
}