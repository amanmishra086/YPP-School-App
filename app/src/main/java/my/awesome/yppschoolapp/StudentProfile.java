package my.awesome.yppschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

//import com.awesome.yppschoolapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class StudentProfile extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
    String strname,strfather,strmother,strpresentadd,strpermanentadd,strphone,strlast,strdate,strgender,stremail,
            strClassname,strAadhar,student_class,strSession,student_session;
    //private static final String HI ="https://yppschool.com/login.php" ;
   // private static final String HI = ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        t1=findViewById(R.id.tv_name);
        t2=findViewById(R.id.tv_father);
        t3=findViewById(R.id.tv_mother);
        t4=findViewById(R.id.tv_address);
        t5=findViewById(R.id.tv_perma_address);
        t6=findViewById(R.id.tv_phone);
        t7=findViewById(R.id.email);
        t8=findViewById(R.id.dob);
        t9=findViewById(R.id.gender);
        t10=findViewById(R.id.classname);
        t11=findViewById(R.id.aadhar);
        t12=findViewById(R.id.sessionName);

//        Intent intent = getIntent();
//
//        if (intent != null) {
//
//            String json = intent.getStringExtra("json");
//            try {
//                JSONObject jsonObj = new JSONObject(json);
//                JSONArray array = jsonObj.getJSONArray("result");
//                for (int i = 0; i < array.length(); i++) {
//                    JSONObject ob = array.getJSONObject(i);
//                     strname=ob.getString("name");
//                    Toast.makeText(StudentProfile.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
//
//                    Toast.makeText(StudentProfile.this, ob.getString("fathersname"), Toast.LENGTH_SHORT).show();}
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }


        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json=sh.getString("json","");

//               strname=sh.getString("name","");
//                strlast=sh.getString("lastname","");
//                strfather=sh.getString("fathersname","");
//                strmother=sh.getString("mothername","");
//                strpresentadd=sh.getString("presentaddress","");
//                strpermanentadd=sh.getString("permanentaddress","");
//                strphone=sh.getString("phone","");
//                strdate=sh.getString("dob","");
//                stremail=sh.getString("email","");
//                strgender=sh.getString("gender","");
//                strClassname=sh.getString("class_name","");
//                strAadhar=sh.getString("aadhar","");
//                strSession=sh.getString("session","");


        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("result");
            for (int i = 0; i < array.length(); i++) {
                JSONObject ob = array.getJSONObject(i);
                strname=ob.getString("name");
                strlast=ob.getString("lastname");
                strfather=ob.getString("fathersname");
                strmother=ob.getString("mothername");
                strpresentadd=ob.getString("presentaddress");
                strpermanentadd=ob.getString("permanentaddress");
                strphone=ob.getString("phone");
                strdate=ob.getString("dob");
                stremail=ob.getString("email");
                strgender=ob.getString("gender");
                strClassname=ob.getString("class");
                strAadhar=ob.getString("aadhar");
                strSession=ob.getString("session");
                //Toast.makeText(StudentProfile.this, ob.getString("name"), Toast.LENGTH_SHORT).show();

               // Toast.makeText(StudentProfile.this, ob.getString("presentaddress"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(strClassname.equals("7"))student_class="NURSERY";
        else if(strClassname.equals("8"))student_class="L.K.G";
        else if(strClassname.equals("9"))student_class="U.K.G";
        else if(strClassname.equals("10"))student_class="STD-01";
        else if(strClassname.equals("11"))student_class="STD-02";
        else if(strClassname.equals("12"))student_class="STD-03";
        else if(strClassname.equals("13"))student_class="STD-04";
        else if(strClassname.equals("14"))student_class="STD-05";
        else if(strClassname.equals("15"))student_class="STD-06";
        else if(strClassname.equals("16"))student_class="STD-07";
        else if(strClassname.equals("17"))student_class="STD-08";
        else if(strClassname.equals("18"))student_class="STD-09";
        else if(strClassname.equals("19"))student_class="STD-10";
        else if(strClassname.equals("22"))student_class="STD-11(core)";
        else if(strClassname.equals("23"))student_class="STD-11(core & entrance)";
        else if(strClassname.equals("24"))student_class="STD-12(core)";
        else if(strClassname.equals("25"))student_class="STD-11";
        else student_class="";


        if(strSession.equals("7"))student_session="2019-2020";
        else if(strSession.equals("10"))student_session="2020-2021";
        else student_session=strSession;

        t1.setText(strname+" "+strlast);
        t2.setText(strfather);
        t3.setText(strmother);
        t4.setText(strpresentadd);
        t5.setText(strpermanentadd);
        t6.setText(strphone);
        t7.setText(stremail);
        t8.setText(strdate);
        t9.setText(strgender);
        t10.setText(student_class);
        t11.setText(strAadhar);
        t12.setText(student_session);



//        Intent intent=getIntent();
//        String HI=intent.getStringExtra("UserEmail");
//        Toast.makeText(this, HI, Toast.LENGTH_SHORT).show();
//
//
//        StringRequest  stringRequest=new StringRequest(Request.Method.GET, HI, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(StudentProfile.this, "world", Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    JSONArray array=jsonObject.getJSONArray("result");
//                    for (int i=0; i<array.length(); i++ ){
//                        JSONObject ob=array.getJSONObject(i);
//                        Toast.makeText(StudentProfile.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
//                        t1.setText(ob.getString("name"));
//                        t2.setText(ob.getString("fathersname"));
//                        t3.setText(ob.getString("mothersname"));
//                        t4.setText(ob.getString("presentaddress"));
//                        t5.setText(ob.getString("permanentaddress"));
//                        t6.setText(ob.getString("phone"));
////                        List_data listData=new List_data(ob.getString("name")
////                                ,ob.getString("address"));
//
////                            if(strname.equals(listData.getName())&&
////                                    strphone.equals(listData.getMoviename())){
////                        Toast.makeText(FirstActivity.this, "user found", Toast.LENGTH_SHORT).show();
////                        Intent intent=new Intent(FirstActivity.this,MainActivity.class);
////                        startActivity(intent);
//
//                        //break;
//
//                        // }//else{
//                       // Toast.makeText(FirstActivity.this, "user not found", Toast.LENGTH_SHORT).show();
//                        // }
//
//                        // list_data.add(listData);
//                    }
//                    //rv.setAdapter(adapter);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);

    }
}
