package com.owl.travels.models

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements

public class GetArrivalTimes(){
    public var stn:String = "TAM"
    private val viewState:String = """/wEPDwUKMTgzMTU4ODI4Mg9kFgICAw9kFgICAw9kFgJmD2QWBAIBDxYCHgdWaXNpYmxlaBYEZg8PFgIeBFRleHQFAzE2MmRkAgEPD2QWBB4Lb25tb3VzZW92ZXIFJHJldHVybiBjaGFuZ2VCdG5JbWFnZSh0aGlzLCAnb3ZlcicpOx4Kb25tb3VzZW91dAUjcmV0dXJuIGNoYW5nZUJ0bkltYWdlKHRoaXMsICdvdXQnKTtkAgMPFgIfAGcWBAIBDxBkEBVXBlNlbGVjdBBBZG1pcmFsdHkgKE5TMTApDkFsanVuaWVkIChFVzkpEUFuZyBNbyBLaW8gKE5TMTYpDkJhcnRsZXkgKENDMTIpD0JheSBGcm9udCAoQ0UyKQtCZWRvayAoRVc1KRJCaXNoYW4gKENDMTUvTlMxNykPQm9vbiBMYXkgKEVXMjcpFkJvdGFuaWMgR2FyZGVucyAoQ0MxOSkPQnJhZGRlbGwgKE5TMTgpEEJyYXMgQmFzYWggKENDMikMQnVnaXMgKEVXMTIpEUJ1a2l0IEJhdG9rIChOUzIpEkJ1a2l0IEdvbWJhayAoTlMzKRdCdW9uYSBWaXN0YSAoQ0MyMi9FVzIxKRBDYWxkZWNvdHQgKENDMTcpD0NhbmJlcnJhIChOUzEyKRRDaGFuZ2kgQWlycG9ydCAoQ0cyKRVDaGluZXNlIEdhcmRlbiAoRVcyNSkTQ2hvYSBDaHUgS2FuZyAoTlM0KRVDaXR5IEhhbGwgKEVXMTMvTlMyNSkPQ2xlbWVudGkgKEVXMjMpE0NvbW1vbndlYWx0aCAoRVcyMCkMRGFrb3RhIChDQzgpFkRob2J5IEdoYXV0IChDQzEvTlMyNCkMRG92ZXIgKEVXMjIpD0VzcGxhbmFkZSAoQ0MzKQtFdW5vcyAoRVc3KQpFeHBvIChDRzEpEkZhcnJlciBSb2FkIChDQzIwKRFHVUwgQ2lyY2xlIChFVzMwKRNIYXJib3VyRnJvbnQgKENDMjkpFEhhdyBQYXIgVmlsbGEgKENDMjUpFkhvbGxhbmQgVmlsbGFnZSAoQ0MyMSkPSm9vIEtvb24gKEVXMjkpFkp1cm9uZyBFYXN0IChFVzI0L05TMSkOS2FsbGFuZyAoRVcxMCkPS2VtYmFuZ2FuIChFVzYpEUtlbnQgUmlkZ2UgKENDMjQpDUtoYXRpYiAoTlMxNCkMS3JhbmppIChOUzYpFExhYnJhZG9yIFBhcmsgKENDMjcpD0xha2VzaWRlIChFVzI2KQ9MYXZlbmRhciAoRVcxMSkTTG9yb25nIENodWFuIChDQzE0KRFNYWNQaGVyc29uIChDQzEwKRVNYXJpbmEgQmF5IChOUzI3L0NFMSkYTWFyaW5hIFNvdXRoIFBpZXIgKE5TMjgpD01hcnNpbGluZyAoTlM3KRBNYXJ5bW91bnQgKENDMTYpEU1vdW50YmF0dGVuIChDQzcpDU5ld3RvbiAoTlMyMSkUTmljb2xsIEhpZ2h3YXkgKENDNSkNTm92ZW5hIChOUzIwKRBPbmUtTm9ydGggKENDMjMpDk9yY2hhcmQgKE5TMjIpEk91dHJhbSBQYXJrIChFVzE2KRRQYXNpciBQYW5qYW5nIChDQzI2KQ9QYXNpciBSaXMgKEVXMSkUUGF5YSBMZWJhciAoQ0M5L0VXOCkOUGlvbmVlciAoRVcyOCkPUHJvbWVuYWRlIChDQzQpEVF1ZWVuc3Rvd24gKEVXMTkpGVJhZmZsZXMgUGxhY2UgKEVXMTQvTlMyNikOUmVkaGlsbCAoRVcxOCkQU2VtYmF3YW5nIChOUzExKRBTZXJhbmdvb24gKENDMTMpC1NpbWVpIChFVzMpD1NvbWVyc2V0IChOUzIzKQ1TdGFkaXVtIChDQzYpD1RhaSBTZW5nIChDQzExKQ5UYW1waW5lcyAoRVcyKRFUYW5haCBNZXJhaCAoRVc0KRRUYW5qb25nIFBhZ2FyIChFVzE1KRRUZWxvayBCbGFuZ2FoIChDQzI4KRJUaW9uZyBCYWhydSAoRVcxNykQVG9hIFBheW9oIChOUzE5KRRUdWFzIENyZXNjZW50IChFVzMxKRBUdWFzIExpbmsgKEVXMzMpFVR1YXMgV2VzdCBSb2FkIChFVzMyKRRXb29kbGFuZHMgKFRFTDIvTlM4KRZXb29kbGFuZHMgTm9ydGggKFRFTDEpFldvb2RsYW5kcyBTb3V0aCAoVEVMMykNWWV3IFRlZSAoTlM1KRNZaW8gQ2h1IEthbmcgKE5TMTUpDVlpc2h1biAoTlMxMykVVwZTZWxlY3QDQURNA0FMSgNBTUsEQ0JMWQRDQkZUA0JESwRDQlNIA0JOTARDQlROA0JETARDQkJTA0JHUwNCQlQDQkdCBENCTlYEQ0NEVANDQlIDQ0dBA0NORwNDQ0sDQ1RIA0NMRQNDT00EQ0RLVARDREJHA0RWUgRDRVBOA0VVTgNYUE8EQ0ZSUgNHQ0wEQ0hCRgRDSFBWBENITFYDSktOA0pVUgNLQUwDS0VNBENLUkcDS1RCA0tSSgRDTEJEA0xLUwNMVlIEQ0xSQwRDTVBTBENNUkIDTVNQA01TTARDTVJNBENNQlQDTkVXBENOQ0gDTk9WBENPTkgDT1JDA09UUARDUFBKA1BTUgRDUFlMA1BOUgRDUE1OA1FVRQNSRlADUkRIA1NCVwRDU0VSA1NJTQNTT00EQ1NETQRDVFNHA1RBTQNUTk0DVFBHBENUTEIDVElCA1RBUANUQ1IDVExLA1RXUgRUV0RMBFRXRE4EVFdEUwNZV1QDWUNLA1lJUxQrA1dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2cWAQIMZAIDDw8WAh8AZ2QWBAIBDw8WBB8BBaUZPGRpdiBjbGFzcz0nZHJkcmFsbCBtYXJnaW50b3AxMCBtYXJnaW5ib3R0MTAgcEIxMCc+PHRhYmxlIGJvcmRlcj0nMHB4JyBjZWxscGFkZGluZz0nMCcgY2VsbHNwYWNpbmc9JzAnIHN0eWxlPSdib3JkZXI6IG5vbmU7Jz48dHI+PHRkPjxwIGNsYXNzPSdib2xkVHh0IGRhcmtncmV5IG1hcmdpbnRvcDEwIG1hcmdpbmJvdHQxMCc+PGI+QnVnaXMgKEVXMTIpIE1SVCBTdGF0aW9uPC9iPjwvcD48L3RkPjwvdHI+PHRyIGNsYXNzPSdub2JyZHInPjx0ZCBhbGlnbj0nY2VudGVyJyBjbGFzcz0nbm9icmRyJz48dGFibGUgYm9yZGVyPScwcHgnIGNlbGxwYWRkaW5nPScwJyBjZWxsc3BhY2luZz0nMCcgY2xhc3M9J25vYnJkcicgc3R5bGU9J3RleHQtYWxpZ246Y2VudGVyO2JhY2tncm91bmQtY29sb3I6V2hpdGU7Jz48dHIgYWxpZ249J2NlbnRlcicgc3R5bGU9J3RleHQtYWxpZ246Y2VudGVyOycgY2xhc3M9J25vYnJkcic+PHRkIGFsaWduPSdjZW50ZXInIGNsYXNzPSdub2JyZHInIHN0eWxlPSd0ZXh0LWFsaWduOmNlbnRlcjtiYWNrZ3JvdW5kLWNvbG9yOldoaXRlOyc+PHAgc3R5bGU9J3RleHQtYWxpZ246Y2VudGVyO2JhY2tncm91bmQtY29sb3I6V2hpdGU7JyBhbGlnbj0nY2VudGVyJyBjbGFzcz0nbm9icmRyJz48ZGl2IHN0eWxlPSJ3aWR0aDoxMDAlOyI+DQoJPHNwYW4gc3R5bGU9ImRpc3BsYXk6aW5saW5lLWJsb2NrO2NvbG9yOkZvcmVzdEdyZWVuO2ZvbnQtc2l6ZTpMYXJnZTtmb250LXdlaWdodDpib2xkO3dpZHRoOjM5MnB4OyI+RWFzdC1XZXN0IExpbmUgKEVXTCk8YnIgLz48YnIgLz48L3NwYW4+PHNwYW4gc3R5bGU9ImRpc3BsYXk6aW5saW5lLWJsb2NrO2NvbG9yOkJsYWNrO2ZvbnQtd2VpZ2h0OmJvbGQ7d2lkdGg6MzkycHg7Ij48cCBjbGFzcz0nYWxpZ24tbGVmdCc+Jm5ic3A7IEVXTCBpbiB0aGUgZGlyZWN0aW9uIG9mIFBhc2lyIFJpczxiciAvPjxiciAvPjwvcD48L3NwYW4+PGRpdj4NCgkJPHRhYmxlIGNlbGxzcGFjaW5nPSIwIiBjZWxscGFkZGluZz0iNCIgYWxpZ249IkNlbnRlciIgcnVsZXM9ImNvbHMiIGJvcmRlcj0iMSIgaWQ9Imd2VGltZSIgc3R5bGU9ImNvbG9yOkJsYWNrO2JhY2tncm91bmQtY29sb3I6V2hpdGU7Ym9yZGVyLWNvbG9yOiNDQ0NDQ0M7Ym9yZGVyLXdpZHRoOjFweDtib3JkZXItc3R5bGU6U29saWQ7Zm9udC1zaXplOjEwcHQ7d2lkdGg6MzUwcHg7Ym9yZGVyLWNvbGxhcHNlOmNvbGxhcHNlOyI+DQoJCQk8dHIgc3R5bGU9ImNvbG9yOldoaXRlO2JhY2tncm91bmQtY29sb3I6IzMzMzMzMztoZWlnaHQ6MzJweDsiPg0KCQkJCTx0aCBzY29wZT0iY29sIj5OZXh0IE1SVDwvdGg+PHRoIHNjb3BlPSJjb2wiPlN1YnNlcXVlbnQgTVJUPC90aD4NCgkJCTwvdHI+PHRyIGFsaWduPSJjZW50ZXIiIHN0eWxlPSJmb250LXdlaWdodDpib2xkO2hlaWdodDoyOHB4OyI+DQoJCQkJPHRkIHN0eWxlPSJ3aWR0aDoxNzVweDsiPjIgbWluKHMpPC90ZD48dGQgc3R5bGU9IndpZHRoOjE3NXB4OyI+NSBtaW4ocyk8L3RkPg0KCQkJPC90cj48dHIgYWxpZ249ImNlbnRlciIgc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQ7aGVpZ2h0OjI4cHg7Ij4NCgkJCQk8dGQ+UGFzaXIgUmlzPC90ZD48dGQ+UGFzaXIgUmlzPC90ZD4NCgkJCTwvdHI+DQoJCTwvdGFibGU+DQoJPC9kaXY+PHNwYW4+PGJyIC8+PC9zcGFuPjxzcGFuIHN0eWxlPSJkaXNwbGF5OmlubGluZS1ibG9jazt3aWR0aDozOTJweDsiPjxwIGNsYXNzPSdhbGlnbi1sZWZ0Jz48YSBocmVmPSdodHRwOi8vam91cm5leS5zbXJ0LmNvbS5zZy9qb3VybmV5L3N0YXRpb25faW5mby9idWdpcy9tYXAvJyB0YXJnZXQ9J19ibGFuayc+PGI+Q2xpY2sgaGVyZTwvYj48L2E+IGZvciBmaXJzdCB0cmFpbi9sYXN0IHRyYWluIHRpbWluZ3MuPC9wPjwvc3Bhbj48c3BhbiBzdHlsZT0iZGlzcGxheTppbmxpbmUtYmxvY2s7Y29sb3I6TmF2eTtmb250LXdlaWdodDpib2xkO2ZvbnQtc3R5bGU6aXRhbGljO3dpZHRoOjM5MnB4OyI+PGJyIC8+PGJyIC8+PC9zcGFuPjxzcGFuIHN0eWxlPSJkaXNwbGF5OmlubGluZS1ibG9jaztjb2xvcjpCbGFjaztmb250LXdlaWdodDpib2xkO3dpZHRoOjM5MnB4OyI+PHAgY2xhc3M9J2FsaWduLWxlZnQnPiZuYnNwOyBFV0wgaW4gdGhlIGRpcmVjdGlvbiBvZiBKb28gS29vbjxiciAvPjxiciAvPjwvcD48L3NwYW4+PGRpdj4NCgkJPHRhYmxlIGNlbGxzcGFjaW5nPSIwIiBjZWxscGFkZGluZz0iNCIgYWxpZ249IkNlbnRlciIgcnVsZXM9ImNvbHMiIGJvcmRlcj0iMSIgaWQ9Imd2VGltZSIgc3R5bGU9ImNvbG9yOkJsYWNrO2JhY2tncm91bmQtY29sb3I6V2hpdGU7Ym9yZGVyLWNvbG9yOiNDQ0NDQ0M7Ym9yZGVyLXdpZHRoOjFweDtib3JkZXItc3R5bGU6U29saWQ7Zm9udC1zaXplOjEwcHQ7d2lkdGg6MzUwcHg7Ym9yZGVyLWNvbGxhcHNlOmNvbGxhcHNlOyI+DQoJCQk8dHIgc3R5bGU9ImNvbG9yOldoaXRlO2JhY2tncm91bmQtY29sb3I6IzMzMzMzMztoZWlnaHQ6MzJweDsiPg0KCQkJCTx0aCBzY29wZT0iY29sIj5OZXh0IE1SVDwvdGg+PHRoIHNjb3BlPSJjb2wiPlN1YnNlcXVlbnQgTVJUPC90aD4NCgkJCTwvdHI+PHRyIGFsaWduPSJjZW50ZXIiIHN0eWxlPSJmb250LXdlaWdodDpib2xkO2hlaWdodDoyOHB4OyI+DQoJCQkJPHRkIHN0eWxlPSJ3aWR0aDoxNzVweDsiPjIgbWluKHMpPC90ZD48dGQgc3R5bGU9IndpZHRoOjE3NXB4OyI+MSBtaW4ocyk8L3RkPg0KCQkJPC90cj48dHIgYWxpZ249ImNlbnRlciIgc3R5bGU9ImZvbnQtd2VpZ2h0OmJvbGQ7aGVpZ2h0OjI4cHg7Ij4NCgkJCQk8dGQ+Sm9vIEtvb248L3RkPjx0ZD5UdWFzIExpbms8L3RkPg0KCQkJPC90cj4NCgkJPC90YWJsZT4NCgk8L2Rpdj48c3Bhbj48YnIgLz48L3NwYW4+PHNwYW4gc3R5bGU9ImRpc3BsYXk6aW5saW5lLWJsb2NrO3dpZHRoOjM5MnB4OyI+PHAgY2xhc3M9J2FsaWduLWxlZnQnPjxhIGhyZWY9J2h0dHA6Ly9qb3VybmV5LnNtcnQuY29tLnNnL2pvdXJuZXkvc3RhdGlvbl9pbmZvL2J1Z2lzL21hcC8nIHRhcmdldD0nX2JsYW5rJz48Yj5DbGljayBoZXJlPC9iPjwvYT4gZm9yIGZpcnN0IHRyYWluL2xhc3QgdHJhaW4gdGltaW5ncy48L3A+PC9zcGFuPjxzcGFuIHN0eWxlPSJkaXNwbGF5OmlubGluZS1ibG9jaztjb2xvcjpOYXZ5O2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zdHlsZTppdGFsaWM7d2lkdGg6MzkycHg7Ij48YnIgLz48YnIgLz48L3NwYW4+DQo8L2Rpdj48L3A+PC90ZD48L3RyPjwvdGFibGU+PC90ZD48L3RyPjwvdGFibGU+PC9kaXY+HwBnZGQCAw8PFgIfAGcWBB8CBSRyZXR1cm4gY2hhbmdlQnRuSW1hZ2UodGhpcywgJ292ZXInKTsfAwUjcmV0dXJuIGNoYW5nZUJ0bkltYWdlKHRoaXMsICdvdXQnKTtkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYBBQtpYnRuUmVmcmVzaCaKKqJH6t7BDaUrGC3+FRHiVghY"""
    public var trains=ArrayList<Pair<Int,String>>()
    private var times= Elements()
    private var dests=Elements()
    private val client = OkHttpClient()
    private var formBody:FormBody = FormBody.Builder()
        .add("ScriptManager1", "UP1|ddlStation")
        .add("__LASTFOCUS","")
        .add("__EVENTTARGET","ddlStation")
        .add("__EVENTARGUMENT","")
        .add("__VIEWSTATE",this.viewState)
        .add("__VIEWSTATEGENERATOR","CA0B0334")
        .add("stnCode","")
        .add("stnName","")
        .add("ddlStation",this.stn)
        .build()

