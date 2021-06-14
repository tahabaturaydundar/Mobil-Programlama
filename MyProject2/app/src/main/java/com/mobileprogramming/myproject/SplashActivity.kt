package com.mobileprogramming.myproject

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Process
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    fun internetVarMi(context: Context): Boolean { // burada internet var mı yok mu fonksiyonunu oluşturdum !
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)


        if (internetVarMi(this)) {
            val splashThread: Thread
            splashThread = object : Thread() {
                override fun run() {
                    try {
                        synchronized(this) { Thread.sleep(3000) }
                    } catch (ex: InterruptedException) {
                    } finally {
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }
                }
            }
            splashThread.start()
        } else {
            val alert = AlertDialog.Builder(this).create()
            alert.setTitle("404 not found!")
            alert.setMessage("İnternet bağlantınızın olduğundan emin olun!")
            alert.setButton(
                DialogInterface.BUTTON_NEUTRAL, "Tamam"
            ) { dialog, which ->
                val pid = Process.myPid()
                Process.killProcess(pid)
                dialog.dismiss()
            }
            alert.show()
        }
    }
}