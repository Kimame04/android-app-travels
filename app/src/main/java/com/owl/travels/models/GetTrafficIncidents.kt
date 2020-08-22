package com.owl.travels.models

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

public class GetTrafficIncidents(){
    public var type:String=""
    public var message:String=""
    public var lat:String=""
    public var long:String=""
    private val client = OkHttpClient()

    private var request = Request.Builder()
            .url("""http://datamall2.mytransport.sg/ltaodataservice/TrainServiceAlerts""")
            .addHeader("AccountKey","""pck4tGIbTny/klvKJefcNw==""")
            .build()

    fun getService(){
        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("failure")

                } else {
                    val s = response.body!!.string()
                    val doc = Jsoup.parse(s)
                    type=doc.attr("Type")
                    message=doc.attr("Message")
                    lat=doc.attr("Latitude")
                    long=doc.attr("Longitude")
                }
            }
        }catch(e:Exception){
            println("failure")
            println(e.printStackTrace())
        }
    }

}