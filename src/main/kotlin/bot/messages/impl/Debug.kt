package bot.messages.impl

import bot.api.Message
import bot.channels.GenericChannel
import bot.core.Core
import bot.messages.CommandHandler
import bot.messages.MessageData

@Message("debug", "Activate or Deactivate debug mode")
class Debug: CommandHandler {

    override fun handle(message: MessageData, source: GenericChannel, core: Core) {
        when (message.params.firstOrNull()) {
            "on" -> {
                core.tempDataBase["debug"] = true
                source.send("Debug Mode activated", message.source)
            }
            "off" -> {
                core.tempDataBase["debug"] = false
                source.send("Debug Mode deactivated", message.source)
            }
            "status" -> { source.send("Debug is ${toDisplay(core.tempDataBase["debug"] as Boolean)}", message.source) }
            else -> { Help().handle(message, source, core) }
        }
    }

    private fun toDisplay(status: Boolean): String = if(status) "Activate" else "Deactivate"
}
