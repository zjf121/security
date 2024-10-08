package org.example.provider

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

class JwtAuthenticationProvider: AuthenticationProvider {

    @Autowired
    lateinit var userDetailsService: UserDetailsService
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    override fun authenticate(authentication: Authentication?): Authentication {
        var username = authentication!!.principal as String
        var password = authentication.credentials as String
        var userDetail: UserDetails = userDetailsService.loadUserByUsername(username)
        if (passwordEncoder.matches(password, userDetail.password)) {
            return UsernamePasswordAuthenticationToken(userDetail.username, userDetail.password, userDetail.authorities)
        } else {
            throw RuntimeException("密码错误")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return  authentication!!.equals(UsernamePasswordAuthenticationToken::class.java)
    }
}