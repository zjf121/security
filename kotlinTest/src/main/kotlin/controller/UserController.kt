package org.example.controller

import cn.hutool.jwt.JWT
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.example.dao.UserDao
import org.example.jwt.JwtProperties
import org.example.service.UserService
import org.example.vo.ResultVo
import org.example.vo.User
import org.example.vo.ok
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val userDao: UserDao,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
) {

    @Autowired
    private lateinit var jwtProperties: JwtProperties


    //注册
    @PostMapping("/register")
    fun register(user: User): ResultVo<Any> {
        return userService.register(user)
    }

    //登录
    @GetMapping("/login")
    fun login(name: String, password: String): ResultVo<Any> {
        /*var wrapper = QueryWrapper<User>().apply {
            eq("name",name)
        }
        var user = userDao.getOne(wrapper)
        if (user == null) {
            return fail("该用户不存在")
        }
        if (passwordEncoder!!.matches(password, user.password)){
            //密码校验成功
            user.status = 1
            var set = UpdateWrapper<User>()
                .eq("name", user.name)
                .set("status", user.status)

            KtQueryWrapper(User::class.java).apply {
                eq(User::name, user.name)
            }
            userDao.update(set)
            return ok(user)
        }else {
            //密码错误
            return fail("密码错误")
        }*/
        // 使用security，用户提交用户名和密码，通过提供的类，将用户信息封装
        var usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(name, password)
        //校验用户名和密码是否正确，然后生成用户凭据（内部重写UserDetailsService的用户名密码校验逻辑）
        var authenticate: Authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        if (Objects.isNull(authenticate)) {
            throw RuntimeException("用户名或密码错误")
        }
        //校验成功造token
        var token: String = JWT.create()
            .setPayload("username", name)
            .setKey(jwtProperties.userSecretKey!!.toByteArray(Charsets.UTF_8))
            .sign()
        println("token:$token")
        return ok(token)
    }

    //查询展示数据
    @GetMapping("/show")
    fun show(user: User): ResultVo<Any> {
        var wrapper = QueryWrapper<User>().apply {
            like(user.name != null, "name", user.name)
            like(user.address != null, "address", user.address)
            eq(user.age != null, "age", user.age)
            eq("1", "1")
        }
        /*KtQueryWrapper(User::class.java).apply {
            like(user.name != null,User::name, user.name)
            like(user.address != null,User::address, user.address)
            eq(user.age != null,User::age, user.age)
            eq(1,1)
        }*/
        val list = userDao.list(wrapper)
        if (list.isEmpty()) {
            return ok(Collections.emptyList<String>())
        }
        return ok(list)

    }
}