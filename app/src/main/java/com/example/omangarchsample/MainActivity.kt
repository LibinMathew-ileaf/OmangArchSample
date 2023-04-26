package com.example.omangarchsample

import android.Manifest
import android.app.DownloadManager
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.omangarchsample.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.Locale


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var downloadID: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        when {
            ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }

            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {

            }

            else -> {

                requestPermissionLauncher.launch(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }

        val url = "http://speedtest.ftp.otenet.gr/files/test10Mb.db"
        var fileName = url.substring(url.lastIndexOf('/') + 1)
        fileName = fileName.substring(0, 1).uppercase(Locale.getDefault()) + fileName.substring(1)
        val file: File = createImageFile()

        val request = DownloadManager.Request(Uri.parse(url))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN) // Visibility of the download Notification
            .setDestinationUri(Uri.fromFile(file)) // Uri of the destination file
            .setTitle(fileName) // Title of the Download Notification
            .setDescription("Downloading") // Description of the Download Notification
            .setRequiresCharging(false) // Set if charging is required to begin the download
            .setAllowedOverMetered(true) // Set if download is allowed on Mobile network
            .setAllowedOverRoaming(true) // Set if download is allowed on roaming network

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadID = downloadManager.enqueue(request)

    }

    // Permission
    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission Accepted
                Toast.makeText(this@MainActivity, "Permission Accepted.", LENGTH_SHORT).show()
            } else {
                // Permission Denied
                Toast.makeText(this@MainActivity, "Permission Denied.", LENGTH_SHORT).show()
            }
        }

    private fun createImageFile(): File {
        val state = Environment.getExternalStorageState()
        // Make sure it's available
        val filesDir: File = if (Environment.MEDIA_MOUNTED == state) {
            // We can read and write the media
            File(
                Environment.getExternalStorageDirectory().toString() + "/Example/Media",
                "Example Images"
            )
        } else {
            //            // Load another directory, probably local memory
            //        filesDir = new File(getFilesDir(),"Images");
            File(getExternalFilesDir(null), "Example Images")
        }
        if (!filesDir.exists()) filesDir.mkdirs()
        return File(filesDir, "sample.jpg")
    }
}