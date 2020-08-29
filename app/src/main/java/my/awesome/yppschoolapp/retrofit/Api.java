package my.awesome.yppschoolapp.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

//    @FormUrlEncoded
//    @POST("request-api/api.php")
//    Call<DefaultResponse> registerUser(
//            @Field("method") String method,
//            @Field("customer_name") String name,
//            @Field("customer_email") String Email,
//            @Field("customer_mobile") String phone,
//            @Field("customer_password") String password
//    );
    @FormUrlEncoded
    @POST("login.php")

    Call<LoginData> LoginUser(
            // @Field("method") String method,
            @Field("email") String Email,
            @Field("password") String password


    );
    //  @FormUrlEncoded
    //  @GET("login.php")
    //  Call<LoginData> getResult();

}
