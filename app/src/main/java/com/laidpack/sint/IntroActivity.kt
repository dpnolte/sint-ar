package com.laidpack.sint

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


interface  IntroFragment {
    fun setGoToNext(method: () -> Unit)
}

class IntroActivity : AppCompatActivity() {
    private lateinit var assetsDirString: String
    private var currentPage = Page.Download

    enum class Page {
        Download,
        Intro1,
        Intro2,
        Start
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        assetsDirString = "${this.filesDir.path}/assets"
        Log.d("IntroActivity", "Assets Dir: $assetsDirString")
        val downloadFragment = supportFragmentManager.findFragmentById(R.id.downloadFragment) as DownloadFragment
        downloadFragment.setGoToNext(this::goToNext)
    }

    private fun goToNext() {
        currentPage = when (currentPage) {
            Page.Download -> Page.Intro1
            Page.Intro1 -> Page.Intro2
            else -> Page.Start
        }
        if (currentPage == Page.Start) {
            // start activity
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("assetsDir", assetsDirString)
            }
            finish()
            startActivity(intent)
        } else {
            //if you're using compat library
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()

            val fragment: IntroFragment = when (currentPage) {
                Page.Intro1 -> Intro1Fragment()
                else -> Intro2Fragment()
            }
            fragment.setGoToNext(this::goToNext)
            transaction.replace(R.id.container, fragment as Fragment, currentPage.toString())
            transaction.addToBackStack(currentPage.toString())
            transaction.commit()
        }

    }
}