package com.example.myapplication.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentEditBinding

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private lateinit var viewModel: EditViewModel
    private var bukuId: Int= 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)

        // Set up data binding
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // Get book ID from arguments (ensure you pass the ID when navigating to this fragment)
        bukuId = arguments?.getInt("bukuId") ?: 0

        // If bukuId is not 0, it means we are editing an existing book
            viewModel.semuaBuku.observe(viewLifecycleOwner) { bukuList ->
                val selectedBuku = bukuList.find { it.id == bukuId }
                selectedBuku?.let {
                    binding.editTextJudul.setText(it.judul)
                    binding.editTextPenulis.setText(it.penulis)
                    binding.checkBoxTersedia.isChecked = it.tersedia
                }
            }

            // Set up Save button for updating existing book
            binding.btnSave.setOnClickListener {
                val judul = binding.editTextJudul.text.toString()
                val penulis = binding.editTextPenulis.text.toString()
                val tersedia = binding.checkBoxTersedia.isChecked
                viewModel.perbaruiBuku(bukuId, judul, penulis, tersedia)
                // Add navigation logic to go back to the book list
                findNavController().navigateUp()
            }

            // Set up Delete button for deleting existing book
            binding.btnDelete.setOnClickListener {
                viewModel.hapusBuku(bukuId)
                findNavController().navigateUp()
                // Add navigation logic to go back to the book list
            }

    }
}
