package bot.messages.impl

import bot.api.Message
import bot.channels.GenericChannel
import bot.core.Core
import bot.messages.CommandHandler
import bot.messages.MessageData

@Message("--help", "list all available commands")
class Help : CommandHandler {

    override fun handle(message: MessageData, source: GenericChannel, core: Core) {
        val commands = core.getCommands()
        val builder = StringBuilder("```JavaScript\n")

        builder.append("CoinBot command usage: J <command> [<args>]\n\n")

        val maxCommandLength = commands.keys.maxByOrNull { it.length }.orEmpty().length + 2

        commands.forEach {
            val description = it.value.javaClass.getAnnotation(Message::class.java).description
            builder.append(it.key.padEnd(maxCommandLength)).append(": ")
            .append(description).append("\n")
        }

        builder.append("```")
        source.send(builder.toString(), message.source)
    }

}
