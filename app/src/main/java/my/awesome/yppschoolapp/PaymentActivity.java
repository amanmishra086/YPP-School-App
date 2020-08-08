package my.awesome.yppschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atom.mpsdklibrary.PayActivity;
//import com.awesome.yppschoolapp.R;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class PaymentActivity extends AppCompatActivity {

    EditText email,phone;
    TextView amount;
    String amtt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        amount=findViewById(R.id.editText);
        email=findViewById(R.id.editText2);
        phone=findViewById(R.id.editText3);

        Intent intent=getIntent();
         amtt=intent.getStringExtra("amount");
        amount.setText("Amount:- "+amtt+"/-");
    }

    public void onClickMakePayment(View view) {

        String amt=amtt+".00";
        String Email=email.getText().toString();
        String Phone=phone.getText().toString();

        if(TextUtils.isEmpty(amt) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Phone)){

            Toast.makeText(this, "Please Fill All Details", Toast.LENGTH_SHORT).show();

        }else{



        Intent newPayIntent=new Intent(this, PayActivity.class);
        newPayIntent.putExtra("merchantId", "197");
//txnscamt Fixed. Must be 0
        newPayIntent.putExtra("txnscamt", "0");
        newPayIntent.putExtra("loginid","197" );
        newPayIntent.putExtra("password", "Test@123");
        newPayIntent.putExtra("prodid", "NSE");
//txncurr Fixed. Must be �INR�
        newPayIntent.putExtra("txncurr", "INR");
        newPayIntent.putExtra("clientcode",encodeBase64 ("007"));
        newPayIntent.putExtra("custacc","100000036600" );
        newPayIntent.putExtra("channelid", "INT");
//amt  Should be 2 decimal number i.e 1.00
        newPayIntent.putExtra("amt",amt );
        newPayIntent.putExtra("txnid", "013346723");
//Date Should be in same format
        newPayIntent.putExtra("date", "01/10/2019 18:31:00");
        newPayIntent.putExtra("signature_request","KEY123657234" );
        newPayIntent.putExtra("signature_response","KEYRESP123657234" );
        newPayIntent.putExtra("discriminator","All");
        newPayIntent.putExtra("isLive",false);
//Optinal Parameters
//Only for Name
        newPayIntent.putExtra("customerName", "abcde");
//Only for Email ID
        newPayIntent.putExtra("customerEmailID", Email);
//Only for Mobile Number
        newPayIntent.putExtra("customerMobileNo",Phone );
//Only for Address
        newPayIntent.putExtra("billingAddress", "Pune");
// Can pass any data
        newPayIntent.putExtra("optionalUdf9", "OPTIONAL DATA 2");
// Pass data in XML format, only for Multi product
        newPayIntent.putExtra("mprod", "mprod");
        startActivityForResult(newPayIntent,1);


        }

    }
    public String encodeBase64(String encode)
    {
        String decode=null;

        try {


            decode=  Base64.encode(encode.getBytes());
        } catch (Exception e) {
            System.out.println("Unable to decode : "+ e);
        }
        return  decode;
    }

    public void history(View view) {
        startActivity(new Intent(PaymentActivity.this,TransactionHistory.class));
    }
}
