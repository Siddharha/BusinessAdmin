package `in`.creativelizard.businessadvisor.models.networkModels

data class LoginOutput(
    val message: String,
    val success: Int,
    val user: User
)

data class User(
    val email: String,
    val id: String,
    val name: String
)