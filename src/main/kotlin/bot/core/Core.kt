package bot.core

import bot.api.*
import bot.channels.GenericChannel
import bot.connectors.GenericConnector
import bot.entity.User
import bot.helper.fullTrim
import bot.helper.loadClasses
import bot.messages.CommandHandler
import bot.messages.MessageData

class Core {

    val channels = loadClasses<GenericChannel>("bot.channels.impl", Channel::class, this)
    private val connectors = loadClasses<GenericConnector>("bot.connectors.impl", Connector::class)
    private val applications = loadClasses<Service>("bot.app", Application::class)
    private val messages = loadClasses<CommandHandler>("bot.messages.impl", Message::class).associateBy {
        it::class.java.getAnnotation(Message::class.java).value
    }.toMap()

    init {
        applications.forEach { it.start(this) }
        println(
            "Successfully loaded ${channels.size} channels, ${connectors.size} connectors," +
                    " ${applications.size} applications, ${messages.size} messages"
        )
    }

    fun onReceiveCommand(message: String, user: User, source: String, channel: GenericChannel) {
        val command = message.fullTrim().split(" ").map { it.fullTrim() }
        val header = command[0].toLowerCase()

        if (messages.containsKey(header)) {
            messages[header]?.handle(
                MessageData(header, command.filterIndexed { i, _ -> i > 0 }, user, source),
                channel
            )
        } else {
            channel.send(
                "Sorry {user:${user.id}}, '${header}' is not a CoinBot command. See 'J --help'.",
                source
            )
        }
    }

}