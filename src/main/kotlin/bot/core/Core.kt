package bot.core

import bot.api.*
import bot.channels.GenericChannel
import bot.connectors.GenericConnector
import bot.entity.User
import bot.helper.fullTrim
import bot.helper.loadClasses
import bot.messages.CommandHandler
import bot.messages.MessageData

const val BTC = "BTC"
const val USDT = "USDT"

class Core {

    val tempDataBase: MutableMap<String, Any> = mutableMapOf("debug" to true)
    val channels = loadClasses<GenericChannel>("bot.channels.impl", Channel::class, this)
    private val connectors: Map<String, GenericConnector> =
        loadClasses<GenericConnector>("bot.connectors.impl", Connector::class).associateBy {
        it::class.java.getAnnotation(Connector::class.java).name
    }.toMap()
    private val applications: List<Service> = loadClasses("bot.app", Application::class)
    private val commands: Map<String, CommandHandler> =
        loadClasses<CommandHandler>("bot.messages.impl", Message::class).associateBy {
        it::class.java.getAnnotation(Message::class.java).value
    }.toMap()

    init {
        applications.forEach { it.start(this) }
        println(
            "Successfully loaded ${channels.size} channels, ${connectors.size} connectors," +
                    " ${applications.size} applications, ${commands.size} messages"
        )
    }

    fun getConnector(name: String): GenericConnector = connectors.getValue(name)

    fun getCommands(): Map<String, CommandHandler> = commands

    fun onReceiveCommand(message: String, user: User, source: String, channel: GenericChannel) {
        val command = message.fullTrim().split(" ").map { it.fullTrim() }
        val header = command[0].toLowerCase()

        if (commands.containsKey(header)) {
            commands[header]?.handle(
                MessageData(header, command.filterIndexed { i, _ -> i > 0 }, user, source),
                channel,
                this
            )
        } else {
            channel.send(
                "Sorry {user:${user.id}}, '${header}' is not a CoinBot command. See 'J --help'.",
                source
            )
        }
    }

}