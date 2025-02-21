package kotlin_team_project.afisha.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController {

    private val users = mutableMapOf<Long, String>() // временное хранилище пользователей

    @PostMapping("/register")
    // нужен id пользователя и как к нему обращаться (пример id = 777 username = 18yearsoldyandexcloudmiddlebackenddeveloper. FCS+CU 28')
    fun register(@RequestParam userId: Long, @RequestParam username: String): ResponseEntity<String> {
        if (users.containsKey(userId)) {
            return ResponseEntity.badRequest().body("User with that ID already exists")
        }
        users[userId] = username
        return ResponseEntity.ok("User successfully registered")
    }

}

/*
когда репу напишут...
package kotlin_team_project.afisha.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val userRepository: UserRepository) {

    @PostMapping("/register")
    // нужен id пользователя и как к нему обращаться (пример id = 777 username = 18yearsoldyandexcloudmiddlebackenddeveloper. FCS+CU 28')
    fun register(@RequestParam userId: Long, @RequestParam username: String): ResponseEntity<String> {
        if (userRepository.existsById(userId)) {
            return ResponseEntity("User with that ID already exists")
        }
        val user = User(userId, username)
        userRepository.save(user)
        return ResponseEntity.ok("User successfully registered")
    }
}
*/