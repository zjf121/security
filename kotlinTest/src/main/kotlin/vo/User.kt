package org.example.vo

import org.springframework.stereotype.Component

@Component
data class User(
                var name: String?=null,
                var id: Int? = null,
                var age: Int? = null,
                var password: String? = null,
                var address: String? = null) {

    constructor(name: String?, age: Int?, password: String?, address: String?) : this() {
        this.name = name
        this.age = age
        this.password = password
        this.address = address
    }
}