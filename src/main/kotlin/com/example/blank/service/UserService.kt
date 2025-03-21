package com.example.blank.service

import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import com.example.blank.dto.UserDto
import com.example.blank.entity.updateTimestamp
import com.example.blank.dto.toEntity
import com.example.blank.repository.UserRepository
import com.example.blank.entity.UserEntity

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun addUser(user: UserDto) {
        userRepository.save(user.toEntity())
    }

//    fun getUserByUserId(userId: Long): UserEntity {
//        return userRepository.findByUserId(userId) ?: throw UserNotFoundException("User with userId $userId not found")
//    }

    fun getUserByUserId(userId: Long): UserEntity {
        return userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User with userId $userId not found") }
    }

    fun getUserByTelegramId(telegramId: Long): UserEntity {
        return userRepository.findByTelegramId(telegramId) ?: throw UserNotFoundException("User with telegramId $telegramId not found")
    }

    fun getAllUsersByRating(rating: Int): List<UserEntity> {
        return userRepository.findAllByRating(rating) ?: throw UserNotFoundException("No users with rating $rating found")
    }

    fun getAllUsersByStreak(streak: Int): List<UserEntity> {
        return userRepository.findAllByStreak(streak) ?: throw UserNotFoundException("No users with streak $streak found")
    }

//    @Transactional
//    fun deleteUserByUserId(userId: Long): UserEntity {
//        val user = userRepository.findByUserId(userId) ?: throw UserNotFoundException("User with userId $userId not found")
//        userRepository.deleteByUserId(userId)
//        return user
//    }

    @Transactional
    fun deleteUserByUserId(userId: Long): Boolean {
        return if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
            true
        } else {
            false
        }
    }

//    @Transactional
//    fun deleteUserByTelegramId(telegramId: Long): UserEntity {
//        val user = userRepository.findByTelegramId(telegramId) ?: throw UserNotFoundException("User with telegramId $telegramId not found")
//        userRepository.deleteByTelegramId(telegramId)
//        return user
//    }

    @Transactional
    fun deleteUserByTelegramId(telegramId: Long): Boolean {
        return if (userRepository.existsByTelegramId(telegramId)) {
            userRepository.deleteByTelegramId(telegramId)
            true
        } else {
            false
        }
    }

//    @Transactional
//    fun updateUserUsername(userId: Long, newUsername: String): UserEntity {
//        val user = userRepository.findByUserId(userId) ?: throw UserNotFoundException("User with userId $userId not found")
//        user.username = newUsername
//        user.updateTimestamp()
//        return userRepository.save(user)
//    }

    @Transactional
    fun updateUserUsername(userId: Long, newUsername: String): UserEntity {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User with userId $userId not found") }
        user.username = newUsername
        user.updateTimestamp()
        return userRepository.save(user)
    }

//    @Transactional
//    fun updateUserFullName(userId: Long, newFullName: String): UserEntity {
//        val user = userRepository.findByUserId(userId) ?: throw UserNotFoundException("User with userId $userId not found")
//        user.fullName = newFullName
//        user.updateTimestamp()
//        return userRepository.save(user)
//    }

    @Transactional
    fun updateUserFullName(userId: Long, newFullName: String): UserEntity {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User with userId $userId not found") }
        user.fullName = newFullName
        user.updateTimestamp()
        return userRepository.save(user)
    }

//    @Transactional
//    fun updateUserProfileData(userId: Long, newProfileData: String): UserEntity {
//        val user = userRepository.findByUserId(userId) ?: throw UserNotFoundException("User with userId $userId not found")
//        user.profileData = newProfileData
//        user.updateTimestamp()
//        return userRepository.save(user)
//    }

    @Transactional
    fun updateUserProfileData(userId: Long, newProfileData: String): UserEntity {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User with userId $userId not found") }
        user.profileData = newProfileData
        user.updateTimestamp()
        return userRepository.save(user)
    }

//    @Transactional
//    fun updateUserRating(userId: Long, newRating: Int): UserEntity {
//        val user = userRepository.findByUserId(userId) ?: throw UserNotFoundException("User with userId $userId not found")
//        user.rating = newRating
//        user.updateTimestamp()
//        return userRepository.save(user)
//    }

    @Transactional
    fun updateUserRating(userId: Long, newRating: Int): UserEntity {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User with userId $userId not found") }
        user.rating = newRating
        user.updateTimestamp()
        return userRepository.save(user)
    }

//    @Transactional
//    fun updateUserStreak(userId: Long, newStreak: Int): UserEntity {
//        val user = userRepository.findByUserId(userId) ?: throw UserNotFoundException("User with userId $userId not found")
//        user.streak = newStreak
//        user.updateTimestamp()
//        return userRepository.save(user)
//    }

    @Transactional
    fun updateUserStreak(userId: Long, newStreak: Int): UserEntity {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException("User with userId $userId not found") }
        user.streak = newStreak
        user.updateTimestamp()
        return userRepository.save(user)
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(message: String) : RuntimeException(message)