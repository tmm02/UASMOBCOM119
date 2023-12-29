package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView
        val adapter = BukuAdapter()

        // Set up item click listener for RecyclerView
        adapter.setOnItemClickListener { buku ->
            // Navigate to EditFragment with the book ID as an argument
            val bundle = Bundle().apply {
                putInt("bukuId", buku.id)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_editFragment,
                bundle
            )
        }
        binding.fabCreate.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tambahBukuFragment)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Inisialisasi TextView untuk menampilkan pesan jika tidak ada data
        val noDataTextView = binding.noDataTextView

        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Observing LiveData dari ViewModel untuk mendapatkan data buku
        viewModel.semuaBuku.observe(viewLifecycleOwner, Observer { bukuList ->
            if (bukuList.isEmpty()) {
                // Tampilkan pesan "Tidak ada data buku"
                noDataTextView.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                // Sembunyikan pesan dan tampilkan RecyclerView jika ada data
                noDataTextView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE

                // Update RecyclerView ketika data berubah
                adapter.submitList(bukuList)
            }
        })
        viewModel.refreshDataBuku()
    }
}