    //change station
    fun refreshFormBody(stn:String){
        this.formBody = FormBody.Builder()
        .add("ScriptManager1", "UP1|ddlStation")
            .add("__LASTFOCUS","")
            .add("__EVENTTARGET","ddlStation")
            .add("__EVENTARGUMENT","")
            .add("__VIEWSTATE",this.viewState)
            .add("__VIEWSTATEGENERATOR","CA0B0334")
            .add("stnCode","")
            .add("stnName","")
            .add("ddlStation",stn)
            .build()

        this.request = Request.Builder()
            .url("http://trainarrivalweb.smrt.com.sg/default.aspx")
            .addHeader("Host","""trainarrivalweb.smrt.com.sg""")
            .addHeader("Accept-Language","""en-US,en;q=0.5""")
            .addHeader("Accept","""*/*""")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Content-Type", """application/x-www-form-urlencoded""")
            .addHeader("Content-Length","7831")
            .addHeader("Origin","""http://trainarrivalweb.smrt.com.sg""")
            .addHeader("Connection","""keep-alive""")
            .addHeader("Referer","""http://trainarrivalweb.smrt.com.sg/default.aspx""")
            .addHeader("cookie","""ASP.NET_SessionId=umv0ktalceechc3ejkykytvd""")
            .post(formBody)
            .build()

        this.stn=stn
        println(stn)
        this.trains=ArrayList()

        getTimes()

    }

