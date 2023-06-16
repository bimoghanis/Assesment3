package com.pt2.assesment3.worker

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result = withContext(Dispatchers.Default) {
        try {
            // Simulasi tugas latar belakang yang memakan waktu
            delay(5000)

            // Proses tugas latar belakang yang ingin Anda lakukan di sini
            // Misalnya, pengiriman notifikasi atau pemrosesan data

            // Contoh pengiriman notifikasi
            val title = "Tugas Latar Belakang Selesai"
            val message = "Tugas latar belakang telah selesai dijalankan."
            NotificationUtils.showNotification(applicationContext, title, message)

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
