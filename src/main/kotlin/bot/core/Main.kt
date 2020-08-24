package bot.core

import bot.app.JhonApp
import bot.events.DefaultEventListener
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.utils.Compression
import net.dv8tion.jda.api.utils.cache.CacheFlag


fun main() {
    JDABuilder.createDefault("NzQ0NjMyNTUyOTkxNzUyMjUz.XzmDGw.6mDwcV0Rp4ojXTC3uUWf4qBlV1c").apply {
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