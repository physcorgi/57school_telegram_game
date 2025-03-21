package com.example.blank.service

import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import com.example.blank.dto.TopicDto
import com.example.blank.dto.toEntity
import com.example.blank.repository.TopicRepository
import com.example.blank.entity.TopicEntity
import com.example.blank.entity.updateTimestamp

@Service
class TopicService(
    private val topicRepository: TopicRepository
) {

    fun addTopic(topic: TopicDto) {
        topicRepository.save(topic.toEntity())
    }

//    fun getTopicByTopicId(topicId: Long): TopicEntity {
//        return topicRepository.findByTopicId(topicId) ?: throw TopicNotFoundException("Topic with topicId $topicId not found")
//    }

    fun getTopicByTopicId(topicId: Long): TopicEntity {
        return topicRepository.findById(topicId)
            .orElseThrow { TopicNotFoundException("Topic with topicId $topicId not found") }
    }

    fun getTopicByName(name: String): TopicEntity {
        return topicRepository.findByName(name) ?: throw TopicNotFoundException("Topic with name $name not found")
    }

    fun getAllTopicsByCreatedBy(createdBy: String): List<TopicEntity> {
        return topicRepository.findAllByCreatedBy(createdBy) ?: throw TopicNotFoundException("No topics created by $createdBy found")
    }

//    @Transactional
//    fun deleteTopicByTopicId(topicId: Long): TopicEntity {
//        val topic = topicRepository.findByTopicId(topicId) ?: throw TopicNotFoundException("Topic with topicId $topicId not found")
//        topicRepository.deleteByTopicId(topicId)
//        return topic
//    }

    @Transactional
    fun deleteTopicByTopicId(topicId: Long): Boolean {
        return if (topicRepository.existsById(topicId)) {
            topicRepository.deleteById(topicId)
            true
        } else {
            false
        }
    }

//    @Transactional
//    fun deleteTopicByName(name: String): TopicEntity {
//        val topic = topicRepository.findByName(name) ?: throw TopicNotFoundException("Topic with name $name not found")
//        topicRepository.deleteByName(name)
//        return topic
//    }

    @Transactional
    fun deleteTopicByName(name: String): Boolean {
        return if (topicRepository.existsByName(name)) {
            topicRepository.deleteByName(name)
            true
        } else {
            false
        }
    }

//    @Transactional
//    fun deleteTopicByCreatedBy(createdBy: String): List<TopicEntity> {
//        val topics = topicRepository.findAllByCreatedBy(createdBy) ?: throw TopicNotFoundException("No topics created by $createdBy found")
//
//        if (topics.isEmpty()) {
//            throw TopicNotFoundException("No topics created by $createdBy found")
//        }
//
//        topicRepository.deleteAllByCreatedBy(createdBy)
//        return topics
//    }

    @Transactional
    fun deleteAllTopicsByCreatedBy(createdBy: String): Boolean {
        return if (topicRepository.existsByCreatedBy(createdBy)) {
            topicRepository.deleteAllByCreatedBy(createdBy)
            true
        } else {
            false
        }
    }

//    @Transactional
//    fun updateTopicName(topicId: Long, newName: String): TopicEntity {
//        val topic = topicRepository.findByTopicId(topicId) ?: throw TopicNotFoundException("Topic with topicId $topicId not found")
//        topic.name = newName
//        topic.updateTimestamp()
//        return topicRepository.save(topic)
//    }

    @Transactional
    fun updateTopicName(topicId: Long, newName: String): TopicEntity {
        val topic = topicRepository.findById(topicId)
            .orElseThrow { TopicNotFoundException("Topic with topicId $topicId not found") }
        topic.name = newName
        topic.updateTimestamp()
        return topicRepository.save(topic)
    }

//    @Transactional
//    fun updateTopicDescription(topicId: Long, newDescription: String): TopicEntity {
//        val topic = topicRepository.findByTopicId(topicId) ?: throw TopicNotFoundException("Topic with topicId $topicId not found")
//        topic.description = newDescription
//        topic.updateTimestamp()
//        return topicRepository.save(topic)
//    }

    @Transactional
    fun updateTopicDescription(topicId: Long, newDescription: String): TopicEntity {
        val topic = topicRepository.findById(topicId)
            .orElseThrow { TopicNotFoundException("Topic with topicId $topicId not found") }
        topic.description = newDescription
        topic.updateTimestamp()
        return topicRepository.save(topic)
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class TopicNotFoundException(message: String) : RuntimeException(message)