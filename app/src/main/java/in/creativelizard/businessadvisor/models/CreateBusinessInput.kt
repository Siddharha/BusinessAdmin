package `in`.creativelizard.businessadvisor.models

data class CreateBusinessInput(
    val addresses: String,
    val close_time: String,
    val description: String,
    val email: String,
    val lat_location: Double,
    val lon_location: Double,
    val number: String,
    val open_time: String,
    val title: String,
    val type: String,
    val uder_id: Int,
    val web_address: String
)