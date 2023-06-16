package com.pt2.assesment3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catatan")
data class CatatanEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,

    @ColumnInfo(name = "judul")
    var judul: String,

    @ColumnInfo(name = "catatan")
    var catatan: String,

)
