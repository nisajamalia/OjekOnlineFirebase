package com.nisa.ojekonlinefirebase.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nisa.ojekonlinefirebase.R
import com.nisa.ojekonlinefirebase.network.Booking
import com.nisa.ojekonlinefirebase.ui.dashboard.adapter.HistoryAdapter
import com.nisa.ojekonlinefirebase.utils.Constan
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.lang.IllegalStateException

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var auth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
         R.layout.fragment_dashboard, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        auth?.uid?.let { bookingHistoryUser(it) }
    }

    //mengambil data dari firebase
    private fun bookingHistoryUser(uid:String) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(Constan.tb_booking)

        val data = ArrayList<Booking>()
        //orderbychild -> sesuai urutan
        var query = myRef.orderByChild("uid")
            .equalTo(uid)

        query.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (issue in snapshot.children){
                    //get value -> mengambil data dari firebasenya
                    val dataFirebase = issue.getValue(Booking::class.java)
                    val booking = Booking()

                    booking.tanggal = dataFirebase?.tanggal
                    booking.uid = dataFirebase?.uid
                    booking.lokasiAwal = dataFirebase?.uid
                    booking.latAwal = dataFirebase?.latAwal
                    booking.lonAwal = dataFirebase?.lonAwal
                    booking.latTujuan = dataFirebase?.latTujuan
                    booking.lonTujuan = dataFirebase?.lonTujuan
                    booking.lokasiTujuan = dataFirebase?.lokasiTujuan
                    booking.jarak = dataFirebase?.jarak
                    booking.harga = dataFirebase?.harga
                    booking.status = dataFirebase?.status

                    data.add(booking)
                    showdata(data)
                }
            }

        })
    }

    //ngeset data recyclerview dari adapter
    private fun showdata(data: java.util.ArrayList<Booking>) {

        if (data != null) {
            try {
                rv1.adapter = HistoryAdapter(data)
                rv1.layoutManager = LinearLayoutManager(context)
            } catch (e: IllegalStateException){

            }

        }


    }
}