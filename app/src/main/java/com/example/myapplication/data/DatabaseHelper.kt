package com.example.myapplication.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "perpustakaan_kampus.db"
        private const val DATABASE_VERSION = 4

        // Tabel buku
        const val TABLE_BUKU = "buku"
        const val COLUMN_ID = "_id"
        const val COLUMN_JUDUL = "judul"
        const val COLUMN_PENULIS = "penulis"
        const val COLUMN_TERSEDIA = "tersedia"
    }

    // Membuat tabel buku
    private val CREATE_TABLE_BUKU = (
            "CREATE TABLE $TABLE_BUKU ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_JUDUL TEXT, $COLUMN_PENULIS TEXT, $COLUMN_TERSEDIA INTEGER);")

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_BUKU)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BUKU")
        onCreate(db)
    }
}
