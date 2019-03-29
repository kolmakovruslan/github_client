package io.github.kolmakovruslan.myapplication

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var view: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = EditText(this)
        view.setBackgroundColor(Color.GREEN)
        view.setTextColor(Color.BLACK)
        view.gravity = Gravity.CENTER
        view.setTextSize(23f)
        view.setText(savedInstanceState?.getString("text") ?: "text")
        setContentView(view)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("text", view.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}