package bot.messages

import bot.channels.GenericChannel
import bot.core.Core

interface CommandHandler {

    fun handle(message: MessageData, source: GenericChannel)

}