package com.pt2.assesment3.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatatanDao {

    @Insert
    fun insert(catatan : CatatanEntity)

    @Query("SELECT * FROM catatan ORDER BY id DESC")
    fun getAllCatatan(): LiveData<List<CatatanEntity>>

    @Delete
    open fun deleteCatatan(ulasan: CatatanEntity?)

}