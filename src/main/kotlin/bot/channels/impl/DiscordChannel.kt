package bot.channels.impl

import bot.api.Channel
import bot.channels.GenericChannel
import bot.events.DefaultEventListener
import bot.helper.getTextChannel
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.utils.Compression
import net.dv8tion.jda.api.utils.cache.CacheFlag

@Channel("Discord")
class DiscordChannel : GenericChannel {

    private val jda: JDA

    init {
        JDABuilder.createDefault(System.getenv("coin_bot_token")).apply {
            disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
            setBulkDeleteSplittingEnabled(false)
            setCompression(Compression.NONE)
            setActivity(Activity.watching("xnxx.com"))
            addEventListeners(DefaultEventListener())
            jda = build()
        }
    }

    override fun send(message: String, channel: String) {
        jda.getTextChannel(channel)?.apply {
            sendTyping()
            sendMessage(message).queue()
        }
    }


}