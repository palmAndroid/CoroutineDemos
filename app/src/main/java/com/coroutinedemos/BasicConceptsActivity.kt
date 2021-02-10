package com.coroutinedemos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BasicConceptsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_concept_layout)
        mainFunction()
    }

    private fun mainFunction() {
        runBlocking {
            Log.d("DemoSystem", "Main Thread Starts: " + Thread.currentThread().name)
            GlobalScope.launch {
                Log.d("DemoSystem", "Fake work starts Thread : " + Thread.currentThread().name)
                delay(100)
                Log.d("DemoSystem", "Fake work ends Thread : " + Thread.currentThread().name)
            }
            delay(200)
            Log.d("DemoSystem", "Main Thread ends: " + Thread.currentThread().name)
        }
        /**
         * output :
         *DemoSystem: Main Thread Starts: main
        DemoSystem: Fake work starts Thread : DefaultDispatcher-worker-1
        DemoSystem: Fake work ends Thread : DefaultDispatcher-worker-1
        DemoSystem: Main Thread ends: main
         */
    }

}
