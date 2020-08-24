package bot.app

import net.dv8tion.jda.api.JDA
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

const val sentences = "This is it! \n" +
        "Oh my god macdee is crossing!\n" +
        "I just received a mail from my source in China!\n" +
        "HOLD YOU FUCKING PUSSY!\n" +
        "This swing is now a long term hold!\n" +
        "Of course I was longing !\n" +
        "This will dump really really hard! \n" +
        "Did you see that Doji ?\n" +
        "We are entering Ichimoku this is very bullish !\n" +
        "It's a Bulltrap !\n" +
        "BUY THE FUCKING DIP !\n" +
        "DON'T PANIC !"

class JhonApp : Application {

    private val scheduler = Executors.newScheduledThreadPool(1)

    override fun start(jda: JDA) {
        val channel = jda.textChannels.first { it.name == "mutual-bucket" }
        val ids = listOf("391236348683485185", "389794413196476441") //koplosex & methyr42
        val random = Random()
        scheduler.scheduleWithFixedDelay({
            if(random.nextInt(10) == 3) {
                channel.sendMessage(sentences.split("\n").random() + " <@${ids.random()}>").queue()
            } else {
                channel.sendMessage(sentences.split("\n").random()).queue()
            }
        }, 5, 5, TimeUnit.MINUTES)
    }

}