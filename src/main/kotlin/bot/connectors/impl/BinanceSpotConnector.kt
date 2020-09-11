package bot.connectors.impl

import bot.api.Connector
import bot.connectors.GenericConnector
import bot.helper.trimTrailingZero
import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.BinanceApiRestClient
import kotlin.random.Random

@Connector("binance-spot")
class BinanceSpotConnector: GenericConnector {

    private val factory: BinanceApiClientFactory = BinanceApiClientFactory.newInstance(
        System.getenv("binance_api_key"), System.getenv("binance_secret_key")
    )
    private val client: BinanceApiRestClient = factory.newRestClient()

    override fun getLastPrice(coin: String): String {
        val stringBuilder = StringBuilder()

        runCatching {
            client.get24HrPriceStatistics("${coin}USDT").lastPrice
        }.onSuccess {
            stringBuilder.append("$coin = ${it.trimTrailingZero()} $")
        }
        runCatching {
            client.get24HrPriceStatistics(coin+"BTC").lastPrice
        }.onSuccess {
            if(stringBuilder.isNotEmpty()) { stringBuilder.append(" / ") }
            stringBuilder.append("${it.trimTrailingZero()} BTC")
        }

        if(stringBuilder.isEmpty()) {
            if(Random.nextBoolean()) {
                stringBuilder.append("$coin was dumped and erased from the Blockchain by Koplosexe")
            } else {
                stringBuilder.append("Oops! I dumped and erased $coin from the Blockchain")
            }
        }
        return stringBuilder.toString()
    }
}
