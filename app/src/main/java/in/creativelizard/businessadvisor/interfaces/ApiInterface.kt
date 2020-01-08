package `in`.creativelizard.businessadvisor.interfaces

import `in`.creativelizard.businessadvisor.models.CreateBusinessInput
import `in`.creativelizard.businessadvisor.models.CreateBusinessProfileResponseOutput
import `in`.creativelizard.businessadvisor.models.networkModels.BusinessTypeListOutput
import `in`.creativelizard.businessadvisor.models.networkModels.LoginInput
import `in`.creativelizard.businessadvisor.utils.Constant
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    //@FormUrlEncoded
    @POST(Constant.LOG_IN)
    fun getLogin(@Body loginInput: LoginInput): Call<Any>

    @GET(Constant.BUSINESS_TYPE_LIST)
    fun getBusinessTypeList(): Call<BusinessTypeListOutput>

    //@FormUrlEncoded
    @POST(Constant.CREATE_BUSINESS_PROFILE)
    fun createBusinessProfile(@Body createBusinessInput: CreateBusinessInput): Call<CreateBusinessProfileResponseOutput>

}