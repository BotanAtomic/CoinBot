package bot.app

import bot.api.Application
import bot.api.Service
import bot.core.Core
import java.util.*
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

    override fun start(core: Core) {
        core.channels.forEach {
            val ids = listOf("391236348683485185", "389794413196476441") //koplosex & methyr42
            val random = Random()


            future = scheduler.scheduleWithFixedDelay({
                var message = jhonSentences.split("\n").random()

                if (random.nextInt() == 3) message += " {user:${ids.random()}}"

                it.send(message, "mutual-bucket")
            }, 0, 15, TimeUnit.MINUTES)
        }

    }

    override fun stop() {
        future.cancel(true)
        scheduler.shutdownNow()
    }

}