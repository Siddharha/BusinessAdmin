package `in`.creativelizard.businessadvisor.viewModels

import `in`.creativelizard.businessadvisor.interfaces.ApiInterface
import `in`.creativelizard.businessadvisor.models.networkModels.LoginInput
import `in`.creativelizard.businessadvisor.repositories.LoginRepo
import `in`.creativelizard.businessadvisor.views.utils.ApiClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val  loginRepo:LoginRepo = LoginRepo.getInstance()
    private val detectedQR:MutableLiveData<String> = MutableLiveData()
    private val locationPermissionLiveData = MutableLiveData<Boolean>()

    val getLoginWithToken:(String) -> MutableLiveData<String> = {loginRepo.getLoginDetails(it)}
    val onQRDetected: () -> LiveData<String> = {
        detectedQR
    }

    val setQRForResult: (String) -> Unit = {
        detectedQR.value = it
    }

    val onLocationPermissionGrant: () ->LiveData<Boolean> = {
        locationPermissionLiveData
    }

    val setLocationPermissionResult: (Boolean) -> Unit = {
        locationPermissionLiveData.value = it
    }
}