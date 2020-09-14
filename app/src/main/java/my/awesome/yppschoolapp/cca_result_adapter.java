package my.awesome.yppschoolapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class cca_result_adapter extends RecyclerView.Adapter<cca_result_adapter.MyViewHolder> {

    //ProgressDialog loading;
    String DATA_URL2 = "http://yppschool.com/cca_name.php?id=";
    String cca_name2="";

    private Context mcontext;
    private List<cca_result_class> arrayList;

    public cca_result_adapter(Context mcontext, List<cca_result_class> arrayList) {
        this.mcontext =  mcontext;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;   //=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        LayoutInflater minflater=LayoutInflater.from(mcontext);
        view=minflater.inflate(R.layout.list_cca,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cca_result_adapter.MyViewHolder holder, int position) {

        final cca_result_class fm=arrayList.get(position);
        holder.class_name.setText(fm.getClass_name());
        //holder.cca_name.setText(fm.getCca_name());
        holder.cca_pos.setText(fm.getCca_pos());
        holder.date.setText(fm.getDate());

        getcca_name(fm.getCca_name(),holder);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,cca_certificate.class);
                intent.putExtra("certificate_id",fm.getCertificate_id());
                mcontext.startActivity(intent);
            }
        });

       // holder.cca_name.setText(cca_name2);

       // Toast.makeText(mcontext, cca_name2, Toast.LENGTH_SHORT).show();

    }

    public void getcca_name(String cca_id, final MyViewHolder holder) {
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
               // loading.dismiss();
                 showJSON2(response,holder);
                //Toast.makeText(cca_result.this, cca_name2+"--", Toast.LENGTH_SHORT).show();

            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(cca_result.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
        requestQueue.add(stringRequest);

    }
    public void showJSON2(String response, MyViewHolder holder){

        String ccc = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
//            for (int i = 0; i < result.length(); i++) {
                JSONObject ob2 = result.getJSONObject(0);


                cca_name2 = ob2.getString("cca_name");


            holder.cca_name.setText(cca_name2);
                // Toast.makeText(FirstActivity.this, ob.getString("name"), Toast.LENGTH_SHORT).show();



           // }
            // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch(JSONException e){
            e.printStackTrace();
        }


        //return ccc;

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView class_name,cca_name,cca_pos,date;
        // CheckBox month;

        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // month=itemView.findViewById(R.id.month);
            class_name=itemView.findViewById(R.id.Class);
            cca_name=itemView.findViewById(R.id.Name);
            cca_pos=itemView.findViewById(R.id.Position);
            date=itemView.findViewById(R.id.date);

            linearLayout=itemView.findViewById(R.id.cca_linearlayout);

        }
    }
}
