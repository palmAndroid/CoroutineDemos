package com.coroutinedemos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class ExceptionDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_concept_layout)
       // makeFunctionCall()
        customisedFinallyBlock()
    }

    private fun customisedFinallyBlock() {
        runBlocking {
            Log.d("DemoSystem", "Main Thread Starts: " + Thread.currentThread().name)
            var job = launch(Dispatchers.Default) {
                try {
                    Log.d("DemoSystem", "Inside the try block: " + Thread.currentThread().name)
                    for (i in 0..50) {
                        if (!isActive) {
                            //break
                            return@launch
                        }
                        Log.d("DemoSystem", "$i.")
                        delay(100)
                    }
                } catch (ex: CancellationException) {
                    Log.d("DemoSystem", "Inside the catch block: " + Thread.currentThread().name)
                } finally {
                    Log.d("DemoSystem", "Inside the finally block: " + Thread.currentThread().name)
                    withContext(NonCancellable) {
                        delay(200)
                        Log.d(
                            "DemoSystem",
                            "Inside the finally coroutine: " + Thread.currentThread().name
                        )
                    }

                }
            }
                delay(10)
                job.cancelAndJoin()
                Log.d("DemoSystem", "Main Thread ends: " + Thread.currentThread().name)
        }
        /*
        DemoSystem: Main Thread Starts: main
        DemoSystem: Inside the try block: DefaultDispatcher-worker-1
        DemoSystem: 0.
        DemoSystem: Inside the catch block: DefaultDispatcher-worker-1
        DemoSystem: Inside the finally block: DefaultDispatcher-worker-1
        DemoSystem: Inside the finally coroutine: DefaultDispatcher-worker-1
        DemoSystem: Main Thread ends: main
         */
    }

    private fun makeFunctionCall() {
        runBlocking {
            Log.d("DemoSystem", "Main Thread Starts: " + Thread.currentThread().name)
            var job = launch(Dispatchers.Default){
                try {
                    Log.d("DemoSystem", "Inside the try block: " + Thread.currentThread().name)
                    for(i in 0..50){
                        if(!isActive){
                            //break
                            return@launch
                        }
                        Log.d("DemoSystem","$i.")
                        delay(100)
                    }
                }catch (ex: CancellationException){
                    Log.d("DemoSystem", "Inside the catch block: " + Thread.currentThread().name)
                }finally {
                    Log.d("DemoSystem", "Inside the finally block: " + Thread.currentThread().name)
                }

            }
            delay(10)
            job.cancelAndJoin()
            Log.d("DemoSystem", "Main Thread ends: " + Thread.currentThread().name)
        }
        /** output
         *
        DemoSystem: Main Thread Starts: main
        DemoSystem: Inside the try block: DefaultDispatcher-worker-1
        DemoSystem: 0.
        DemoSystem: Inside the catch block: DefaultDispatcher-worker-1
        DemoSystem: Inside the finally block: DefaultDispatcher-worker-1
        DemoSystem: Main Thread ends: main
         */
    }

}