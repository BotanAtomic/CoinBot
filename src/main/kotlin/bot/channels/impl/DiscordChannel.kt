package bot.channels.impl

import bot.api.Channel
import bot.channels.GenericChannel
import bot.core.Core
import bot.helper.getTextChannel
import bot.helper.toUser
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.Compression
import net.dv8tion.jda.api.utils.cache.CacheFlag

@Channel("Discord")
class DiscordChannel(private val core: Core) : GenericChannel, ListenerAdapter() {

    private val jda: JDA

    init {
        JDABuilder.createDefault(System.getenv("coin_bot_token")).apply {
            disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
            setBulkDeleteSplittingEnabled(false)
            setCompression(Compression.NONE)
            setActivity(Activity.watching("xnxx.com"))
            addEventListeners(this@DiscordChannel)
            jda = build()
        }
    }

    private fun preProcess(message: String): String {
        var newMessage = message
        val pattern = "\\{user:\\d+}".toRegex()
        val matcher = pattern.findAll(message)

        matcher.forEach {
            val id = it.value.substringAfter("{user:").removeSuffix("}")
            newMessage = pattern.replaceFirst(newMessage, "<@${id}>")
        }

        return newMessage
    }

    override fun send(message: String, channel: String) {
        jda.getTextChannel(channel)?.apply {
            sendTyping()
            sendMessage(preProcess(message)).queue()
        }
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val rawMessage = event.message.contentStripped.toLowerCase()

        if (rawMessage.startsWith("b ")) {
            core.onReceiveCommand(
                rawMessage.substringAfter("b "),
                event.author.toUser(),
                event.channel.idLong.toString(),
                this
            )
        }
    }

}