package my.awesome.yppschoolapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NoticeList extends AppCompatActivity {

    // public static final String DATA_URL = "http://192.168.94.1/Android/College/getData.php?id=";
    String DATA_URL = "http://yppschool.com/noticeDetails.php?id=";
    //    public static final String KEY_NAME = "name";
//    public static final String KEY_ADDRESS = "address";
//    public static final String KEY_VC = "vc";
    String JSON_ARRAY = "result";
    String id;

    private List<NoticeClass > arrayList;
    private NoticeAdapter noticeAdapter;
    private RecyclerView recyclerView;

    private ProgressDialog loading;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);

        NoticeList.this.setTitle("Notice");
        //Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();

        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);

        //recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        //recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticeAdapter = new NoticeAdapter(this,arrayList);
        recyclerView.setAdapter(noticeAdapter);

        getData();


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getData() {
        //calculate date

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        id=dtf.format(now);
       // Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        //id="2020-06-03";
        // id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
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
                        Toast.makeText(NoticeList.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            for (int i=result.length()-1; i>=0; i-- ){
                JSONObject ob=result.getJSONObject(i);

                // Toast.makeText(FirstActivity.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
                NoticeClass listData=new NoticeClass(ob.getString("notice_title")
                        ,ob.getString("notice_description"),ob.getString("notice_no"));


                arrayList.add(listData);
            }


            // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(noticeAdapter);
        noticeAdapter.notifyDataSetChanged();

    }
}
