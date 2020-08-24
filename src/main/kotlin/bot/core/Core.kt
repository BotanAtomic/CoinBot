package bot.core

import bot.api.Application
import bot.api.Channel
import bot.api.Connector
import bot.api.Service
import bot.channels.GenericChannel
import bot.connectors.GenericConnector
import bot.helper.loadClasses

class Core {

    val channels: List<GenericChannel> = loadClasses("bot.channels.impl", Channel::class.java)
    val connectors: List<GenericConnector> = loadClasses("bot.connectors.impl", Connector::class.java)
    val applications: List<Service> = loadClasses("bot.app", Application::class.java)

    init {
        println("Successfully loaded ${channels.size} channels, ${connectors.size} connectors, ${applications.size} applications")
    }

}