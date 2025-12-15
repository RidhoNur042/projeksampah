package com.ridho.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ridho.project.model.ModelDatabase
import com.ridho.project.utils.FunctionHelper.rupiahFormat
import java.util.ArrayList


class RiwayatAdapter(
    private val mContext: Context,
    modelInputList: MutableList<ModelDatabase>,
    adapterCallback: RiwayatAdapterCallback
) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    private val modelDatabase: MutableList<ModelDatabase> = modelInputList
    private val mAdapterCallback: RiwayatAdapterCallback = adapterCallback

    fun setDataAdapter(items: List<ModelDatabase>) {
        modelDatabase.clear()
        modelDatabase.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_riwayat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: ModelDatabase = modelDatabase[position]

        holder.tvNama.text = data.namaPengguna
        holder.tvDate.text = data.tanggal
        holder.tvKategori.text = "Sampah ${data.jenisSampah}"
        holder.tvBerat.text = "Berat : ${data.berat} Kg"
        holder.tvSaldo.text = "Pendapatan : ${rupiahFormat(data.harga)}"

        if (data.berat < 5.0) {
            holder.tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.red))
            holder.tvStatus.text = "Masih dalam proses"
        } else {
            // Pastikan Anda memiliki resource warna 'colorPrimary' di R.color.colorPrimary
            holder.tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
            holder.tvStatus.text = "Sudah di konfirmasi"
        }
    }

    override fun getItemCount(): Int {
        return modelDatabase.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.findViewById(R.id.tvNama)
        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var tvKategori: TextView = itemView.findViewById(R.id.tvKategori)
        var tvBerat: TextView = itemView.findViewById(R.id.tvBerat)
        var tvSaldo: TextView = itemView.findViewById(R.id.tvSaldo)
        var tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        var imageDelete: ImageView = itemView.findViewById(R.id.imageDelete)

        init {
            imageDelete.setOnClickListener { view: View? ->
                val modelLaundry: ModelDatabase = modelDatabase[adapterPosition]
                mAdapterCallback.onDelete(modelLaundry)
            }
        }
    }

    interface RiwayatAdapterCallback {
        fun onDelete(modelDatabase: ModelDatabase?)
    }

}