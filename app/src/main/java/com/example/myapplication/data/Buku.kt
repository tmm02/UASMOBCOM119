package com.example.myapplication.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Buku(
    val id: Int,
    val judul: String,
    val penulis: String,
    val tersedia: Boolean
) : Parcelable
