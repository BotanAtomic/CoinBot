package bot.helper

import net.dv8tion.jda.api.JDA

fun JDA.getChannel(name: String) = this.textChannels.first { it.name == name }
