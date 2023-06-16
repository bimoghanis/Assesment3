package com.pt2.assesment3

import NotificationUtils
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.pt2.assesment3.databinding.ActivityMainBinding
import com.pt2.assesment3.worker.MyWorker

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = "Selamat datang"
        val message = "kalau takut lupa dengan sesuatu tinggal catat aja"
        NotificationUtils.showNotification(this, title, message)

        // Buat permintaan WorkRequest untuk menjalankan MyWorker
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()

        // Jalankan tugas latar belakang menggunakan WorkManager
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        NavigationUI.setupActionBarWithNavController(this, navController)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_listCatatan, R.id.navigation_Tambah, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val workManager = WorkManager.getInstance(applicationContext)
        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this, { workInfo ->
            if (workInfo != null) {
                when (workInfo.state) {
                    WorkInfo.State.ENQUEUED -> {
                        Log.d("WorkManager", "Kerjaan dalam antrian")
                    }
                    WorkInfo.State.RUNNING -> {
                        Log.d("WorkManager", "Kerjaan sedang berjalan")
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        Log.d("WorkManager", "Kerjaan selesai dengan sukses")
                    }
                    WorkInfo.State.FAILED -> {
                        Log.d("WorkManager", "Kerjaan gagal")
                    }
                    WorkInfo.State.CANCELLED -> {
                        Log.d("WorkManager", "Kerjaan dibatalkan")
                    }
                    else -> {

                    }
                }
            }
        })
    }
}
