package org.example.dao

import com.baomidou.mybatisplus.extension.service.IService
import org.example.vo.User
import org.springframework.stereotype.Repository


interface UserDao: IService<User> {
}