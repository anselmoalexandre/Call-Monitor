package mz.co.bilheteira.callmonitor.data

data class Log(
    val id: Int,
    val beginning: String,
    val duration: String,
    val number: String,
    val name: String,
    val timesQueried: Int
)

data class Service(
    val id: String,
    val name: String,
    val uri: String
)

data class Status(
    val id: Int,
    val ongoing: Boolean,
    val number: String,
    val name: String
)

data class Root(val start: String, val services: List<Service>)