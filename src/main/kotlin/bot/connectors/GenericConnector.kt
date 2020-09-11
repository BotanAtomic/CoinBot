package bot.connectors

interface GenericConnector {

    fun getLastPrice(coin: String): String

}
