package com.owl.travels.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owl.travels.R
import com.owl.travels.models.GetArrivalTimes


class ArrivalsAdapter(private val stationList: Array<String>) : RecyclerView.Adapter<ArrivalsAdapter.ViewHolder>(){
    val timeGetter= GetArrivalTimes()
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.cards_train_arrival, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val info = timeGetter.trains[position]
        holder.stationName.text = stationList[position].subSequence(4, stationList[position].length)
        holder.platform1.text = "N/A"
        holder.platform2.text = "N/A"
        holder.platform3.text = "N/A"
        holder.platform4.text = "N/A"
        holder.platform5.text = "N/A"
        holder.platform6.text = "N/A"
        holder.platform7.text = "N/A"
        holder.platform8.text = "N/A"
        holder.platform9.text = "N/A"
        holder.platform10.text = "N/A"
        holder.platform11.text = "N/A"
        holder.platform12.text = "N/A"
    }

    override fun getItemCount(): Int {
        return stationList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val stationName: TextView
         val platform1: TextView
         val platform2: TextView
         val platform3: TextView
         val platform4: TextView
         val platform5: TextView
         val platform6: TextView
        val platform7: TextView
        val platform8: TextView
        val platform9: TextView
        val platform10: TextView
        val platform11: TextView
        val platform12: TextView
        override fun onClick(view: View) {}

        init {
            stationName = view.findViewById(R.id.station_title)
            platform1 = view.findViewById(R.id.platform_1)
            platform2 = view.findViewById(R.id.platform_2)
            platform3 = view.findViewById(R.id.platform_3)
            platform4 = view.findViewById(R.id.platform_4)
            platform5 = view.findViewById(R.id.platform_5)
            platform6 = view.findViewById(R.id.platform_6)
            platform7 = view.findViewById(R.id.platform_7)
            platform8 = view.findViewById(R.id.platform_8)
            platform9 = view.findViewById(R.id.platform_9)
            platform10 = view.findViewById(R.id.platform_10)
            platform11 = view.findViewById(R.id.platform_11)
            platform12 = view.findViewById(R.id.platform_12)

            var a = ArrayList<TextView>()
            a.add(platform1)
            a.add(platform2)
            a.add(platform3)
            a.add(platform4)
            a.add(platform5)
            a.add(platform6)
            a.add(platform7)
            a.add(platform8)
            a.add(platform9)
            a.add(platform10)
            a.add(platform11)
            a.add(platform12)

            Thread {
                while (true) {
                for (h in stationList.indices){
                    timeGetter.refreshFormBody(stationList[h].substring(0, 3))
                    @SuppressLint("SetTextI18n")
                    for (i in 0 until timeGetter.trains.size){
                        //a[i].setVisibility(View.VISIBLE);
                        a[i].text = timeGetter.trains[i].second+": "+timeGetter.trains[i].first.toString()+" mins"
                    }
                    Thread.sleep(2500)
                }
            }}.start()
        }
    }
}