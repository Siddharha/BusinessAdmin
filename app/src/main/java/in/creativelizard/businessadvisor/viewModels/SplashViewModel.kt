package `in`.creativelizard.businessadvisor.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    var isSplashLaded : MutableLiveData<Boolean> = MutableLiveData()
    var getSplashLoadStatus:()->LiveData<Boolean> = {
        isSplashLaded
    }
}