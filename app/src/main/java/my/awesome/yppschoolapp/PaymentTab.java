package my.awesome.yppschoolapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.atom.mpsdklibrary.PayActivity;
import com.google.android.material.tabs.TabLayout;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class PaymentTab extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    String total_trans;
    String total_tution;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_tab);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        textView = findViewById(R.id.payable_amount_tab);

        tabLayout.addTab(tabLayout.newTab().setText("Tution"));
        tabLayout.addTab(tabLayout.newTab().setText("Transport"));
        tabLayout.addTab(tabLayout.newTab().setText("Hostel"));
        tabLayout.addTab(tabLayout.newTab().setText("DayBoarding"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TabAdapter adapter = new TabAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message-transport"));

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver_tution,
                new IntentFilter("custom-message"));
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String Fee = intent.getStringExtra("fee");
            String fine = intent.getStringExtra("fine");
            total_trans = intent.getStringExtra("total");


            //  Toast.makeText(PaymentTab.this, ""+total_trans, Toast.LENGTH_SHORT).show();
            // Toast.makeText(getContext(),ItemName +" "+qty ,Toast.LENGTH_SHORT).show();
        }
    };
    public BroadcastReceiver mMessageReceiver_tution = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent

            String Fee = intent.getStringExtra("fee");
            String fine = intent.getStringExtra("fine");
            total_tution = intent.getStringExtra("total");


            // Toast.makeText(PaymentTab.this, ""+total_tution, Toast.LENGTH_SHORT).show();

            // Toast.makeText(getContext(),ItemName +" "+qty ,Toast.LENGTH_SHORT).show();
        }
    };

    int amt = 0;

    public void onclickbtm(View view) {
        if (total_trans == null) {
            amt = Integer.parseInt(total_tution);
        } else {
            amt = Integer.parseInt(total_tution) + Integer.parseInt(total_trans);
        }
        Toast.makeText(this, "Total Amount:- " + amt + "/-", Toast.LENGTH_SHORT).show();

        String amt5 = amt + ".00";
        String Email = "abc@gmail.com";
        String Phone = "1234567890";



            Intent newPayIntent = new Intent(this, PayActivity.class);
            newPayIntent.putExtra("merchantId", "197");
//txnscamt Fixed. Must be 0
            newPayIntent.putExtra("txnscamt", "0");
            newPayIntent.putExtra("loginid", "197");
            newPayIntent.putExtra("password", "Test@123");
            newPayIntent.putExtra("prodid", "NSE");
//txncurr Fixed. Must be �INR�
            newPayIntent.putExtra("txncurr", "INR");
            newPayIntent.putExtra("clientcode", encodeBase64("007"));
            newPayIntent.putExtra("custacc", "100000036600");
            newPayIntent.putExtra("channelid", "INT");
//amt  Should be 2 decimal number i.e 1.00
            newPayIntent.putExtra("amt", amt5);
            newPayIntent.putExtra("txnid", "013346723");
//Date Should be in same format
            newPayIntent.putExtra("date", "01/10/2019 18:31:00");
            newPayIntent.putExtra("signature_request", "KEY123657234");
            newPayIntent.putExtra("signature_response", "KEYRESP123657234");
            newPayIntent.putExtra("discriminator", "All");
            newPayIntent.putExtra("isLive", false);
//Optinal Parameters
//Only for Name
            newPayIntent.putExtra("customerName", "abcde");
//Only for Email ID
            newPayIntent.putExtra("customerEmailID", Email);
//Only for Mobile Number
            newPayIntent.putExtra("customerMobileNo", Phone);
//Only for Address
            newPayIntent.putExtra("billingAddress", "Pune");
// Can pass any data
            newPayIntent.putExtra("optionalUdf9", "OPTIONAL DATA 2");
// Pass data in XML format, only for Multi product
            newPayIntent.putExtra("mprod", "mprod");
            startActivityForResult(newPayIntent, 1);


//        Intent intent = new Intent(PaymentTab.this, PaymentActivity.class);
//        intent.putExtra("amount",""+amt);
//        startActivity(intent);
        }




    public String encodeBase64 (String encode)
    {
        String decode = null;

        try {


            decode = Base64.encode(encode.getBytes());
        } catch (Exception e) {
            System.out.println("Unable to decode : " + e);
        }
        return decode;
    }
    public void onclickhistorybtn (View view){
        // Toast.makeText(this, "Not found..!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PaymentTab.this, TransactionHistory.class);

        startActivity(intent);
    }
}
