package com.example.blank.dto

import io.swagger.v3.oas.annotations.media.Schema
import com.example.blank.entity.UserTestResultEntity
import java.time.LocalDateTime

class UserTestResultDto(
    @Schema(
        description = "Идентификатор пользователя",
    )
    val userId: Long,

    @Schema(
        description = "Идентификатор теста",
    )
    val testId: Long,

    @Schema(
        description = "Результат теста (количество баллов)",
    )
    val score: Int,

    @Schema(
        description = "Дата и время завершения теста",
    )
    val completedAt: LocalDateTime = LocalDateTime.now()
)

fun UserTestResultDto.toEntity() = UserTestResultEntity(
    userId = userId,
    testId = testId,
    score = score,
    completedAt = completedAt
)