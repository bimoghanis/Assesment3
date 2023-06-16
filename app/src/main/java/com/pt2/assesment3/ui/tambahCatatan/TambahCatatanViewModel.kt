package com.pt2.assesment3.ui.tambahCatatan

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.pt2.assesment3.db.CatatanDao
import com.pt2.assesment3.db.CatatanDb
import com.pt2.assesment3.db.CatatanEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TambahCatatanViewModel(application: Application) : AndroidViewModel(application) {
    private val catatanDao: CatatanDao = CatatanDb.getDatabase(application).catatanDao()


    fun getAllCatatan(): LiveData<List<CatatanEntity>> {
        return catatanDao.getAllCatatan()
    }

    fun deleteCatatan(catatan: CatatanEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            catatanDao.deleteCatatan(catatan)
        }
    }

    fun submitComment(
        judul: String,
        catatan: String
    ) {
        val catatan = CatatanEntity(
            judul = judul,
            catatan = catatan
        )
        // Menampilkan Konfirmasi berhasil
        viewModelScope.launch(Dispatchers.IO) {
            catatanDao.insert(catatan)
            withContext(Dispatchers.Main) {
                Toast.makeText(getApplication(), "Catatan berhasil disimpan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
