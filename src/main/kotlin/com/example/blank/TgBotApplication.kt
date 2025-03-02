package com.example.blank

import eu.vendeli.tgbot.TelegramBot

suspend fun main() {
    val bot = TelegramBot("")
    // And that'd all write your own bot from blank.
    // Create new controllers in the controller folder or expand old one

    bot.handleUpdates()
}
