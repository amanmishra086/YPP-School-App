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

public class ClassTask extends AppCompatActivity {

    String DATA_URL = "http://yppschool.com/classTask.php?class=";

    String JSON_ARRAY = "result";
    String class_name,session;


    List<classtask_class > arrayList;
    classtask_adapter classtask_adapter_ob;
    RecyclerView recyclerView;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_task);

        ClassTask.this.setTitle("Class Work");

        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.rv_classtask);
        recyclerView.setHasFixedSize(true);

        //recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        //recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classtask_adapter_ob = new classtask_adapter(this,arrayList);
        recyclerView.setAdapter(classtask_adapter_ob);

        getData();
        recyclerView.setAdapter(classtask_adapter_ob);
    }

    public void getData() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json=sh.getString("json","");

        try {
            JSONObject jsonObj1 = new JSONObject(json);
            JSONArray array = jsonObj1.getJSONArray("result");
            for (int i = 0; i < array.length(); i++) {
                JSONObject ob1 = array.getJSONObject(i);
                //id=ob1.getString("id");
                class_name=ob1.getString("class");
                session=ob1.getString("session");

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
        String url = DATA_URL+class_name+"&"+"session="+session;

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
                        Toast.makeText(ClassTask.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
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
                classtask_class history=new classtask_class(ob.getString("task_date")
                        ,ob.getString("subject"),ob.getString("task_remarks"),ob.getString("created_by"));

                arrayList.add(history);
            }



            // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(classtask_adapter_ob);
        classtask_adapter_ob.notifyDataSetChanged();

    }

}
