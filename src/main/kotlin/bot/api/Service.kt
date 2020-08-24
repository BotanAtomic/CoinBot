package bot.api

import bot.core.Core

interface Service {

    fun start(core: Core)

    fun stop()
}