package co.achareh.interview.network

import co.achareh.interview.data.AcharehRequest
import co.achareh.interview.data.AcharehResponse
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.time.Duration
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @Headers("Content-Type:application/json")
    @POST("address")
    suspend fun sendOrder(@Body request: AcharehRequest): Response<AcharehResponse>



    @GET("address")
    suspend fun getOrder(): Response<List<AcharehResponse>>


    data class S(val name:String,val age:Int,val count:Int)

    @GET("?name=alas")
    suspend fun gst():Response<S>



    companion object Services {

        private const val Username = "09822222222"
        private const val Password = "Sana12345678"

        private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()


        private val httpClient: OkHttpClient.Builder = OkHttpClient
            .Builder()
            .addInterceptor(BasicAuthInterceptor(Username, Password))
            .callTimeout(30L,TimeUnit.SECONDS)


        private var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                logging.level=HttpLoggingInterceptor.Level.HEADERS
                httpClient.addInterceptor(logging)
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://stage.achareh.ir/api/karfarmas/")
                    .client(httpClient.build())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!

        }

    }

}
