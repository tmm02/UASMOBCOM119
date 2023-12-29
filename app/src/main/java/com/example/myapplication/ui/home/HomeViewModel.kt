package com.example.myapplication.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.Buku
import com.example.myapplication.data.BukuRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    // Inisialisasi repository sesuai kebutuhan dengan Application context
    private val bukuRepository: BukuRepository = BukuRepository(application)

    // LiveData untuk menyimpan daftar semua buku
    val semuaBuku: LiveData<List<Buku>> = bukuRepository.ambilSemuaBuku()

    fun refreshDataBuku() {
        bukuRepository.refreshDataBuku()
    }
}
