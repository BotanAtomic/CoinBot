package bot.helper

fun String.fullTrim() = this.trim().trimEnd()

fun String.trimTrailingZero() = this.trimEnd('0')