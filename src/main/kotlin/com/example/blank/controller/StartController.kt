package com.example.blank.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.internal.ProcessedUpdate
import java.nio.file.Files
import java.nio.file.Paths


fun loadRules(): String {
    val path = Paths.get("src/main/resources/texts/rules.txt")
    return Files.readString(path)
}


class StartController {
    @CommandHandler(["/start"])
    suspend fun start(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        message{ "Test" }.inlineKeyboardMarkup {
            callbackData("Правила"){"rules"}
            callbackData("Настройки"){"settings"}
            callbackData("О проекте"){"help"}
            callbackData("Профиль"){"about"}
        }.send(user, bot)
    }
    @CommandHandler.CallbackQuery(["rules"])
    suspend fun rules(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        message{
            loadRules()
        }.send(user, bot)


    }

}
