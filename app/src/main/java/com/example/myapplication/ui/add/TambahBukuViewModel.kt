package com.example.myapplication.ui.add

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Buku
import com.example.myapplication.data.BukuRepository

class TambahBukuViewModel : ViewModel() {

    private lateinit var bukuRepository: BukuRepository

    fun init(bukuRepository: BukuRepository) {
        this.bukuRepository = bukuRepository
    }

    fun tambahBuku(buku: Buku) {
        bukuRepository.tambahBuku(buku)
    }
}
