package `in`.creativelizard.businessadvisor.models.networkModels

data class GetBusinessOutput(
    val business_card: BusinessCard,
    val message: String,
    val success: Int
)

data class BusinessCard(
    val addresses: String,
    val close_time: String,
    val description: String,
    val email: String,
    val id: String,
    val lat_location: String,
    val lon_location: String,
    val long_description: String,
    val number: String,
    val open_time: String,
    val profile_img: String,
    val title: String,
    val type: String,
    val web_address: String
)