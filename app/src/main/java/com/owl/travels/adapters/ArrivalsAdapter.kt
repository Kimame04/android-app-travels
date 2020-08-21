package com.owl.travels.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owl.travels.R
import com.owl.travels.models.GetArrivalTimes
import com.owl.travels.models.TrainInfo

class ArrivalsAdapter(private val trainInfoList: List<TrainInfo>) : RecyclerView.Adapter<ArrivalsAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.cards_train_arrival, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = trainInfoList[position]
        holder.stationName.text = "hardcoded"
    }

    override fun getItemCount(): Int {
        return trainInfoList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val stationName: TextView
        private val platform1: TextView
        private val platform2: TextView
        private val platform3: TextView
        private val platform4: TextView
        private val platform5: TextView
        private val platform6: TextView
        override fun onClick(view: View) {}

        init {
            val timeGetter= GetArrivalTimes()

            stationName = view.findViewById(R.id.station_title)
            platform1 = view.findViewById(R.id.platform_1)
            platform2 = view.findViewById(R.id.platform_2)
            platform3 = view.findViewById(R.id.platform_3)
            platform4 = view.findViewById(R.id.platform_4)
            platform5 = view.findViewById(R.id.platform_5)
            platform6 = view.findViewById(R.id.platform_6)

            var a = ArrayList<TextView>()
            a.add(platform1)
            a.add(platform2)
            a.add(platform3)
            a.add(platform4)
            a.add(platform5)
            a.add(platform6)

            Thread {
                while (true) {
                    timeGetter.getTimes()
                    @SuppressLint("SetTextI18n")
                    for (i in 0 until timeGetter.trains.size){
                        a[i].text = timeGetter.trains[i].second+": "+timeGetter.trains[i].first.toString()+" mins"
                    }
                    Thread.sleep(2500)
                }
            }.start()
        }
    }
}