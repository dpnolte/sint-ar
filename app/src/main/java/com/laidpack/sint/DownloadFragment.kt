package com.laidpack.sint

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class DownloadFragment : Fragment(), IntroFragment {

    private lateinit var goToNext: () -> Unit
    private lateinit var downloadButton : Button
    private lateinit var continueButton : Button
    private lateinit var downloadStatus: TextView
    private lateinit var assetsDirString: String
    private lateinit var assetsZipString: String

    override fun setGoToNext(method: () -> Unit) {
        goToNext = method
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_download, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val v = this.view as View
        val a = this.activity as Activity
        continueButton = v.findViewById(R.id.continueButton)
        downloadButton = v.findViewById(R.id.downloadButton)
        downloadStatus = v.findViewById(R.id.downloadStatus)

        assetsDirString = "${activity?.filesDir?.path}/assets"
        assetsZipString = "$assetsDirString/assets.zip"
        Log.d("IntroActivity", "Assets Dir: $assetsDirString")

        val file = File(assetsDirString, "assets.zip")
        if (file.exists()) {
            downloadStatus.visibility = View.VISIBLE
            downloadStatus.text = getString(R.string.zipExists)
            continueButton.isEnabled = true
        } else {
            downloadStatus.visibility = View.VISIBLE
            downloadStatus.text = getString(R.string.notYetDownloaded)
        }

        downloadButton.setOnClickListener {
            downloadStatus.visibility = View.VISIBLE
            downloadStatus.text = getString(R.string.createFolder)

            AsyncTask.execute {
                val assetsDir = File(assetsDirString)
                assetsDir.mkdirs()

                a.runOnUiThread {
                    downloadStatus.text = getString(R.string.downloading)
                }
                downloadZipFile(assetsUrl, assetsZipString)

                a.runOnUiThread {
                    downloadStatus.text = getString(R.string.unpacking)
                }
                unpackZip(assetsZipString)

                a.runOnUiThread {
                    downloadStatus.text = getString(R.string.done)
                    continueButton.isEnabled = true
                }
            }
        }


        continueButton.setOnClickListener {
            goToNext()
        }
    }

    private fun downloadZipFile(urlStr: String, destinationFilePath: String) {
        var input: InputStream? = null
        var output: OutputStream? = null
        var connection: HttpURLConnection? = null
        try {
            val url = URL(urlStr)

            connection = url.openConnection() as HttpURLConnection
            connection.connect()

            Log.d("downloadZipFile", "Server ResponseCode=" + connection.responseCode + " ResponseMessage=" + connection.responseMessage)
            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                Log.w("downloadZipFile", "Connection failed")

            }
            connection.let {
                Log.d("downloadZipFile", it.url.toString())
            }

            // download the file
            input = connection.inputStream

            Log.d("downloadZipFile", "destinationFilePath=$destinationFilePath")
            File(destinationFilePath).createNewFile()
            output = FileOutputStream(destinationFilePath)

            val data = ByteArray(4096)
            var count: Int = input.read(data)
            var total: Long = 0
            var lastPercentage = -1
            while (count != -1) {
                output.write(data, 0, count)
                total += count
                val progress = ((total * 100 / fileSize)).toInt()
                if (progress > lastPercentage) {
                    lastPercentage = progress
                    activity?.runOnUiThread {
                        downloadStatus.text = "Downloading %$progress"
                    }
                }

                count = input.read(data)

            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        } finally {
            try {
                output?.close()
                input?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            connection?.disconnect()
        }

        val f = File(destinationFilePath)

        Log.d("downloadZipFile", "f.getParentFile().getPath()=" + f.parentFile.path)
        Log.d("downloadZipFile", "f.getName()=" + f.name.replace(".zip", ""))
    }

    private fun unpackZip(filePath: String): Boolean {
        val inputStream: InputStream
        val zipInputStream: ZipInputStream
        try {

            val zipFile = File(filePath)
            val parentFolder = zipFile.parentFile.path
            var filename: String

            inputStream = FileInputStream(filePath)
            zipInputStream = ZipInputStream(BufferedInputStream(inputStream))
            var zipEntry: ZipEntry? = null
            val buffer = ByteArray(1024)
            var count: Int

            zipEntry = zipInputStream.nextEntry
            @Suppress("SENSELESS_COMPARISON")
            while (zipEntry != null) {
                filename = zipEntry.name

                if (zipEntry.isDirectory) {
                    val fmd = File("$parentFolder/$filename")
                    fmd.mkdirs()
                    continue
                }

                val fileOutputStream = FileOutputStream("$parentFolder/$filename")

                count = zipInputStream.read(buffer)
                while (count != -1) {
                    fileOutputStream.write(buffer, 0, count)
                    count = zipInputStream.read(buffer)
                }

                fileOutputStream.close()
                zipInputStream.closeEntry()
                zipEntry = zipInputStream.nextEntry
            }

            zipInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }

        return true
    }

    companion object {
        private const val assetsUrl = "https://drive.google.com/uc?export=download&id=1APC6R4D96joUM-pP7Is1WdAKJRTebSn2"
        private const val fileSize = 40800000
    }


}