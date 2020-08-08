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

import java.util.ArrayList;
import java.util.List;

public class exam_result extends AppCompatActivity {

    String DATA_URL = "http://yppschool.com/examResult.php?id=";

    String JSON_ARRAY = "result";
    String id;

    List<exam_result_Class > arrayList;
    Exam_result_adapter exam_result_adapter;
    RecyclerView recyclerView;

    ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result);

        exam_result.this.setTitle("Result");

        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        //recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        //recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        exam_result_adapter = new Exam_result_adapter(this,arrayList);
        recyclerView.setAdapter(exam_result_adapter);

        getData();
        recyclerView.setAdapter(exam_result_adapter);
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
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(exam_result.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            for (int i=0; i<result.length(); i++ ){
                JSONObject ob=result.getJSONObject(i);

                // Toast.makeText(FirstActivity.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
                exam_result_Class history=new exam_result_Class(ob.getString("exam_term")
                        ,ob.getString("grade"),ob.getString("teacher_remarks"),ob.getString("class_id"));

                arrayList.add(history);
            }



            // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(exam_result_adapter);
        exam_result_adapter.notifyDataSetChanged();

    }
}
