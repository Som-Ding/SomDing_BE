package com.swcontest.somding.common.config.s3

import jakarta.persistence.*
import lombok.*

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class Uuid(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "uuid_id", nullable = false)
        val id: Long? = null,

        @Column(unique = true, nullable = false)
        val uuid: String
)
