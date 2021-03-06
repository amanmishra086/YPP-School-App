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

public class TransactionHistory extends AppCompatActivity {



    String DATA_URL = "http://yppschool.com/transactionhistory.php?id=";

    String JSON_ARRAY = "result";
    String id,stu_name,stu_class,stu_session;

     List<TransHistoryClass > arrayList;
     TransactionAdapter transactionAdapter;
     RecyclerView recyclerView;

     ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        TransactionHistory.this.setTitle("Transaction History");

        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.rv_transaction);
        recyclerView.setHasFixedSize(true);

        //recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        //recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        transactionAdapter = new TransactionAdapter(this,arrayList);
        recyclerView.setAdapter(transactionAdapter);

        getData();
        recyclerView.setAdapter(transactionAdapter);




    }

    public void getData() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String json=sh.getString("json","");

//        id=sh.getString("id","");
//        stu_name=sh.getString("name","");
//        stu_class=sh.getString("class_name","");
//        stu_session=sh.getString("session","");

        try {
            JSONObject jsonObj1 = new JSONObject(json);
            JSONArray array = jsonObj1.getJSONArray("result");
            for (int i = 0; i < array.length(); i++) {
                JSONObject ob1 = array.getJSONObject(i);
                id=ob1.getString("id");
                stu_name=ob1.getString("name");
                stu_class=ob1.getString("class");
                stu_session=ob1.getString("session");


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
                        Toast.makeText(TransactionHistory.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
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
                TransHistoryClass history=new TransHistoryClass(ob.getString("receipt_no")
                        ,ob.getString("receipt_date"),ob.getString("total_amount"),
                        ob.getString("receipt_status"),ob.getString("payment_mode"),stu_name,stu_class,stu_session);

                arrayList.add(history);
            }



            // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(transactionAdapter);
        transactionAdapter.notifyDataSetChanged();

    }
}
