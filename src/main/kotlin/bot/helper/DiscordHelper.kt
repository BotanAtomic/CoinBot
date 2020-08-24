package bot.helper

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.TextChannel

fun JDA.getTextChannel(name: String): TextChannel = this.textChannels.first { it.name == name }
