package org.example.controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import org.apache.tomcat.jni.Address
import org.example.dao.UserDao
import org.example.service.UserService
import org.example.vo.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sun.security.util.Password

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService,
private val userDao: UserDao) {


    //注册
    @PostMapping("/register")
    fun register(name: String, password: String, age: Int, address: String): ResultVo<Boolean> {
        val user = User().apply {
            this.name = name
            this.password = password
            this.age = age
            this.address = address
        }
        val user1 = User(name, age, password, address)
        return userService.register(user1)
    }

    //登录
    @GetMapping("/login")
    fun login(name: String, password: String): ResultVo<Any> {
        val wapper = QueryWrapper<User>().apply {
            eq("name", name)
            eq("password", password)
        }
        val wapper1: QueryWrapper<User> = QueryWrapper<User>().apply {
            eq("name", name)
        }
        var count = userDao.count(wapper)
        return if (count > 0) {
            ok(true, "登陆成功")
        } else {
            fail("用户名或密码错误")
        }
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

        val list = userDao.list(wrapper)
        return ok(list)

    }
}