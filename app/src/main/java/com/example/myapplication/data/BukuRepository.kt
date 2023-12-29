package com.example.myapplication.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BukuRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val _semuaBuku = MutableLiveData<List<Buku>>()

    fun tambahBuku(buku: Buku) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_JUDUL, buku.judul)
            put(DatabaseHelper.COLUMN_PENULIS, buku.penulis)
            put(DatabaseHelper.COLUMN_TERSEDIA, buku.tersedia) // Add isAvailable to ContentValues
        }
        db.insert(DatabaseHelper.TABLE_BUKU, null, values)
        db.close()
        refreshDataBuku()
    }

    @SuppressLint("Range")
    fun ambilSemuaBuku(): LiveData<List<Buku>> {
        refreshDataBuku()
        return _semuaBuku
    }

    @SuppressLint("Range")
    fun refreshDataBuku() {
        val bukus = mutableListOf<Buku>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_BUKU}", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
                val judul = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_JUDUL))
                val penulis = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PENULIS))
                val tersedia = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_TERSEDIA)) == 1

                val buku = Buku(id, judul, penulis, tersedia)
                bukus.add(buku)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        _semuaBuku.value = bukus
    }

    fun perbaruiBuku(buku: Buku) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_JUDUL, buku.judul)
            put(DatabaseHelper.COLUMN_PENULIS, buku.penulis)
            put(DatabaseHelper.COLUMN_TERSEDIA, buku.tersedia) // Update isAvailable in ContentValues
        }
        db.update(
            DatabaseHelper.TABLE_BUKU,
            values,
            "${DatabaseHelper.COLUMN_ID} = ?",
            arrayOf(buku.id.toString())
        )
        db.close()
        refreshDataBuku()
    }

    fun hapusBuku(id: Int) {
        val db = dbHelper.writableDatabase
        db.delete(
            DatabaseHelper.TABLE_BUKU,
            "${DatabaseHelper.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
        refreshDataBuku()
    }
}
