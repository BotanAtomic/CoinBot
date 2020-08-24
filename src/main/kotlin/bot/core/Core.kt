package bot.core

import bot.api.*
import bot.channels.GenericChannel
import bot.connectors.GenericConnector
import bot.entity.User
import bot.helper.loadClasses
import bot.messages.CommandHandler
import bot.messages.MessageData

class Core {

    val channels = loadClasses<GenericChannel>("bot.channels.impl", Channel::class, this)
    private val connectors = loadClasses<GenericConnector>("bot.connectors.impl", Connector::class)
    private val applications = loadClasses<Service>("bot.app", Application::class)
    private val messages = loadClasses<CommandHandler>("bot.messages.impl", Message::class).associateBy {
        it::class.java.getAnnotationsByType(Message::class.java).first().header
    }.toMap()

    init {
        applications.forEach { it.start(this) }
        println(
            "Successfully loaded ${channels.size} channels, ${connectors.size} connectors," +
                    " ${applications.size} applications, ${messages.size} messages"
        )
    }

    fun onReceiveCommand(message: String, user: User, source: String, channel: GenericChannel) {
        val command = message.split(" ").map { it.trim() }
        val header = command[0].toLowerCase()

        if (messages.containsKey(header)) {
            messages[header]?.handle(
                MessageData(header, command.filterIndexed { i, _ -> i > 0 }, user, source),
                channel
            )
        } else {
            channel.send(
                "Sorry {user:${user.id}}, '${command[0]}' is not a CoinBot command. See 'B --help'.",
                source
            )
        }
    }

}