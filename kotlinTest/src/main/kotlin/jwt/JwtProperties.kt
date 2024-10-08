package org.example.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "com.jwt")
class JwtProperties {
     var userSecretKey: String? = null
     var userToenName: String? = null
     var userTtl: Long? = null

     constructor()
     constructor(userSecretKey: String?, userToenName: String?, userTtl: Long?) {
         this.userSecretKey = userSecretKey
         this.userToenName = userToenName
         this.userTtl = userTtl
     }
}