package bot.events

import bot.app.Application
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class DefaultEventListener(private val applications: List<Application>) : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.contentDisplay.startsWith("B ".toLowerCase())) {
            event.channel.sendTyping()
            event.channel.sendMessage(
                "<@${event.author.idLong}> a dit ${
                    event.message.contentDisplay.removePrefix(
                        "B"
                    )
                }"
            ).queue()
        }
    }


    override fun onReady(event: ReadyEvent) {
        applications.forEach { it.start(event.jda) }
    }


}