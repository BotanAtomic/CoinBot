package bot.events

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class DefaultEventListener : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.contentDisplay.toLowerCase().startsWith("B ".toLowerCase())) {
            //TODO: handle message
        }
    }

}