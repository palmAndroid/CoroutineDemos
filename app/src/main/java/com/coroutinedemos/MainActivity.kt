package com.coroutinedemos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.coroutinedemos.image_processing.ImageProcessorActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button1).setOnClickListener {
            startActivity(Intent(applicationContext,BasicConceptsActivity::class.java))
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(applicationContext,JobDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            startActivity(Intent(applicationContext,AsyncDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            startActivity(Intent(applicationContext,CoroutineCancelDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            startActivity(Intent(applicationContext,IsActiveDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            startActivity(Intent(applicationContext,ExceptionDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            startActivity(Intent(applicationContext,DispatcherDemoActivity::class.java))
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            startActivity(Intent(applicationContext,ImageProcessorActivity::class.java))
        }
    }
}