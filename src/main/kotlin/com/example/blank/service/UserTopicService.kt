package com.example.blank.service

import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import com.example.blank.dto.UserTopicDto
import com.example.blank.entity.updateTimestamp
import com.example.blank.dto.toEntity
import com.example.blank.repository.UserTopicRepository
import com.example.blank.entity.UserTopicEntity

@Service
class UserTopicService(
    val userTopicRepository: UserTopicRepository
) {

    fun addUserTopic(userTopic: UserTopicDto) {
        userTopicRepository.save(userTopic.toEntity())
    }

    fun getUserTopicById(userTopicId: Long): UserTopicEntity {
        return userTopicRepository.findByUserTopicId(userTopicId)
            ?: throw UserTopicNotFoundException("User topic with id $userTopicId not found")
    }

    fun getAllUserTopicsByUserId(userId: Long): List<UserTopicEntity> {
        return userTopicRepository.findAllByUserId(userId)
            ?: throw UserTopicNotFoundException("No topics found for user with id $userId")
    }

    fun getAllUserTopicsByTopicId(topicId: Int): List<UserTopicEntity> {
        return userTopicRepository.findAllByTopicId(topicId)
            ?: throw UserTopicNotFoundException("No topics found with topic id $topicId")
    }

    @Transactional
    fun deleteUserTopicById(userTopicId: Long): UserTopicEntity {
        val userTopic = userTopicRepository.findByUserTopicId(userTopicId)
            ?: throw UserTopicNotFoundException("User topic with id $userTopicId not found")
        userTopicRepository.deleteByUserTopicId(userTopicId)
        return userTopic
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserTopicNotFoundException(message: String) : RuntimeException(message)