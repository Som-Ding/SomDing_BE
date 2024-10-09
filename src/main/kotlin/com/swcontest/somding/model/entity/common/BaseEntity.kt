package com.swcontest.somding.model.entity.common

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
open class BaseEntity(
        @Column(nullable = false, updatable = false)
        val createdAt: LocalDateTime = LocalDateTime.now(),

        @Column(nullable = false)
        var updatedAt: LocalDateTime = LocalDateTime.now()
) {
        // No-argument constructor for JPA
        protected constructor() : this(LocalDateTime.now(), LocalDateTime.now())
}
