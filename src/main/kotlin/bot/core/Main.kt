package bot.core

import bot.app.JhonApp
import bot.events.DefaultEventListener
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.utils.Compression
import net.dv8tion.jda.api.utils.cache.CacheFlag


fun main() {
    JDABuilder.createDefault(System.getenv("coin_bot_token")).apply {
        disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
        setBulkDeleteSplittingEnabled(false)
        setCompression(Compression.NONE)
        setActivity(Activity.watching("xnxx.com"))
        addEventListeners(
            DefaultEventListener(
                listOf(
                    JhonApp()
                )
            )
        )
        build()
    }

}