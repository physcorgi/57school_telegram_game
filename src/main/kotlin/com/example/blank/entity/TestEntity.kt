package com.example.blank.entity

import com.example.blank.dto.TestDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "Tests",
    indexes = [
        Index(columnList = "id", name = "test_id_hidx"),
        Index(columnList = "content_type", name = "content_type_hidx")
    ]
)
class TestEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "content_type", nullable = false, length = 50)
    val contentType: String,

    @Column(nullable = true, length = 50)
    val difficulty: String?,

    @Column(nullable = false, columnDefinition = "jsonb")
    val questions: String,

    @Column(nullable = false, columnDefinition = "jsonb")
    val answers: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)

fun TestEntity.toDto() = TestDto(
    contentType = contentType,
    difficulty = difficulty,
    questions = questions,
    answers = answers,
    createdAt = createdAt
)