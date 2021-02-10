package com.coroutinedemos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DispatcherDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_concept_layout)
        setDispatcherDemo()
    }

    private fun setDispatcherDemo() {
      runBlocking {
          Log.d("DemoSystem", "Main Thread Starts: " + Thread.currentThread().name)
          launch {
              Log.d("DemoSystem", "C1: " + Thread.currentThread().name) //main
              delay(1000)
              Log.d("DemoSystem", "C1 after delay: " + Thread.currentThread().name) //main
          }

          launch(Dispatchers.Default){
              Log.d("DemoSystem", "C2: " + Thread.currentThread().name) //DefaultDispatcher-worker-1
              delay(1000)
              Log.d("DemoSystem", "C2 after delay: " + Thread.currentThread().name) //DefaultDispatcher-worker-1
          }

          launch(Dispatchers.Unconfined){
              Log.d("DemoSystem", "C3: " + Thread.currentThread().name) //main
              delay(1000)
              Log.d("DemoSystem", "C3 after delay: " + Thread.currentThread().name) //kotlinx.coroutines.DefaultExecutor
          }

          launch(coroutineContext){
              Log.d("DemoSystem", "C4: " + Thread.currentThread().name) //main
              delay(1000)
              Log.d("DemoSystem", "C4 after delay: " + Thread.currentThread().name) //kotlinx.coroutines.DefaultExecutor
          }
      }
        /*
         D/DemoSystem: Main Thread Starts: main
         D/DemoSystem: C3: main
         D/DemoSystem: C2: DefaultDispatcher-worker-1
         D/DemoSystem: C1: main

         D/DemoSystem: C3 after delay: kotlinx.coroutines.DefaultExecutor
         D/DemoSystem: C2 after delay: DefaultDispatcher-worker-1
         D/DemoSystem: C1 after delay: main
         */
    }
}