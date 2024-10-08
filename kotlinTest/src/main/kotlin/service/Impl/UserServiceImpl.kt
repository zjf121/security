package org.example.service.Impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.example.dao.UserDao
import org.example.mapper.UserMapper
import org.example.service.UserService
import org.example.vo.ResultVo
import org.example.vo.User
import org.example.vo.fail
import org.example.vo.ok
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
open class UserServiceImpl(
    private val userDao: UserDao,
    private val passwordEncoder: PasswordEncoder
): UserService, ServiceImpl<UserMapper, User>() {
    override fun register(user:User): ResultVo<Any> {
        //查询该用户
        var wrapper = QueryWrapper<User>().apply {
            eq("name",user.name)
        }

        if(userDao.count(KtQueryWrapper(User::class.java).eq(User::name, user.name)) > 0){
            return fail("该用户已存在")
        }
        //密码加密
        var password = passwordEncoder?.encode(user.password)
        user.password = password
        user.status = 0
        //实现注册
        return ok(userDao.save(user))
    }
}