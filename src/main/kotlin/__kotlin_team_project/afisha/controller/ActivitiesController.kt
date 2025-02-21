package __kotlin_team_project.afisha.controller

import jdk.jfr.Event
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/events")
class ActivitiesController(private val eventRepository: EventRepository) {

    // Получить все события
    @GetMapping("/")
    fun getAllEvents(): ResponseEntity<List<Event>> {
        return ResponseEntity(eventRepository.findAll(), HttpStatus.OK)
    }

    // Поиск событий по названию
    @GetMapping("/search/by-name")
    fun searchByName(@RequestParam name: String): ResponseEntity<List<Event>> {
        val events = eventRepository.findByNameContainingIgnoreCase(name)  // Поиск по части названия
        return if (events.isNotEmpty()) {
            ResponseEntity.ok(events)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Поиск событий по категории
    @GetMapping("/search/by-category")
    fun searchByCategory(@RequestParam category: String): ResponseEntity<List<Event>> {
        val events = eventRepository.findByCategoryContainingIgnoreCase(category)  // Поиск по категории
        return if (events.isNotEmpty()) {
            ResponseEntity.ok(events)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}