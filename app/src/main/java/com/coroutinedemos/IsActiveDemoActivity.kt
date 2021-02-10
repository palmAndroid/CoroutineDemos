package com.coroutinedemos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class IsActiveDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_concept_layout)
        runMakeFunction()
    }

    private fun runMakeFunction() {
        runBlocking {
            Log.d("DemoSystem", "Main Thread Starts: " + Thread.currentThread().name)
            var job = launch(Dispatchers.Default){
                for(i in 0..50){
                    if(!isActive){
                        //break
                        return@launch
                    }
                    Log.d("DemoSystem","$i.")
                }
            }
            delay(100)
            job.cancelAndJoin()
            Log.d("DemoSystem", "Main Thread ends: " + Thread.currentThread().name)

        }
    }
}