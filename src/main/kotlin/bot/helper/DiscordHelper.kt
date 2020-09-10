package bot.helper

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.entities.User

fun JDA.getTextChannel(id: String): TextChannel? =
    if (this.textChannels.isEmpty()) null else when {
        id.toLongOrNull() != null -> this.getTextChannelById(id.toLong())
        else -> this.textChannels.firstOrNull { it.name.toLowerCase() == id.toLowerCase() }
    }

fun User.toUser() = bot.entity.User(this.idLong.toString(), this.name)
