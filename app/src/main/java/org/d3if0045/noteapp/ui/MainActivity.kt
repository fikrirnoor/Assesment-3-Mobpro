package org.d3if0045.noteapp.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import org.d3if0045.noteapp.R

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "updater"
    }

    private lateinit var navigationController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = getString(R.string.channel_desc)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
            manager.createNotificationChannel(channel)
 }
        navigationController = findNavController(R.id.navHostFragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.todoFragment)
        )

        NavigationUI.setupActionBarWithNavController(this, navigationController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp()
    }
}