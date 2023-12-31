package util

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class LocationManager {
    suspend fun getLocation(): Location {
        return withContext(Dispatchers.IO) {
            val url = URL("https://ipinfo.io/json")
            val json = url.readText()
            val data = Gson().fromJson(json, Location::class.java)
            val location = data.loc
            Location(loc = location)
        }
    }
}

data class Location(val loc: String)