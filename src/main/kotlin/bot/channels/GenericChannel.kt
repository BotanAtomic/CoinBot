package bot.channels

import net.dv8tion.jda.api.EmbedBuilder

interface GenericChannel {

    fun send(message: String, channel: String)

    fun send(embedBuilder: EmbedBuilder, channel: String)

}