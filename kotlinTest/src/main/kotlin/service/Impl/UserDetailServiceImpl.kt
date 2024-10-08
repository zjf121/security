package org.example.service.Impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.example.dao.UserDao
import org.example.vo.User
import org.example.vo.fail
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(
    private val userDao: UserDao,
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        //根据用户名查询用户信息
        var user = userDao.getOne(KtQueryWrapper(User::class.java).eq(User::name, username))
        if (user == null) {
            throw RuntimeException("用户名不存在")
        }
        val userDetails: UserDetails = org.springframework.security.core.userdetails.User
            .withUsername(user.name)
            .password(user.password)
            .authorities("admin")
            .build()
        return userDetails
    }
}