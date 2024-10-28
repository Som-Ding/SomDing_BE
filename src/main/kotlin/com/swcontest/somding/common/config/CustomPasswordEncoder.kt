package com.swcontest.somding.common.config

import lombok.extern.slf4j.Slf4j
import org.mindrot.jbcrypt.BCrypt
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
@Slf4j
class CustomPasswordEncoder : PasswordEncoder {
    override fun encode(rawPassword: CharSequence): String {
        requireNotNull(rawPassword) { "rawPassword cannot be null" }
        val salt = BCrypt.gensalt()
        return BCrypt.hashpw(rawPassword.toString(), salt)
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        require(!(rawPassword == null || encodedPassword == null)) { "rawPassword and encodedPassword cannot be null" }
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword)
    }
}
