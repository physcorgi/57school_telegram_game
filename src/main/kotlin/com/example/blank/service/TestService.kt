package com.example.blank.service

import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import com.example.blank.dto.TestDto
import com.example.blank.dto.toEntity
import com.example.blank.repository.TestRepository
import com.example.blank.entity.TestEntity
import com.example.blank.entity.updateTimestamp

@Service
class TestService(
    val TestRepository: TestRepository
) {

    fun addTest(test: TestDto) {
        TestRepository.save(test.toEntity())
    }

    fun getTestByTestId(testId: Long): TestEntity {
        return TestRepository.findByTestId(testId) ?: throw TestNotFoundException("Test with testId $testId not found")
    }

    fun getAllTestsByContentType(contentType: String): List<TestEntity> {
        return TestRepository.findAllByContentType(contentType) ?: throw TestNotFoundException("No tests with contentType $contentType found")
    }

    fun getAllTestsByDifficulty(difficulty: String): List<TestEntity> {
        return TestRepository.findAllByDifficulty(difficulty) ?: throw TestNotFoundException("No tests with difficulty $difficulty found")
    }

    @Transactional
    fun deleteTestByTestId(testId: Long): TestEntity {
        val test = TestRepository.findByTestId(testId) ?: throw TestNotFoundException("No test with testId $testId found")
        TestRepository.deleteByTestId(testId)
        return test
    }

    @Transactional
    fun deleteTestByContentType(contentType: String): List<TestEntity> {
        val tests = TestRepository.findAllByContentType(contentType) ?: throw TestNotFoundException("No test with contentType $contentType found")

        if (tests.isEmpty()) {
            throw TestNotFoundException("No tests with contentType $contentType found")
        }

        TestRepository.deleteByContentType(contentType)
        return tests
    }

    @Transactional
    fun deleteTestByDifficulty(difficulty: String): List<TestEntity> {
        val tests = TestRepository.findAllByDifficulty(difficulty) ?: throw TestNotFoundException("No tests with difficulty $difficulty found")

        if (tests.isEmpty()) {
            throw TestNotFoundException("No tests with difficulty $difficulty found")
        }

        TestRepository.deleteByDifficulty(difficulty)
        return tests
    }

    @Transactional
    fun updateTestDifficulty(testId: Long, newDifficulty: String): TestEntity {
        val test = TestRepository.findByTestId(testId) ?: throw TestNotFoundException("No test with testId $testId found")
        test.difficulty = newDifficulty
        test.updateTimestamp()
        return TestRepository.save(test)
    }

    @Transactional
    fun updateTestQuestions(testId: Long, newQuestions: String): TestEntity {
        val test = TestRepository.findByTestId(testId) ?: throw TestNotFoundException("Test with testId $testId not found")
        test.questions = newQuestions
        test.updateTimestamp()
        return TestRepository.save(test)
    }

    @Transactional
    fun updateTestAnswers(testId: Long, newAnswers: String): TestEntity {
        val test = TestRepository.findByTestId(testId) ?: throw TestNotFoundException("Test with testId $testId not found")
        test.answers = newAnswers
        test.updateTimestamp()
        return TestRepository.save(test)
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class TestNotFoundException(message: String) : RuntimeException(message)