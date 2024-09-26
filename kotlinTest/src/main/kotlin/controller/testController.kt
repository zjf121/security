package org.example.controller

import org.example.service.UserService
import org.example.vo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class testController(
    val userService: UserService
) {
    fun test(name : String): String {
        return name + "我是张三"
    }
    fun test1(x1: Int , x2: String, test:(String)->String):String{
        var user1: User;
        var usercontt: UserController;

        return test(x2)
    }

    var result = test1(1, "zhangsan", ::test)
}