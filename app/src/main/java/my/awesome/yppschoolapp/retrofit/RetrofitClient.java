package my.awesome.yppschoolapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL="http://yppschool.com/";
    // public static final String BASE_URL="http://yppschool.com/student/index.php/login/";
    private  static RetrofitClient mInstance;
    private Retrofit retrofit;

    public RetrofitClient() {
        //  Gson gson=new GsonBuilder().setLenient().create();
        retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(mInstance==null){
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }

}
