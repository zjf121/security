package org.example.vo

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
@TableName("user")
data class User(
    @TableId(value = "id")
    var id: Int? = null,
    var name: String? = null,
    var age: Int? = null,
    var password: String? = null,
    var address: String? = null,
    var status: Int? = 0,
    var role: String? = "user"
) {

    constructor(name: String?, age: Int?, password: String?, address: String?) : this() {
        this.name = name
        this.age = age
        this.password = password
        this.address = address
    }
}