package my.awesome.yppschoolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class cca_result extends AppCompatActivity {

    String DATA_URL = "http://yppschool.com/cca_result.php?id=";
   String DATA_URL2 = "http://yppschool.com/cca_name.php?id=";

    String JSON_ARRAY = "result";
    String id;

    List<cca_result_class > arrayList;
    cca_result_adapter cca_result_adapter;
    RecyclerView recyclerView;

    ProgressDialog loading;
    String student_class="";
    String student_pos="";

     String cca_name2="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cca_result);

        cca_result.this.setTitle("CCA-Result");

        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        //recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        //recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cca_result_adapter = new cca_result_adapter(this,arrayList);
        recyclerView.setAdapter(cca_result_adapter);

        getData();
        recyclerView.setAdapter(cca_result_adapter);

    }

    public void getData() {

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json=sh.getString("json","");
        try {
            JSONObject jsonObj1 = new JSONObject(json);
            JSONArray array = jsonObj1.getJSONArray("result");
            for (int i = 0; i < array.length(); i++) {
                JSONObject ob1 = array.getJSONObject(i);
                id=ob1.getString("id");

                //Toast.makeText(StudentProfile.this, ob.getString("name"), Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // id = editTextId.getText().toString().trim();
//        if (id.equals("")) {
//            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
//            return;
//        }
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        //String url = DATA_URL+editTextId.getText().toString().trim();
        String url = DATA_URL+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(cca_result.this, "Please wait....", Toast.LENGTH_SHORT).show();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(cca_result.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJSON(String response){


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            for (int i=0; i<result.length(); i++ ){

                JSONObject ob=result.getJSONObject(i);

                if(ob.getString("class_id").equals("7"))student_class="NURSERY";
                else if(ob.getString("class_id").equals("8"))student_class="L.K.G";
                else if(ob.getString("class_id").equals("9"))student_class="U.K.G";
                else if(ob.getString("class_id").equals("10"))student_class="STD-01";
                else if(ob.getString("class_id").equals("11"))student_class="STD-02";
                else if(ob.getString("class_id").equals("12"))student_class="STD-03";
                else if(ob.getString("class_id").equals("13"))student_class="STD-04";
                else if(ob.getString("class_id").equals("14"))student_class="STD-05";
                else if(ob.getString("class_id").equals("15"))student_class="STD-06";
                else if(ob.getString("class_id").equals("16"))student_class="STD-07";
                else if(ob.getString("class_id").equals("17"))student_class="STD-08";
                else if(ob.getString("class_id").equals("18"))student_class="STD-09";
                else if(ob.getString("class_id").equals("19"))student_class="STD-10";
                else if(ob.getString("class_id").equals("22"))student_class="STD-11(core)";
                else if(ob.getString("class_id").equals("23"))student_class="STD-11(core & entrance)";
                else if(ob.getString("class_id").equals("24"))student_class="STD-12(core)";
                else if(ob.getString("class_id").equals("25"))student_class="STD-11";
                else student_class="";

                if(ob.getString("cca_position").equals("1"))student_pos="1st";
                else if(ob.getString("cca_position").equals("2"))student_pos="2nd";
                else if(ob.getString("cca_position").equals("3"))student_pos="3rd";
                else{
                    student_pos=(ob.getString("cca_position")+"th");
                }

                String ddd=ob.getString("cca_date");

               // getcca_name(ob.getString("cca_id"));

                // Toast.makeText(FirstActivity.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
               cca_result_class cca=new cca_result_class(student_class,ob.getString("cca_id"),student_pos,ddd);





                arrayList.add(cca);
            }



            // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(cca_result_adapter);
        cca_result_adapter.notifyDataSetChanged();

    }


    // for getting cca-name,, using another api

    public void getcca_name(String cca_id) {
//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        String json=sh.getString("json","");
//        try {
//            JSONObject jsonObj1 = new JSONObject(json);
//            JSONArray array = jsonObj1.getJSONArray("result");
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject ob1 = array.getJSONObject(i);
//                id=ob1.getString("id");
//
//                //Toast.makeText(StudentProfile.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        // id = editTextId.getText().toString().trim();
//        if (id.equals("")) {
//            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
//            return;
//        }
      //  loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        //String url = DATA_URL+editTextId.getText().toString().trim();

        String url2 = DATA_URL2+cca_id;

        StringRequest stringRequest = new StringRequest(url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                 cca_name2 =showJSON2(response);
                Toast.makeText(cca_result.this, cca_name2+"--", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(cca_result.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public String showJSON2(String response){

        String ccc = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject ob2 = result.getJSONObject(i);

                  cca_name2 = ob2.getString("cca_name");


                // Toast.makeText(FirstActivity.this, ob.getString("name"), Toast.LENGTH_SHORT).show();

            }
                // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
            } catch(JSONException e){
                e.printStackTrace();
            }


        return cca_name2;

    }
}
