package bot.messages.impl

import bot.api.Message
import bot.channels.GenericChannel
import bot.helper.getClasses
import bot.messages.CommandHandler
import bot.messages.MessageData

@Message("--help", "list all available commands")
class Help : CommandHandler {

    private val commands = getClasses("bot.messages.impl", Message::class).map {
        it.getAnnotation(Message::class.java)
    }.associateBy({ it.value }, { it.description })

    override fun handle(message: MessageData, source: GenericChannel) {
        val builder = StringBuilder("```JavaScript\n")

        builder.append("CoinBot command usage: J <command> [<args>]\n\n")

        val maxCommandLength = commands.keys.maxByOrNull { it.length }.orEmpty().length + 2

        commands.forEach {
            builder.append(it.key.padEnd(maxCommandLength)).append(": ").append(it.value).append("\n")
        }

        builder.append("```")
        source.send(builder.toString(), message.source)
    }

}