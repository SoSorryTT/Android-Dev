package org.classapp.whatsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView

class LogInViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapApiKey = BuildConfig.MAPS_API_KEY
        Log.i("MAP_API_KEY", "Map API Key = " + mapApiKey)

        setContentView(R.layout.activity_log_in_view)
        val signInBtn:Button = findViewById(R.id.signInBtn)
        val signInPanel:CardView = findViewById(R.id.signInPanel)
        val usernameTxt:EditText = findViewById(R.id.usernameTxt)
        val pwdTxt:EditText = findViewById(R.id.pwdTxt)
        val enterBtn:Button = findViewById(R.id.enterBtn)
        signInBtn.setOnClickListener { signInPanel.visibility = View.VISIBLE }
        var username:String; var password:String
        enterBtn.setOnClickListener {
            username = usernameTxt.text.toString()
            password = pwdTxt.text.toString()
            if (username.equals("android") && password.equals("google"))
            {
                Toast.makeText(this, "Welcome to WhatsUp!!", Toast.LENGTH_LONG).show()
                usernameTxt.setText("")
                pwdTxt.setText("")
                signInPanel.visibility = View.GONE

                val i = Intent(this, MainActivity::class.java)
                i.putExtra("loginUsername", username)
                startActivity(i)
            }
            else Toast.makeText(this, "Please Try Again", Toast.LENGTH_LONG).show()
        }
    }
}