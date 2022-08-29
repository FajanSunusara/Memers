package com.example.memers

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.lang.Math.abs

class MainActivity : AppCompatActivity(),GestureDetector.OnGestureListener {

    var TAG : String = "MainActivity"

    lateinit var  gestureDetector: GestureDetector
    var x2 :Float = 0.0F
    var x1 :Float = 0.0F
    var y1 :Float = 0.0F
    var y2 :Float = 0.0F
    var ciurl: String? = null

    companion object{
        const val MIN_DISTANCE = 150


    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gestureDetector = GestureDetector(this,this)
        loadMeme()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        gestureDetector.onTouchEvent(event)
        when(event?.action) {
            //when we start to swipe
            0->
            {
                x1=event.x
                y1=event.y

            }
            //when we end the swipe
            1->
            {
                x2=event.x
                y2=event.y

                val  valueX: Float = x2-x1
                val  valueY:Float = y2-y1

                if(abs(valueX) >  MIN_DISTANCE)
                {
                    if(x2>x1)
                    {
                        Log.d(TAG,"Right Swipe")
                    }
                    else
                    {
                        Log.d(TAG,"Left Swipe")
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type= "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT,"Check this meme $ciurl")
                        val chooser = Intent.createChooser(intent,"Share this meme using........")
                        startActivity(chooser)
                    }


                }
                 if(abs(valueY) >  MIN_DISTANCE)
                {
                    if(y2>y1)
                    {
                        Log.d(TAG,"Swipe Down")
                    }
                    else
                    {
                        Log.d(TAG,"Swipe Up")
                        loadMeme()
                    }

                }


            }










        }



        return super.onTouchEvent(event)
    }


    override fun onDown(p0: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(p0: MotionEvent?) {
       // TODO("Not yet implemented")
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        //TODO("Not yet implemented")
        return false
    }


    private fun loadMeme(){
        var pg  =findViewById<ProgressBar>(R.id.ProgressBar)
        pg.visibility = View.VISIBLE
        val img =findViewById<ImageView >(R.id.imageView1)

        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                ciurl =response.getString("url")
                Glide.with(this).load(ciurl).listener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pg.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pg.visibility = View.GONE
                        return false
                    }

                }).into(img)
            },
            Response.ErrorListener {


            })

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}