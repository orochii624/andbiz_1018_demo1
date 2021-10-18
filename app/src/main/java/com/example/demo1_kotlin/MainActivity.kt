package com.example.demo1_kotlin

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    //先宣告在定義lateinit var textView
    lateinit var textView: TextView
    var doActivity: Button? = null
    private lateinit var doWeb: Button
    private lateinit var doCall: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = View(this)
        textView = findViewById(R.id.textView1)

        //另一種onclick寫法 比較好
        val doActivityButton: Button = findViewById(R.id.doActivity)
        val doCallButton: Button = findViewById(R.id.doCall)
        doActivityButton.setOnClickListener { v -> doActivity(v) }
        doCallButton.setOnClickListener { v -> doCall(v) }

        //第三種寫法
        findViewById<Button>(R.id.doWeb).setOnClickListener { _ ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.yahoo.com.tw")
            startActivity(intent)

        }

        doActivity = findViewById(R.id.doActivity)
        doActivity!!.setBackgroundColor(Color.RED)
        doWeb = findViewById(R.id.doWeb)
        doWeb.setBackgroundColor(Color.GREEN)
        doCall = findViewById(R.id.doCall)
        doCall.setBackgroundColor(Color.BLUE)

    }



    fun doActivity(view: View) {
        val intent = Intent()
        intent.setClass(this, Second::class.java)
        val bundle = Bundle()
        bundle.putString(KEY1, "654321")
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun doCall(view: View) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            val textView = findViewById<TextView>(R.id.textView1)
            textView.text = "should show a dialog or prompt for ask permission"
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE))
            {
                showPromptDialog()

            } else {
                askForPermission()
            }

        } else {

            callAction()
        }
    }

    //static fianl變數寫法
    companion object {
        const val KEY1 = "id"
        const val CALL_PHONE_PERMISSION_CHECK = 1234
    }

    private fun askForPermission() {
        //TODO("Not yet implemented")
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), CALL_PHONE_PERMISSION_CHECK)
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode)
        {
            CALL_PHONE_PERMISSION_CHECK -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    textView.text = "user already grant, can dial phone"
                    callAction()
                } else {
                    textView.text = "oops, user not agree, so still can not dial phone"
                }
            }
        }
    }


    private fun showPromptDialog() {
        //TODO("Not yet implemented")
        AlertDialog.Builder(this)
            .setTitle("need call phone permission")
            .setMessage("for identity verification")
            //偷懶寫法用_ _
            //.setPositiveButton("OK") { _, _ -> askForPermission() }
            //.setNegativeButton("No") { _, _ -> textView.text = "user not agree, no permission ask will display" }

            .setPositiveButton("OK") { d, w -> askForPermission() }
            .setNegativeButton("No") { d, w -> textView.text = "user not agree, no permission ask will display" }
            .show();

    }

    private fun callAction() {
        val intent = Intent(Intent.ACTION_CALL)
        //intent.setData(Uri.parse("tel:021234567"))
        intent.data = Uri.parse("tel:021234567")
        startActivity(intent)
    }
}