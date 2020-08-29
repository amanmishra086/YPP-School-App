package my.awesome.yppschoolapp;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    String DATA_URL = "http://yppschool.com/TutionFee.php?id=";

    String JSON_ARRAY = "result";
    String id;
    String Class_name;
    String currentdate="";
    int currentMonth=0;
    int currentYear=0;

    int amt=0;
    String amtt="";

    List<FrameClass> arrayList;
    Frame_adapter frame_adapter;
    ProgressDialog loading;

    Button button;
    TextView pay_fee,pay_fine,pay_amount;
    List<String> homefeesList;
    int amount;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

//        Intent intent=getActivity().getIntent();
//        amtt=intent.getStringExtra("amount");
//
//        amt=Integer.parseInt(amtt);

       // button=view.findViewById(R.id.payBtn);
        pay_fee=view.findViewById(R.id.payable_fee);
        pay_fine=view.findViewById(R.id.payable_fine);
        pay_amount=view.findViewById(R.id.payable_amount);

        homefeesList=new ArrayList<>();


        arrayList=new ArrayList<FrameClass>();
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        frame_adapter = new Frame_adapter(view.getContext(),arrayList);
        recyclerView.setAdapter(frame_adapter);
        getData(view);


        pay_fee.setText(""+frame_adapter.getFinalFee());

        LocalBroadcastManager.getInstance(view.getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

//        homefeesList=frame_adapter.getArrayList();

//        for(int i=0;i<frame_adapter.feesList.size();i++){
//            amount=amount+frame_adapter.feesList.get(i);
//        }



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                amount=frame_adapter.getFinalAmount();
//                //pay_fee.setText(""+frame_adapter.getFinalFee());
//                Toast.makeText(v.getContext(), ""+amount, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), PaymentActivity.class);
//                intent.putExtra("amount",""+amount);
//                //startActivity(intent);
//            }
//        });

//        recyclerView.setAdapter(new Frame_adapter(1234));

        return view;
    }



    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String Fee = intent.getStringExtra("fee");
            String fine = intent.getStringExtra("fine");
            String total = intent.getStringExtra("total");
            pay_fee.setText(Fee+"/-");
            pay_fine.setText(fine+"/-");
            pay_amount.setText(total);
           // Toast.makeText(getContext(),ItemName +" "+qty ,Toast.LENGTH_SHORT).show();
        }
    };


    public int getFinalamt(){
        return Integer.parseInt(pay_amount.getText().toString());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getData(View view) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        currentMonth=now.getMonthValue();
        currentYear=now.getYear();

        currentdate=dtf.format(now);


        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String json=sh.getString("json","");
              // id=sh.getString("id","");
               //Class_name=sh.getString("class_name","");

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
        loading = ProgressDialog.show(view.getContext(),"Please wait...","Fetching...",false,false);

        //String url = DATA_URL+editTextId.getText().toString().trim();
        String url = DATA_URL+id+"&"+"class="+Class_name;

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

        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            for (int i=0; i<result.length(); i++ ){
                JSONObject ob=result.getJSONObject(i);

                String date=ob.getString("month_year");
                String fee=ob.getString("fee_amount");
               String finestr="";

                String[] arr=date.split("-");


                long fine=0;
                if(date.compareTo(currentdate)>0){
                    fine=0;
                    finestr=String.valueOf(fine);
                }else {

                    int month = Integer.parseInt(arr[1]);
                    int year = Integer.parseInt(arr[0]);


                    int diffmonth = (month - currentMonth);
                    int diffyear = (year - currentYear);

                    long diff =(long) Math.abs(diffyear * 12 - diffmonth);


                    if(diff>2){

                        finestr=fee;

                    }else{
                        fine = (diff * 50);
                        finestr=String.valueOf(fine);
                    }



                }

                // Toast.makeText(FirstActivity.this, ob.getString("name"), Toast.LENGTH_SHORT).show();
                FrameClass history=new FrameClass(ob.getString("month_year")
                        ,ob.getString("fee_amount"),finestr,ob.getString("paid_status"));

                arrayList.add(history);
            }



            // JSONObject ob = result.getJSONObject(0);

//            name = collegeData.getString(KEY_NAME);
//            address = collegeData.getString(KEY_ADDRESS);
//            vc = collegeData.getString(KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setAdapter(frame_adapter);
        frame_adapter.notifyDataSetChanged();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

 //    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
