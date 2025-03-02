package com.example.blank.service

import java.nio.file.Files
import java.nio.file.Paths

class RulesService {
    fun loadRules(): String {
        val path = Paths.get("src/main/resources/texts/rules.txt")
        return Files.readString(path)
    }

}