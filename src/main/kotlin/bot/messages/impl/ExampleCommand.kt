package bot.messages.impl

import bot.api.Message
import bot.channels.GenericChannel
import bot.messages.CommandHandler
import bot.messages.MessageData

@Message("example")
class ExampleCommand : CommandHandler {

    override fun handle(message: MessageData, source: GenericChannel) {

    }

}