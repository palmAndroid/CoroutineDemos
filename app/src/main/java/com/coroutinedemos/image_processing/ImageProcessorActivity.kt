package com.coroutinedemos.image_processing

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.coroutinedemos.R
import kotlinx.android.synthetic.main.image_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

const val IMAGE_URL = "https://clipart4school.com/wp-content/uploads/2018/07/free-butterfly-clipart.jpg"

class ImageProcessorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_layout)
        var coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
           var bitmapDeffered =  coroutineScope.async(Dispatchers.IO) { processBitmap() }
            var bitmap = bitmapDeffered.await()
            loadImage(bitmap)
        }

    }

    private fun processBitmap() =
        URL(IMAGE_URL).openStream().use {
            BitmapFactory.decodeStream(it)
    }

    private fun loadImage(bitmap: Bitmap) {
        progress_bar.visibility = View.GONE
        image_view.setImageBitmap(bitmap)
        image_view.visibility = View.VISIBLE
    }
}