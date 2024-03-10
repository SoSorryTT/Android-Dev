package org.classapp.cx6310545329

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class UserInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_input2)

        val convertBtn:Button = findViewById(R.id.convertBtn)
        val amountTxt:EditText = findViewById(R.id.amountTxt)
        var convertAmountInput:String
        convertBtn.setOnClickListener {
            convertAmountInput = amountTxt.text.toString()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}