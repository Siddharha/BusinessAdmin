package `in`.creativelizard.businessadvisor.utils

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
            val originalRequest = chain.request()

            val builder = originalRequest.newBuilder()
            /*builder.header("Content-Type", "multipart/form-data")*/
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60 / 2, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .cache(null)
            .build()


        val gson = GsonBuilder()
            .setLenient()
            .create()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit!!
    }
}