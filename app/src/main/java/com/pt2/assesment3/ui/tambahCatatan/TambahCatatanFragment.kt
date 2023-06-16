package com.pt2.assesment3.ui.tambahCatatan


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.pt2.assesment3.R
import com.pt2.assesment3.ui.listcatatan.ListCatatanFragment


class TambahCatatanFragment : Fragment() {

    private lateinit var viewModel: TambahCatatanViewModel
    private lateinit var editTextJudulCatatan: EditText
    private lateinit var editTextIsiCatatan: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_tambah, container, false)

        viewModel = ViewModelProvider(this).get(TambahCatatanViewModel::class.java)

        editTextJudulCatatan = rootView.findViewById(R.id.editTextJudulCatatan)
        editTextIsiCatatan = rootView.findViewById(R.id.editTextIsiCatatan)
        buttonSubmit = rootView.findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            submitCatatan()
        }

        return rootView
    }

    private fun submitCatatan() {
        val judulCatatan = editTextJudulCatatan.text.toString()
        val isiCatatan = editTextIsiCatatan.text.toString()

        viewModel.submitComment(judulCatatan, isiCatatan)

        // Reset form setelah submit
        editTextJudulCatatan.text.clear()
        editTextIsiCatatan.text.clear()
    }

}
