package com.pt2.assesment3.ui.solat

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pt2.assesment3.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class JadwalSolatFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_jadwalsolat, container, false)

        val apiUrl = "https://raw.githubusercontent.com/lakuapik/jadwalsholatorg/master/adzan/bandung/2022/12.json"

        val fajrTextView = view.findViewById<TextView>(R.id.fajrTextView)
        val dhuhrTextView = view.findViewById<TextView>(R.id.dhuhrTextView)
        val asrTextView = view.findViewById<TextView>(R.id.asrTextView)
        val maghribTextView = view.findViewById<TextView>(R.id.maghribTextView)
        val ishaTextView = view.findViewById<TextView>(R.id.ishaTextView)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val url = URL(apiUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()

                val responseData = response.toString()
                val jsonArray = JSONArray(responseData)

                val jsonObject = jsonArray.getJSONObject(0)

                val fajrTime = jsonObject.getString("shubuh")
                val dhuhrTime = jsonObject.getString("dzuhur")
                val asrTime = jsonObject.getString("ashr")
                val maghribTime = jsonObject.getString("magrib")
                val ishaTime = jsonObject.getString("isya")

                fajrTextView.text = "Fajr: $fajrTime"
                dhuhrTextView.text = "Dhuhr: $dhuhrTime"
                asrTextView.text = "Asr: $asrTime"
                maghribTextView.text = "Maghrib: $maghribTime"
                ishaTextView.text = "Isha: $ishaTime"

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("API Request", "Error: ${e.printStackTrace()}")
            }
        }


        return view
    }

}
