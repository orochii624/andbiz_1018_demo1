package com.example.demo1_kotlin

import android.view.View
import android.widget.Toast

class Bar(val context: Second) : View.OnClickListener {
    override fun onClick(p0: View?) {
        Toast.makeText(context, "button4 clicked", Toast.LENGTH_LONG).show()
    }

}
