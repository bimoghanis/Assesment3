package com.pt2.assesment3.ui.deletecatatan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pt2.assesment3.R
import com.pt2.assesment3.ui.tambahCatatan.CatatanAdapter
import com.pt2.assesment3.ui.tambahCatatan.TambahCatatanViewModel


class DeleteCatatanFragment : Fragment() {
    private lateinit var recyclerViewCatatan: RecyclerView
    private lateinit var catatanAdapter: CatatanAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listcatatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RecyclerView dan adapter
        recyclerViewCatatan = view.findViewById(R.id.recyclerViewCatatan)
        catatanAdapter = CatatanAdapter()

        // Atur layout manager untuk RecyclerView
        recyclerViewCatatan.layoutManager = LinearLayoutManager(requireContext())

        // Atur adapter ke RecyclerView
        recyclerViewCatatan.adapter = catatanAdapter

        // Panggil method untuk mengambil data ulasan dari ViewModel
        observeCatatan()
    }

    private fun observeCatatan() {
        val catatanViewModel =
            ViewModelProvider(requireActivity()).get(TambahCatatanViewModel::class.java)
        catatanViewModel.getAllCatatan().observe(viewLifecycleOwner) { catatanList ->
            // Set data ulasan ke adapter
            catatanAdapter.submitList(catatanList)
        }

        catatanAdapter.setOnItemClickListener { catatan ->
            catatanViewModel.deleteCatatan(catatan)
            val pesan = "Berhasil menghapus catatan 👍"
            Toast.makeText(requireContext(), pesan, Toast.LENGTH_SHORT).show()
        }

    }
}


