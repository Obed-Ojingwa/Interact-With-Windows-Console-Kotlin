import java.io.BufferedReader
import java.io.InputStreamReader

fun getWifiProfiles(): List<String> {
    val wifiProfiles = mutableListOf<String>()
    try {


        // Execute the netsh command to list Wi-Fi profiles
        val process = Runtime.getRuntime().exec("netsh wlan show profiles")

        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?

        // Iterate over the output and extract profile names
        while (reader.readLine().also { line = it } != null) {
            println("Hello" + line)

            // showing them passwords
            val process2 = Runtime.getRuntime().exec("netsh wlan show profiles "+line+" key=clear")
            // Profiles are displayed with "All User Profile" as a prefix
            if (line!!.contains("All User Profile")) {
                // Extract the profile name after the colon
                val profileName = line!!.substringAfter(":").trim()
                wifiProfiles.add(profileName)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return wifiProfiles
}

fun main() {
    val profiles = getWifiProfiles()
/*
    val profiles2 = getWifiProfiles()
*/
    println("Stored Wi-Fi Profiles: ")
    profiles.forEach { profile -> println(profile) }
}
