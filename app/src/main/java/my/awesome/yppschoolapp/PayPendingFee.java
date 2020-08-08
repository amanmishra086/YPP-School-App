package my.awesome.yppschoolapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class PayPendingFee extends AppCompatActivity {

    String DATA_URL = "http://yppschool.com/Pay_pending_fee.php?id=";

    String JSON_ARRAY = "result";
    String id;
    String Class_name;
    String date;
    String payable;
    static double tamount=0.00;
    int amt=0;

    RecyclerView recyclerView;
    List<FrameClass> arrayList;

    PayPendingFee_adapter payPendingFee_adapter;
    ProgressDialog loading;

    Button paybtn,history;
    TextView payableamount;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pending_fee);

        PayPendingFee.this.setTitle("Pending Fees");

        payableamount=findViewById(R.id.payable_amount);
        paybtn=findViewById(R.id.pay_btn);
        history=findViewById(R.id.history_btn);

        arrayList=new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview_pending_fee);
        recyclerView.setHasFixedSize(true);

        //recyclerViewAdapter = new RecyclerViewAdapter(arrayList);
        //recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        payPendingFee_adapter = new PayPendingFee_adapter(this,arrayList);
        recyclerView.setAdapter(payPendingFee_adapter);

        getData();
        recyclerView.setAdapter(payPendingFee_adapter);

         //payable=""+tamount+"/-";

       // payableamount.append(payable);

//        homefeesList=frame_adapter.getArrayList();

//        for(int i=0;i<frame_adapter.feesList.size();i++){
//            amount=amount+frame_adapter.feesList.get(i);
//        }

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                amount=frame_adapter.getFinalAmount();
//                Toast.makeText(v.getContext(), ""+amount, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), PaymentActivity.class);
//                intent.putExtra("amount",""+amount);
//                startActivity(intent);
//            }
//        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getData() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        date=dtf.format(now);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String json=sh.getString("json","");
        try {
            JSONObject jsonObj1 = new JSONObject(json);
            JSONArray array = jsonObj1.getJSONArray("result");
            for (int i = 0; i < array.length(); i++) {
                JSONObject ob1 = array.getJSONObject(i);
                id=ob1.getString("id");
                Class_name=ob1.getString("class");
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
        String url = DATA_URL+id+"&"+"class="+Class_name+"&"+"date="+date;

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
                        //Toast.makeText(TransactionHistory.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
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


                double fee=Double.parseDouble(ob.getString("fee_amount"));
                double fine=Double.parseDouble(ob.getString("fine_collected"));

                double total=fee+fine;

                tamount = tamount+total;
                amt= (int) tamount;

                payableamount.setText(""+amt+"/-");



                String strTotal=""+total;



                // Toast.makeText(FirstActivity.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
                FrameClass history=new FrameClass(ob.getString("month_year")
                        ,ob.getString("fee_amount"),ob.getString("fine_collected"),strTotal);

                arrayList.add(history);
            }



            // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(payPendingFee_adapter);
        payPendingFee_adapter.notifyDataSetChanged();

    }

    public void onClickPayBtn(View view) {

        Intent intent = new Intent(PayPendingFee.this, PaymentActivity.class);
        intent.putExtra("amount",""+payableamount.getText().toString());
        startActivity(intent);


    }

    public void onClickHistory(View view) {
        startActivity(new Intent(PayPendingFee.this,TransactionHistory.class));
    }

    public void onClickAddMore(View view) {

        Toast.makeText(this, "Added Amount:- "+payableamount.getText().toString()+".0/-", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PayPendingFee.this, PaymentTab.class);
        intent.putExtra("amount",""+payableamount.getText().toString());
        startActivity(intent);
    }
}
