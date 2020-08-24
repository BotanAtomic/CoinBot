package bot.core

import bot.api.Application
import bot.api.Channel
import bot.api.Connector
import bot.api.Service
import bot.channels.GenericChannel
import bot.connectors.GenericConnector
import bot.entity.User
import bot.helper.loadClasses

class Core {

    val channels: List<GenericChannel> = loadClasses("bot.channels.impl", Channel::class, this)
    private val connectors: List<GenericConnector> = loadClasses("bot.connectors.impl", Connector::class)
    private val applications: List<Service> = loadClasses("bot.app", Application::class)

    init {
        applications.forEach { it.start(this) }
        println("Successfully loaded ${channels.size} channels, ${connectors.size} connectors, ${applications.size} applications")
    }

    fun onReceiveCommand(message: String, user: User, source: String) {

    }

}