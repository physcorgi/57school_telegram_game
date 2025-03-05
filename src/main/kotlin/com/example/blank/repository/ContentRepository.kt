package com.example.blank.repository

import com.example.blank.entity.ContentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
//import eu.vendeli.tgbot.types.User

@Repository
interface ContentRepository : JpaRepository<ContentEntity, Long> {
    fun findByContentId(contentId: Long): ContentEntity?
    fun findAllByTopicId(topicId: Long): ContentEntity?
    fun findAllByType(type: String): List<ContentEntity>?
    fun deleteByContentId(contentId: Long): ContentEntity?
    fun deleteAllByTopicId(topicId: Long): ContentEntity?
    fun deleteAllByType(type: String): List<ContentEntity>?
}
