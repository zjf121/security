package org.example.test

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking { // this: CoroutineScope
        val job = launch { // launch a new coroutine and continue
            doWorld()
        }
        println("Hello") // main coroutine continues while a previous one is delayed
        job.join()
        println("ssss")
    }
}

suspend fun doWorld(){
    coroutineScope {
        launch {
            delay(1000)
            println("word")
        }
        launch {
            delay(2000)
            println("word1")
        }
    }
}