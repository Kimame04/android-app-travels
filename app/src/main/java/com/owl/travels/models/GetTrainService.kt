package com.owl.travels.models

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements

public class GetTrainService(){
    public var disruption:Boolean=false
    public var lines:String=""
    public var direction:String=""
    public var station:String=""
    public var publicBus:String=""
    public var shuttleBus:String=""
    public var shuttleDirn:String=""
    public var message:String=""
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
                    if (doc.attr("Status")=="1"){
                        disruption=false
                    }else{
                        disruption = true
                        lines=doc.attr("Line")
                        direction=doc.attr("Direction")
                        station=doc.attr("Stations")
                        publicBus=doc.attr("FreePublicBus")
                        shuttleBus=doc.attr("FreeMRTShuttle")
                        shuttleDirn=doc.attr("MRTShuttleDirection")
                        message=doc.attr("Message")
                    }
                }
            }
        }catch(e:Exception){
            println("failure")
            println(e)
        }
    }

}