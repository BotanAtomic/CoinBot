package bot.app

import net.dv8tion.jda.api.JDA

interface Application {

    fun start(jda: JDA)

}