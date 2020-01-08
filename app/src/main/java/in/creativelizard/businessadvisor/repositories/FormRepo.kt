package `in`.creativelizard.businessadvisor.repositories

import `in`.creativelizard.businessadvisor.interfaces.ApiInterface
import `in`.creativelizard.businessadvisor.models.CreateBusinessInput
import `in`.creativelizard.businessadvisor.models.CreateBusinessProfileResponseOutput
import `in`.creativelizard.businessadvisor.models.networkModels.LoginInput
import `in`.creativelizard.businessadvisor.views.utils.ApiClient
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class FormRepo {
    companion object{
        var instance:FormRepo?=null

        var getInstance : ()-> FormRepo = {
            if(instance!=null){
                instance!!
            }else{
                instance = FormRepo()
                instance!!
            }
        }
    }


     var createBusiness : (CreateBusinessInput)-> MutableLiveData<CreateBusinessProfileResponseOutput> = {
        val data = MutableLiveData<CreateBusinessProfileResponseOutput>()
       /* val data = MutableLiveData<Any>()
        data.value = callLoginAPI()
        data*/

        val apiClient = ApiClient().getClient().create(ApiInterface::class.java)
        val call = apiClient.createBusinessProfile(it)

        call.enqueue(object : retrofit2.Callback<CreateBusinessProfileResponseOutput> {
            override fun onFailure(call: Call<CreateBusinessProfileResponseOutput>, t: Throwable) {
               t.printStackTrace()
            }

            override fun onResponse(call: Call<CreateBusinessProfileResponseOutput>, response: Response<CreateBusinessProfileResponseOutput>) {
                val jString =  Gson().toJson(response.body())
                print(jString)
                data.value = response.body()
            }
        })

        data

    }

   /* private fun callLoginAPI(token:String): Any? {

    }*/

}