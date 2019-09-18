package `in`.creativelizard.businessadvisor.viewModels

import `in`.creativelizard.businessadvisor.interfaces.ApiInterface
import `in`.creativelizard.businessadvisor.models.networkModels.LoginInput
import `in`.creativelizard.businessadvisor.repositories.LoginRepo
import `in`.creativelizard.businessadvisor.views.utils.ApiClient
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class BottomSheetViewModel : ViewModel() {
    /*var openBottomSheet : (Int) ->Unit = {
        when(it){
            0 -> Log.e("response","Not implemented!")
            1 -> Log.e("response","Not Implemented!")
        }
    }*/

    var liveBottomSheetData : MutableLiveData<Map<String,Int>> = MutableLiveData()
    val getBottomSheetOpenType : () -> LiveData<Map<String,Int>> = {
        liveBottomSheetData
    }
}