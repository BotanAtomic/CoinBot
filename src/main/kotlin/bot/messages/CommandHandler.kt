package bot.messages

import bot.channels.GenericChannel

interface CommandHandler {

    fun handle(message: MessageData, source: GenericChannel)

}