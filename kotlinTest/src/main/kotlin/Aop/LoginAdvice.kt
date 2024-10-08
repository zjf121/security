package org.example.Aop

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.example.vo.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

/*
@Aspect
@Component
class LoginAdvice{
    // 配置织入点
    @Pointcut("execution(* org.example.controller.UserController.show())")
    fun loginPointCut() {
    }

    @Before("loginPointCut()")
    fun before(){
        var user:User = SecurityContextHolder.getContext().authentication.principal as User
        if (user.status == 0) {
            throw RuntimeException("用户未登录")
        }
    }
}*/
