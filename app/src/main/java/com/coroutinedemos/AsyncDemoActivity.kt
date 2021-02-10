package com.coroutinedemos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class AsyncDemoActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_concept_layout)
        mainFunction()
    }

    private fun mainFunction() {
        runBlocking {
            Log.d("DemoSystem", "Main Thread Starts: " + Thread.currentThread().name)
            /*
            Deferred<Int> -> if async block will return Integer value
            Deferred<Unit> -> if async block will return nothing
             */
            var job : Deferred<Int> =  async {
                Log.d("DemoSystem", "Fake work starts Thread : " + Thread.currentThread().name)
                delay(100)
                Log.d("DemoSystem", "Fake work ends Thread : " + Thread.currentThread().name)
                101
            }
           // job.join() // will not be able to get the integer returned value
           var intNum =  job.await()
            Log.d("DemoSystem", "Main Thread ends: " + Thread.currentThread().name)
            Log.d("DemoSystem", "Async Value returned value: $intNum")

        }
        /**
         * output :
         *DemoSystem: Main Thread Starts: main
        DemoSystem: Fake work starts Thread : main
        DemoSystem: Fake work ends Thread : main
        DemoSystem: Main Thread ends: main
        DemoSystem: Async Value returned value: 101
         */
    }

}