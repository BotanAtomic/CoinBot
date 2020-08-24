package bot.api

interface Service {

    fun start(input: Any?)

    fun stop()
}