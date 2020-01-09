package `in`.creativelizard.businessadvisor.viewModels

import `in`.creativelizard.businessadvisor.interfaces.ApiInterface
import `in`.creativelizard.businessadvisor.models.CreateBusinessInput
import `in`.creativelizard.businessadvisor.models.CreateBusinessProfileResponseOutput
import `in`.creativelizard.businessadvisor.models.GetBusinessInput
import `in`.creativelizard.businessadvisor.models.networkModels.BusinessTypeListOutput
import `in`.creativelizard.businessadvisor.models.networkModels.CategoryArray
import `in`.creativelizard.businessadvisor.models.networkModels.GetBusinessOutput
import `in`.creativelizard.businessadvisor.models.networkModels.LoginInput
import `in`.creativelizard.businessadvisor.repositories.BusinessPageRepo
import `in`.creativelizard.businessadvisor.repositories.FormRepo
import `in`.creativelizard.businessadvisor.repositories.LoginRepo
import `in`.creativelizard.businessadvisor.views.utils.ApiClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class FromPageViewModel : ViewModel() {
    private val  formRepo: FormRepo = FormRepo.getInstance()
    private var updateFragment:MutableLiveData<Boolean> = MutableLiveData()

    val createBusiness:(CreateBusinessInput) -> MutableLiveData<CreateBusinessProfileResponseOutput> = {formRepo.createBusiness(it)}
    val getBusiness:(GetBusinessInput) ->MutableLiveData<GetBusinessOutput> = {
        formRepo.getBusinessData(it)
    }

    val isFragmentUIUpdated: () -> LiveData<Boolean> = {
        updateFragment
    }

    val setFragmentLoadState:(Boolean) -> Unit = {
        updateFragment.value = it
    }
}