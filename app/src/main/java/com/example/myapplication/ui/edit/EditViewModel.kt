package com.example.myapplication.ui.edit


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.Buku
import com.example.myapplication.data.BukuRepository

class EditViewModel(application: Application) : AndroidViewModel(application) {

    private val bukuRepository: BukuRepository = BukuRepository(application)
    val semuaBuku: LiveData<List<Buku>> = bukuRepository.ambilSemuaBuku()

    fun tambahBuku(judul: String, penulis: String, tersedia: Boolean) {
        val buku = Buku(0, judul, penulis, tersedia)
        bukuRepository.tambahBuku(buku)
    }

    fun perbaruiBuku(id: Int, judul: String, penulis: String, tersedia: Boolean) {
        val buku = Buku(id, judul, penulis, tersedia)
        bukuRepository.perbaruiBuku(buku)
    }

    fun hapusBuku(id:Int) {
        bukuRepository.hapusBuku(id)
    }
}
