package com.example.jsoup3_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jsoup3_kotlin.databinding.ActivityMainBinding
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        retrieveWebData()
    }

    private fun retrieveWebData() {
        thread {

            val doc= Jsoup.connect("https://www.bookmyforex.com/us-dollar/rates/kochi/")
                    .timeout(60000).validateTLSCertificates(false)
                    .get()
            val textElement=doc.getElementsByTag("h1")

            val textNew=doc.getElementsByTag("td")

            this.runOnUiThread {
                binding.textId.text=textNew[1].text()
            }
        }
    }
}