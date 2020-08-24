package bot.channels.impl

import bot.api.Channel
import bot.channels.GenericChannel

@Channel
class DiscordChannel : GenericChannel {

    override fun send(message: String, channel: String) {

    }

}