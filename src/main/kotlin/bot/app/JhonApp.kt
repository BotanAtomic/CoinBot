package bot.app

import bot.api.Application
import bot.api.Service
import bot.helper.getTextChannel
import net.dv8tion.jda.api.JDA
import java.util.Random
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit


@Application("jhon random sentences")
class JhonApp : Service {

    private val jhonSentences = "This is it! \n" +
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


    private val scheduler = Executors.newScheduledThreadPool(1)

    private lateinit var future: ScheduledFuture<*>

    override fun start(input: Any?) {
        (input as JDA).getTextChannel("mutual-bucket").let {
            val ids = listOf("391236348683485185", "389794413196476441") //koplosex & methyr42
            val random = Random()
            val message = jhonSentences.split("\n").random()
            future = scheduler.scheduleWithFixedDelay({
                if (random.nextInt(10) == 3) {
                    it.sendMessage(message + " <@${ids.random()}>").queue()
                } else {
                    it.sendMessage(message).queue()
                }
            }, 5, 5, TimeUnit.MINUTES)
        }

    }

    override fun stop() {
        future.cancel(true)
        scheduler.shutdownNow()
    }

}