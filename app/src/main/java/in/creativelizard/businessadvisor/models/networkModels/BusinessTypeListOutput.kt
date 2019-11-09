package `in`.creativelizard.businessadvisor.models.networkModels

data class BusinessTypeListOutput(
    val category_array: List<CategoryArray>,
    val message: String,
    val success: Int
)

data class CategoryArray(
    val id: String,
    val name: String
)