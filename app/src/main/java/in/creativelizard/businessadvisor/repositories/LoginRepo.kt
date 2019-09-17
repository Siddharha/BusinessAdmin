package `in`.creativelizard.businessadvisor.repositories

import `in`.creativelizard.businessadvisor.interfaces.ApiInterface
import `in`.creativelizard.businessadvisor.models.networkModels.LoginInput
import `in`.creativelizard.businessadvisor.views.utils.ApiClient
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class LoginRepo {
    companion object{
        var instance:LoginRepo?=null

        var getInstance : ()-> LoginRepo = {
            if(instance!=null){
                instance!!
            }else{
                instance = LoginRepo()
                instance!!
            }
        }
    }

    var getLoginDetails : (String)-> MutableLiveData<String> = {
        val data = MutableLiveData<String>()
       /* val data = MutableLiveData<Any>()
        data.value = callLoginAPI()
        data*/

        val apiClient = ApiClient().getClient().create(ApiInterface::class.java)
        val call = apiClient.getLogin(LoginInput(it))

        call.enqueue(object : retrofit2.Callback<Any> {
            override fun onFailure(call: Call<Any>, t: Throwable) {
                data.value = "{}"
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val jString =  Gson().toJson(response.body())
                print(jString)
                data.value = jString
            }
        })

        data

    }

   /* private fun callLoginAPI(token:String): Any? {

    }*/

}