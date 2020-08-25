package com.owl.travels.models

import android.os.StrictMode
import com.owl.travels.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


public class GetTrafficIncidents(){
    var array:ArrayList<Incident> = ArrayList<Incident>()
    var arr:ArrayList<String> = ArrayList()
    private val client = OkHttpClient()

    private var request = Request.Builder()
            .url("""http://datamall2.mytransport.sg/ltaodataservice/TrafficIncidents""")
            .addHeader("AccountKey", BuildConfig.ltaapi)
            .build()

    fun getService(){
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

            StrictMode.setThreadPolicy(policy)
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("failure to receive")

                } else {
                    array.clear()
                    val s = response.body!!.string()
                    println(s)
                    println("before")
                    val v = JSONObject(s).getJSONArray("value")
                    for (i in 0 until v.length()){
                        val a = v.getJSONObject(i)
                        array.add(Incident(a.getString("Type"), a.getDouble("Latitude"), a.getDouble("Longitude"), a.getString("Message")))
                        arr.add(a.getString("Message"))
                    }
                    print(array[0])
                }
            }
        }catch (e: Exception){
            println("nani?!")
            println(e.printStackTrace())
        }
    }

}
data class Incident(val type: String, val Latitude: Double, val Longitude: Double, val message: String)