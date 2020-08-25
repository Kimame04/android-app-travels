package com.owl.travels.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owl.travels.R
import com.owl.travels.models.GetTrafficIncidents


class IncidentsAdapter(private val incidentsList: Array<String>) : RecyclerView.Adapter<IncidentsAdapter.ViewHolder>(){
    val incidents = GetTrafficIncidents()
    private var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.cards_traffic_incidents, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val info = timeGetter.trains[position]
        holder.desc.text = incidentsList[position]
    }

    override fun getItemCount(): Int {
        return incidentsList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val desc: TextView

        override fun onClick(view: View) {}

        init {
            desc = view.findViewById(R.id.incident_description)

            Thread {
                while (true) {
                    incidents.getService()
                    @SuppressLint("SetTextI18n")
                    for (i in 0 until incidentsList.size){
                        //desc.text = incidents.incidentsList[i].toString()
                        print(incidentsList[i].toString())
                    }
                    Thread.sleep(2500)
                }
            }.start()

            var a = ArrayList<TextView>()


        }
    }
}