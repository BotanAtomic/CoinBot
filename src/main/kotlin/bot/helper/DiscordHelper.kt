package bot.helper

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.TextChannel

fun JDA.getTextChannel(name: String): TextChannel? =
    if (this.textChannels.isEmpty()) null else this.textChannels.first { it.name == name }

