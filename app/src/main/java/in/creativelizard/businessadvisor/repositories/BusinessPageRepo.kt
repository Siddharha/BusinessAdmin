package `in`.creativelizard.businessadvisor.repositories

import `in`.creativelizard.businessadvisor.interfaces.ApiInterface
import `in`.creativelizard.businessadvisor.models.networkModels.BusinessTypeListOutput
import `in`.creativelizard.businessadvisor.models.networkModels.CategoryArray
import `in`.creativelizard.businessadvisor.models.networkModels.LoginInput
import `in`.creativelizard.businessadvisor.views.utils.ApiClient
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class BusinessPageRepo {
    companion object{
        var instance:BusinessPageRepo?=null

        var getInstance : ()-> BusinessPageRepo = {
            if(instance!=null){
                instance!!
            }else{
                instance = BusinessPageRepo()
                instance!!
            }
        }
    }


     var getBusinessTypeList : ()-> MutableLiveData<List<CategoryArray>> = {
         val list = ArrayList<CategoryArray>()
        val data = MutableLiveData<List<CategoryArray>>()
         data.value = list
       /* val data = MutableLiveData<Any>()
        data.value = callLoginAPI()
        data*/

        val apiClient = ApiClient().getClient().create(ApiInterface::class.java)
        val call = apiClient.getBusinessTypeList()

        call.enqueue(object : retrofit2.Callback<BusinessTypeListOutput> {
            override fun onFailure(call: Call<BusinessTypeListOutput>, t: Throwable) {
                data.value = ArrayList()
            }

            override fun onResponse(call: Call<BusinessTypeListOutput>, response: Response<BusinessTypeListOutput>) {
                val jString =  Gson().toJson(response.body())
                print(jString)

                if(response.body()?.success ==1){

                    data.value = ArrayList()
                }

                for(itm in response.body()?.category_array!!){
                    list.add(itm)
                }
                data.value = list
            }
        })

        data

    }

   /* private fun callLoginAPI(token:String): Any? {

    }*/

}