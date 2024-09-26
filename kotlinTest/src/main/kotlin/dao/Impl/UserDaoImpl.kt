package org.example.dao.Impl

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.example.dao.UserDao
import org.example.mapper.UserMapper
import org.example.service.Impl.UserServiceImpl
import org.example.service.UserService
import org.example.vo.User
import org.springframework.stereotype.Repository

@Repository
open class UserDaoImpl:ServiceImpl<UserMapper,User>() ,UserDao {
}