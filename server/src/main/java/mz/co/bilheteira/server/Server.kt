package mz.co.bilheteira.server

import android.app.Application
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mz.co.bilheteira.server.data.Root
import mz.co.bilheteira.server.data.Service
import java.util.*
import java.util.concurrent.TimeUnit

class Server{}
//: Application() {
//
//    private val log = Service(name = "log", uri = "localhost:8080/log")
//    private val status = Service(name = "status", uri = "localhost:8080/status")
//
//    private val root = Root(
//        start = Calendar.getInstance().time,
//        services = listOf(log, status)
//    )
//
//    private val server = embeddedServer(Netty, 8080) {
//        install(ContentNegotiation) {
//            gson()
//        }
//        routing {
//            get("/") {
//                call.respondText(Gson().toJson(root).toString(), ContentType.Application.Json)
//            }
//        }
//    }
//
//    fun start() = server.start(wait = true)
//
//    fun stop() = server.stop(gracePeriod = 100L, timeout = 10L, TimeUnit.MILLISECONDS)
//}