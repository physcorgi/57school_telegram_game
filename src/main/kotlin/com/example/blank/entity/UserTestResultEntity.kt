package com.example.blank.entity

import com.example.blank.dto.UserTestResultDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "User_Test_Results",
    indexes = [
        Index(columnList = "id", name = "user_test_result_id_hidx"),
        Index(columnList = "user_id", name = "user_id_hidx"),
        Index(columnList = "test_id", name = "test_id_hidx")
    ]
)
class UserTestResultEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "test_id", nullable = false)
    val testId: Long,

    @Column(nullable = false)
    val score: Int,

    @Column(name = "completed_at", nullable = false)
    val completedAt: LocalDateTime = LocalDateTime.now()
)

fun UserTestResultEntity.toDto() = UserTestResultDto(
    userId = userId,
    testId = testId,
    score = score,
    completedAt = completedAt
)