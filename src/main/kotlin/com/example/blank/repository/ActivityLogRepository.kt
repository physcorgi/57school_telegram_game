package com.example.blank.repository

import com.example.blank.entity.ActivityLogEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

//import eu.vendeli.tgbot.types.User

@Repository
interface ActivityLogRepository : JpaRepository<ActivityLogEntity, Long> {
    fun findByActivityLogId(activityLogId: Long): ActivityLogEntity?
    fun findAllByUserId(userId: Long): ActivityLogEntity?
    fun findAllByAction(action: String): List<ActivityLogEntity>?
    fun deleteByActivityLogId(activityLogId: Long): ActivityLogEntity?
    fun deleteAllByUserId(userId: Long): ActivityLogEntity?
    fun deleteAllByAction(action: String): List<ActivityLogEntity>?

    @Modifying
    @Query("DELETE FROM ActivityLogEntity a WHERE a.createdAt < :date")
    fun deleteAllByCreatedAtBefore(date: LocalDateTime): List<ActivityLogEntity>?
}
