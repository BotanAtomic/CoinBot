package bot.messages.impl

import bot.api.Message
import bot.channels.GenericChannel
import bot.messages.CommandHandler
import bot.messages.MessageData

@Message("example", "an example command")
class Example : CommandHandler {

    override fun handle(message: MessageData, source: GenericChannel) {
        source.send("Hello {user:${message.user.id}}, this is the example command!", message.source)
    }

}