package com.pt2.assesment3.ui.tambahCatatan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pt2.assesment3.R
import com.pt2.assesment3.db.CatatanEntity

class CatatanAdapter : ListAdapter<CatatanEntity, CatatanAdapter.CatatanViewHolder>(UlasanDiffCallback()) {

    private var onItemClick: ((CatatanEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (CatatanEntity) -> Unit) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatatanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_catatan, parent, false)
        return CatatanViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatatanViewHolder, position: Int) {
        val catatan = getItem(position)
        holder.bind(catatan)
    }

    inner class CatatanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewJudulCatatan: TextView = itemView.findViewById(R.id.textViewJudulCatatan)
        private val textViewIsiCatatan: TextView = itemView.findViewById(R.id.textViewIsiCatatan)


        fun bind(catatan: CatatanEntity) {
            textViewJudulCatatan.text = catatan.judul
            textViewIsiCatatan.text = catatan.catatan
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val catatan = getItem(position)
                    onItemClick?.invoke(catatan)
                }
            }
        }
    }

    private class UlasanDiffCallback : DiffUtil.ItemCallback<CatatanEntity>() {
        override fun areItemsTheSame(oldItem: CatatanEntity, newItem: CatatanEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatatanEntity, newItem: CatatanEntity): Boolean {
            return oldItem == newItem
        }
    }
}
