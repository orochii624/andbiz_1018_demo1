package com.example.demo1_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast




class Second : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)
        val id = intent.extras!!.getString(MainActivity.KEY1)
        Toast.makeText(this, "you passed:${id}!!",
            Toast.LENGTH_LONG).show()

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener(this)
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(Foo())
        findViewById<Button>(R.id.button4).setOnClickListener(Bar(this))

        findViewById<Button>(R.id.button5).setOnClickListener(View.OnClickListener { v ->
            Toast.makeText(this, "button5 clicked", Toast.LENGTH_SHORT).show()
        })
        findViewById<Button>(R.id.button6).setOnClickListener(View.OnClickListener { _ ->
            Toast.makeText(this, "button6 clicked", Toast.LENGTH_SHORT).show()
        })
        findViewById<Button>(R.id.button7).setOnClickListener() { v ->
            Toast.makeText(this, "button7 clicked", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.button8).setOnClickListener { v ->
            Toast.makeText(this, "button8 clicked", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.button9).setOnClickListener { _ ->
            Toast.makeText(this, "button9 clicked", Toast.LENGTH_SHORT).show()
        }

    }

    inner class Foo : View.OnClickListener {
        override fun onClick(p0: View?) {
            //val x1 = this@Foo
            //val x2 = this@Second
            Toast.makeText(this@Second, "button3 clicked", Toast.LENGTH_LONG).show()
        }

    }

    override fun onClick(p0: View?) {
        var buttonMessage = ""
        when (p0!!.id) {
            R.id.button1 -> buttonMessage = "button1"
            R.id.button2 -> buttonMessage = "button2"
        }
        Toast.makeText(this,"${buttonMessage} is clicked", Toast.LENGTH_LONG).show();
    }
}