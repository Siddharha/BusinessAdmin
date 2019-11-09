package `in`.creativelizard.businessadvisor.viewModels

import `in`.creativelizard.businessadvisor.interfaces.ApiInterface
import `in`.creativelizard.businessadvisor.models.networkModels.BusinessTypeListOutput
import `in`.creativelizard.businessadvisor.models.networkModels.CategoryArray
import `in`.creativelizard.businessadvisor.models.networkModels.LoginInput
import `in`.creativelizard.businessadvisor.repositories.BusinessPageRepo
import `in`.creativelizard.businessadvisor.repositories.LoginRepo
import `in`.creativelizard.businessadvisor.views.utils.ApiClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class BusinessPageViewModel : ViewModel() {
    private val  businessRepo: BusinessPageRepo = BusinessPageRepo.getInstance()

    val getBusinessTypeList:() -> MutableLiveData<List<CategoryArray>> = {businessRepo.getBusinessTypeList()}
}