package com.coroutinedemos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineCancelDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_concept_layout)
        mainFunction()
    }

    private fun mainFunction() {
        runBlocking {
            Log.d("DemoSystem", "Main Thread Starts: " + Thread.currentThread().name)
            var job = launch {
              for(i in 0..50){
                  Log.d("DemoSystem","$i.")
                  delay(1000)
              }
            }
            delay(20)
            job.cancelAndJoin() // waits /cancel the current coroutine
            Log.d("DemoSystem", "Main Thread Ends: " + Thread.currentThread().name)
        }
    }

    /**
     * Output:
     DemoSystem: Main Thread Starts: main
     DemoSystem: 0.
    DemoSystem: Main Thread Ends: main
     */
}