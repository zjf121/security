package org.example.filter

import cn.hutool.jwt.JWTUtil
import org.example.jwt.JwtProperties
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import java.util.Objects
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/*
@Component
open class JwtAuthenticationTokenFilter(
    private val userDao: UserDao,
    private val jwtProperties: JwtProperties
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var token: String
        if(!StringUtils.hasText(request.getHeader("token"))) {
            filterChain.doFilter(request, response)
            return
        }
        token = request.getHeader("token")
        var userInfo: UserDetails
        try {
            //TODO:解构问题
            var claims: Claims = JwtUtils.parseJWT(jwtProperties.userSecretKey,token)
            var any: Any? = claims.get("authentication")
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("token非法")
        }
        */
/*var user = userDao.getOne(KtQueryWrapper(org.example.vo.User::class.java).eq(org.example.vo.User::name,userInfo.username))
        if(user.status == 0){
            throw RuntimeException("用户未登录")
        }
        var authenticationToken = UsernamePasswordAuthenticationToken(user,null,null)
        SecurityContextHolder.getContext().setAuthentication(authenticationToken)*//*


        filterChain.doFilter(request, response)
    }
}*/
open class JwtAuthenticationTokenFilter: OncePerRequestFilter(){
    companion object{
        private const val AUTH_HEADER = "Authorization"
    }

    @Autowired
    lateinit var userDetailsService: UserDetailsService
    @Autowired
    lateinit var jwtProperties: JwtProperties
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        //从请求头中获取token
        var header: String? = request.getHeader(AUTH_HEADER)
        if (Objects.isNull(header)) {
            //用户未登录
            filterChain.doFilter(request, response)
            return
        }
        var token: String? = header
        //验证token
        if (!JWTUtil.verify(token, jwtProperties.userSecretKey!!.toByteArray(Charsets.UTF_8))) {
            filterChain.doFilter(request,response)
            return
        }

        val username: String = JWTUtil.parseToken(token).getPayload("username") as String
        var userDetail: UserDetails = userDetailsService.loadUserByUsername(username)

        var authenticationToken = UsernamePasswordAuthenticationToken(userDetail.username,userDetail.password,userDetail.authorities)
        authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

        //保存到环境上下文
        SecurityContextHolder.getContext().authentication = authenticationToken
        println(SecurityContextHolder.getContext())
        filterChain.doFilter(request, response)
    }

}
