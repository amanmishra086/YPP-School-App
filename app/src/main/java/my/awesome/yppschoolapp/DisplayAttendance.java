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
//import com.awesome.yppschoolapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayAttendance extends AppCompatActivity {

   // public static final String DATA_URL = "http://192.168.94.1/Android/College/getData.php?id=";
    String DATA_URL = "http://yppschool.com/androidAttendance.php?id=";
//    public static final String KEY_NAME = "name";
//    public static final String KEY_ADDRESS = "address";
//    public static final String KEY_VC = "vc";
    String JSON_ARRAY = "result";
    String id;

    private List<StoreAttendanceClass > arrayList;
    private AttendanceAdapter attendanceAdapter;
    private RecyclerView recyclerView;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_attendance);

        DisplayAttendance.this.setTitle("Attendance");

       // Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();

        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.rv_attendance);
        recyclerView.setHasFixedSize(true);

        //recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        //recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        attendanceAdapter = new AttendanceAdapter(this,arrayList);
        recyclerView.setAdapter(attendanceAdapter);

        getData();


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
                        Toast.makeText(DisplayAttendance.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
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
                StoreAttendanceClass listData=new StoreAttendanceClass(ob.getString("attend_date")
                        ,ob.getString("status"),ob.getString("remarks"));


                 arrayList.add(listData);
            }


           // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(attendanceAdapter);
        attendanceAdapter.notifyDataSetChanged();

    }
}
