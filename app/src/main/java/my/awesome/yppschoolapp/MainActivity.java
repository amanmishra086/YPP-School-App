package my.awesome.yppschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import my.awesome.yppschoolapp.retrofit.LoginData;
import my.awesome.yppschoolapp.retrofit.Result;
import my.awesome.yppschoolapp.retrofit.RetrofitClient;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.awesome.yppschoolapp.R;

public class MainActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    String id,name,dob,gender,email,lastname,presentaddress, permanentaddress,fathersname,mothersname,class_name,section,phone,aadhar,session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);
    }

    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString().trim();
        String password = PasswordEt.getText().toString().trim();
//        String type = "login";
//        String x="";
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute(type, username, password,x);

        Call<LoginData> call= RetrofitClient.getInstance().getApi().LoginUser(username,password);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                ArrayList<Result> loginData=response.body().getResult();


               // Toast.makeText(MainActivity.this, loginData.get(0).toString(), Toast.LENGTH_SHORT).show();

                for(int i=0;i<loginData.size();i++){
                     id=loginData.get(i).getId();
                     name=loginData.get(i).getName();
                     dob=loginData.get(i).getDob();
                     gender=loginData.get(i).getGender();
                     email=loginData.get(i).getEmail();
                     lastname=loginData.get(i).getLastname();
                     presentaddress=loginData.get(i).getPresentadd();
                     permanentaddress=loginData.get(i).getPermanentadd();
                     fathersname=loginData.get(i).getFathersname();
                     mothersname=loginData.get(i).getMothersname();
                     class_name=loginData.get(i).getClass_name();
                     section=loginData.get(i).getSection();
                     phone=loginData.get(i).getPhone();
                     aadhar=loginData.get(i).getAadhar();
                     session=loginData.get(i).getSession();
                }
                // Toast.makeText(Login.this, loginData, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("id",id);
                myEdit.putString("name",name);
                myEdit.putString("dob",dob);
                myEdit.putString("gender",gender);
                myEdit.putString("email",email);
                myEdit.putString("lastname",lastname);
                myEdit.putString("presentaddress",presentaddress);
                myEdit.putString("permanentaddress",permanentaddress);
                myEdit.putString("fathersname",fathersname);
                myEdit.putString("mothersname",mothersname);
                myEdit.putString("class_name",class_name);
                myEdit.putString("section",section);
                myEdit.putString("phone",phone);
                myEdit.putString("aadhar",aadhar);
                myEdit.putString("session",session);
                // myEdit.putString("json", httpResponseMsg);
                myEdit.apply();

                Toast.makeText(MainActivity.this, "sucess", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this,ParentPortal.class));


            }



            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });


    }


}
