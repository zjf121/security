package org.example.service.Impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.example.dao.UserDao
import org.example.mapper.UserMapper
import org.example.service.UserService
import org.example.vo.ResultVo
import org.example.vo.User
import org.example.vo.ok
import org.springframework.stereotype.Service

@Service
open class UserServiceImpl(
    private val userDao: UserDao
): UserService, ServiceImpl<UserMapper, User>() {
    override fun register(user:User): ResultVo<Boolean> {
        //实现注册
        return ok(userDao.save(user))
    }
}