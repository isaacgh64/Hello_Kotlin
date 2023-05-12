package com.isaac.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tts= TextToSpeech(this,this)

        findViewById<Button>(R.id.btn_play).setOnClickListener{speak()}

    }
    private fun speak(){
        var message: String = findViewById<TextView>(R.id.et_message).text.toString()
        if(message.isEmpty()){
            findViewById<TextView>(R.id.txt_status).text=getString(R.string.txt_error)
            message = "¿Ya es enserio? Pon algo en el editor"
        }
        tts!!.speak(message,TextToSpeech.QUEUE_FLUSH, null, "")
    }
    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.txt_status).text = getString(R.string.txt_active);
            tts!!.language = Locale("ES")
        }else{
            findViewById<TextView>(R.id.txt_status).text = getString(R.string.txt_no_active);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
    }
}