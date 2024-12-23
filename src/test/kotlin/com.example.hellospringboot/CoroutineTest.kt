package com.example.hellospringboot;
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CoroutineTest {

    // Sequentially executes doWorld followed by "Done"
    @Test
    fun main() = runBlocking {
        doWorld()
        println("Done")
    }

    // Concurrently executes both sections
    private suspend fun doWorld() = coroutineScope { // this: CoroutineScope
        launch {
            delay(2000L)
            println("World 2")
        }
        launch {
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }

}