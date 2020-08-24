package bot.messages

import bot.entity.User

data class MessageData(val header: String, val params: List<String>, val user: User, val channel: String)