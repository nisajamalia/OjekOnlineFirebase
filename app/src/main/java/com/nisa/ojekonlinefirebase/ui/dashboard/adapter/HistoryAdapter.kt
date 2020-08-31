package com.nisa.ojekonlinefirebase.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nisa.ojekonlinefirebase.R
import com.nisa.ojekonlinefirebase.network.Booking
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryAdapter (
    private val mValues:List<Booking>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    //memanggil layout item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)

        return ViewHolder(view)
    }
    //menghitung jumlah item
    override fun getItemCount(): Int = mValues.size



//mengisi view dengan data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mValues[position]

        holder.mAwal.text = item.lokasiAwal
        holder.mTanggal.text = item.tanggal
        holder.mTujuan.text = item.lokasiTujuan
    }

    //menginisialisasi
    inner class ViewHolder(mView: View)
        :RecyclerView.ViewHolder(mView){

        val mAwal : TextView = mView.item_awal
        val mTujuan : TextView = mView.item_tujuan
        val mTanggal : TextView = mView.item_tanggal
    }
}