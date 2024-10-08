package org.example.service

import com.sun.org.apache.xpath.internal.operations.Bool
import org.example.vo.ResultVo
import org.example.vo.User
import org.springframework.stereotype.Service

interface UserService {
    fun register(user: User): ResultVo<Any>
}