package my.awesome.yppschoolapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

//import com.awesome.yppschoolapp.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    EditText Email, Password;

    ConstraintLayout cnst;
    LinearLayout linear;
    ScrollView scrollView;
    String dirpath;
    WebView webView;



    Button LogIn ;
    String PasswordHolder, EmailHolder;
    String finalResult ;
    String HttpURL = "http://yppschool.com/login.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    JsonHttpParse jsonHttpParse = new JsonHttpParse();
  //  public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        LogIn = (Button) findViewById(R.id.login);

        cnst=findViewById(R.id.cnst);
        linear=findViewById(R.id.linearLayout);
        scrollView=findViewById(R.id.scrollview);



    }



        public void loginclick(View view) {



           // startActivity(new Intent(LoginActivity.this,ParentPortal.class));
                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {


                    UserLoginFunction(EmailHolder, PasswordHolder);
                    //Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(LoginActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }


    public void CheckEditTextIsEmptyOrNot(){

        EmailHolder = Email.getText().toString().trim();
        PasswordHolder = Password.getText().toString().trim();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }

    public void UserLoginFunction(final String email, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(LoginActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);



              // Toast.makeText(LoginActivity.this, httpResponseMsg, Toast.LENGTH_SHORT).show();


              // if(httpResponseMsg.substring(0,9).equalsIgnoreCase("{\"result\"")) {

                if(httpResponseMsg.contains("result")){
                    try {
                        JSONObject jsonObject = new JSONObject(httpResponseMsg);

                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("json", jsonObject.toString());
                           // myEdit.putString("json", httpResponseMsg);
                            myEdit.apply();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   progressDialog.dismiss();
                    finish();
                   startActivity(new Intent(LoginActivity.this,ParentPortal.class));
                }
                else{

                   // Toast.makeText(LoginActivity.this, "else part", Toast.LENGTH_SHORT).show();

                   progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, httpResponseMsg, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(LoginActivity.this,"Unable to connect to server !!!",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("password",params[1]);
                //String jsonInputString="{\"email\":\""+email+"\",\"password\":\""+password+"\"}";
                //String jsonInputString="&email="+email+"&password="+password;
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                //finalResult = jsonHttpParse.postRequest(jsonInputString, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email,password);
    }




}

