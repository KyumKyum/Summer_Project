package com.spproject.sp_backend.model

import jakarta.persistence.*
import java.util.UUID

@Entity(name = "Users")
@Table(name = "Users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false)
    val ident: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val keyVal: String,
)