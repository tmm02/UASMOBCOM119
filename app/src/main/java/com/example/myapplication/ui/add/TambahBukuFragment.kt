package com.example.myapplication.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.Buku
import com.example.myapplication.data.BukuRepository
import com.example.myapplication.databinding.FragmentTambahBukuBinding

class TambahBukuFragment : Fragment() {

    private lateinit var binding: FragmentTambahBukuBinding
    private lateinit var viewModel: TambahBukuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTambahBukuBinding.inflate(inflater, container, false)

        // Initialize viewModel before accessing it
        viewModel = ViewModelProvider(this).get(TambahBukuViewModel::class.java)
        viewModel.init(BukuRepository(requireContext()))

        // Bind the viewModel variable to the layout
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // The rest of your onViewCreated code...
        binding.btnAddBuku.setOnClickListener {
            tambahBuku()
        }
    }

    private fun tambahBuku() {
        val judul = binding.editJudul.text.toString()
        val penulis = binding.editPenulis.text.toString()
        val tersedia = binding.checkboxTersedia.isChecked

        if (judul.isNotBlank() && penulis.isNotBlank()) {
            val buku = Buku(id = 0, judul = judul, penulis = penulis, tersedia = tersedia)
            viewModel.tambahBuku(buku)
            findNavController().navigateUp()
        }
    }
}

