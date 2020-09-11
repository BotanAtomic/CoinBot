package bot.messages.impl

import bot.api.Message
import bot.channels.GenericChannel
import bot.connectors.Connectors
import bot.connectors.impl.BinanceSpotConnector
import bot.core.Core
import bot.messages.CommandHandler
import bot.messages.MessageData

@Message("pc", "Check the price of a coin, ex: `J pc HPB`")
class PriceCheck: CommandHandler {

    override fun handle(message: MessageData, source: GenericChannel, core: Core) {
        if(message.params.isEmpty()) {
            Help().handle(message, source, core)
            return
        }

        val binanceSpotConnector = core.getConnector(Connectors.BinanceSpot.connectorName) as BinanceSpotConnector
        val lastPrice = binanceSpotConnector.getLastPrice(message.params.first().toUpperCase())
        source.send(lastPrice, message.source)
    }
}