    private var request = Request.Builder()
        .url("http://trainarrivalweb.smrt.com.sg/default.aspx")
        .addHeader("Host","""trainarrivalweb.smrt.com.sg""")
        .addHeader("Accept-Language","""en-US,en;q=0.5""")
        .addHeader("Accept","""*/*""")
        .addHeader("Cache-Control", "no-cache")
        .addHeader("Content-Type", """application/x-www-form-urlencoded""")
        .addHeader("Content-Length","7831")
        .addHeader("Origin","""http://trainarrivalweb.smrt.com.sg""")
        .addHeader("Connection","""keep-alive""")
        .addHeader("Referer","""http://trainarrivalweb.smrt.com.sg/default.aspx""")
        .addHeader("cookie","""ASP.NET_SessionId=umv0ktalceechc3ejkykytvd""")
        .post(formBody)
        .build()

    //call this every so often to refresh
    fun getTimes(){
        println(request)
        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    println("failure")

                } else {
                    val s = response.body!!.string()
                    val doc = Jsoup.parse(s)
                    times = doc.getElementsContainingOwnText("""min(s)""")
                    dests =
                        doc.getElementsMatchingOwnText("^((Woodlands North)|(Woodlands South)|(Jurong East)|(Marina South Pier)|(Marina Bay)|(HarbourFront)|(Dhoby Ghaut)|(Boon Lay)|(Tanah Merah)|(Changi Airport)|(Pasir Ris)|(Joo Koon)|(Tuas Link)|(Do Not Board))$")
                    if(this.times.size==0){
                        println("what???")
                        println(s)
                    }
                    else if (this.times.size >= this.dests.size) {
                        println("size matches")
                        trains = ArrayList()
                        for (i in 0 until this.times.size) {
                            trains.add(
                                Pair(
                                    this.times[i].text().replace("[^\\d.]".toRegex(), "").toInt(),
                                    this.dests[i].text()
                                )
                            )
                        }


                        println(trains)

                    } else {
                        println("size does not match")
                    }
                }
            }
        }catch(e:Exception){
            println("failure")
            println(e)
        }
    }

}