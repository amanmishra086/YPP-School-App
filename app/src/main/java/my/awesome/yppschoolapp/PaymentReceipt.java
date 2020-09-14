package my.awesome.yppschoolapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
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

public class PaymentReceipt extends AppCompatActivity {

    private WebView webView;

    private String name,cnic,status,class1;

    String html4="";
    String DATA_URL = "http://yppschool.com/paymentReceipt.php?id=";

    String JSON_ARRAY = "result";
    String id = "";

   String str2="";
    String ReceiptNo,Receiptdate,totalamt,paymentstatus,paymentmode,stu_name,strClassname,stu_session;
    String student_class;

    ArrayList <PaymentReceipt_class> arrayList ;
    //ArrayList <String> arr=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);

        PaymentReceipt.this.setTitle("Transaction receipt");

        String str="";
        arrayList=new ArrayList<PaymentReceipt_class>();

        Intent intent=getIntent();
          ReceiptNo=intent.getExtras().getString("no");
         Receiptdate=intent.getExtras().getString("date");
         totalamt=intent.getExtras().getString("total");
         paymentstatus=intent.getExtras().getString("status");
         paymentmode=intent.getExtras().getString("mode");
         stu_name=intent.getExtras().getString("name");
        strClassname=intent.getExtras().getString("class");
         stu_session=intent.getExtras().getString("session");

        if(strClassname.equals("7"))student_class="NURSERY";
        else if(strClassname.equals("8"))student_class="L.K.G";
        else if(strClassname.equals("9"))student_class="U.K.G";
        else if(strClassname.equals("10"))student_class="STD-01";
        else if(strClassname.equals("11"))student_class="STD-02";
        else if(strClassname.equals("12"))student_class="STD-03";
        else if(strClassname.equals("13"))student_class="STD-04";
        else if(strClassname.equals("14"))student_class="STD-05";
        else if(strClassname.equals("15"))student_class="STD-06";
        else if(strClassname.equals("16"))student_class="STD-07";
        else if(strClassname.equals("17"))student_class="STD-08";
        else if(strClassname.equals("18"))student_class="STD-09";
        else if(strClassname.equals("19"))student_class="STD-10";
        else if(strClassname.equals("22"))student_class="STD-11(core)";
        else if(strClassname.equals("23"))student_class="STD-11(core & entrance)";
        else if(strClassname.equals("24"))student_class="STD-12(core)";
        else if(strClassname.equals("25"))student_class="STD-11";
        else student_class="";

        webView=findViewById(R.id.webview);

        getData(str);



       // Toast.makeText(this, str, Toast.LENGTH_SHORT).show();


//        webView.loadDataWithBaseURL(null,html4,"text/html","utf-8",null);

    }
    public void getData(final String str) {



        id=ReceiptNo;

        //String url = DATA_URL+editTextId.getText().toString().trim();
        String url = DATA_URL+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response,str);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PaymentReceipt.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response,String str){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            for (int i=0; i<result.length(); i++ ){
                JSONObject ob=result.getJSONObject(i);

                PaymentReceipt_class history2=new PaymentReceipt_class(ob.getString("item_name")
                        ,ob.getString("item_quantity"),ob.getString("final_amount"));


                str+="  <tr>\n" +
                        "    <td width=\"70%\">"+history2.getProduct()+"</td>\n" +
                        "    <td width=\"10%\">"+history2.getQuantity()+"</td>\n" +
                        "    <td align=\"right\" width=\"20%\">"+history2.getPrice()+"</td>\n" +
                        "  </tr>\n";



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        html4="<table width=\"100%\" style=\"font-size: 9px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\">  <tr>   " +
                " <td width=\"50%\">      <img src=\"http://yppschool.com/student/assets/img/phoenix.png\" alt=\"Young Phoenix\" width=\"250\">    </td>   " +
                " <td width=\"10%\"></td>    <td width=\"40%\">Payment Receipt<hr/><b>Receipt # "+ReceiptNo+"</b><br>" +
                "<b>Receipt Date : </b> '. " +
                ""+Receiptdate+"<br><b>Receipt By : </b>'.$usr[0]['firstname'].' '.$usr[0]['lastname'].'</td>  </tr></table>" +
                "<table width=\"100%\" style=\"font-size: 9px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\">\n" +
                "  <tr>\n" +
                "    <td width=\"50%\">\n" +
                "      <br/>\n" +
                "      BBSR-2, Odisha<br/>\n" +
                "      Phone No - 8658 599 505, 0674-2343851<br/>\n" +
                "      Aff No: 1530218/16, NOC No: 14890/18\n" +
                "    </td>\n" +
                "    <td width=\"10%\"></td>\n" +
                "    <td width=\"40%\">\n" +
                "      <address>\n" +
                "        <strong>"+stu_name+"</strong><br>\n" +
                "        Class - "+student_class+"<br>\n" +
                "        Session - "+"2020-2021"+"\n" +
                "      </address>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<table width=\"100%\" border=\"1\" cellpadding=\"3\" cellspacing=\"0\" style=\"font-size: 9px;\">\n" +
                "  <thead>\n" +
                "  <tr>\n" +
                "    <th width=\"70%\">Product</th>\n" +
                "    <th width=\"10%\">Qty</th>\n" +
                "    <th width=\"20%\">Total</th>\n" +
                "  </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n" +str


                + "  </tbody>\n" +
                "  <tfoot>\n" +
                "    <tr>\n" +
                "      <td></td>\n" +
                "      <td align=\"right\">Grand Total:</td>\n" +
                "      <td align=\"right\">"+totalamt+"</td>\n" +
                "    </tr>\n" +
                "  </tfoot>\n" +
                "</table>\n" +
                "<p style=\"font-size: 11px;\">Payment Methods: <b>"+paymentmode+"\n" +
                "</b>" +
                 "<br/>\n" +
                " </b>\n Amount In Words : <b>"+totalamt+"</b> only</p>\n" +
                "<p style=\"font-size: 11px;\">Remarks: <b><?=$receipt[0]['remarks'];?></b></p>\n" +
                "<table width=\"100%\" border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"font-size: 10px;\">\n" +
                "  <tr>\n" +
                "    <td width=\"70%\">\n" +
                "<div class=\"col-xs-12\" style=\"font-size: 8px;\">\n" +
                "NB: The tution fees is to be paid before 10th of every subsequent month. If it is not paid within the said period then Rs. 50/- will be charged as fine till 10th of next subsequent month. After this Rs. 100/- will be charged as fine till rest 10th. Finally if the fees is not paid  double of tution fees will be charged as fine. If tution fee is not paid for 3 months the school has right to expel the student without any further notice.\n" +
                "</div>\n" +
                "</td>\n" +
                "<td width=\"30%\" valign=\"bottom\"><br/>\n" +
                "  <br/>  <br/>\n" +
                "  <br/>\n" +
                "Account Seal with Signature\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>";
        webView.loadDataWithBaseURL(null,html4,"text/html","utf-8",null);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void CreatePdf(View view){
        Context context=PaymentReceipt.this;
        PrintManager printManager=(PrintManager)PaymentReceipt.this.getSystemService(context.PRINT_SERVICE);
        PrintDocumentAdapter adapter=null;
        String JobName=getString(R.string.app_name) +"Document";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            adapter=webView.createPrintDocumentAdapter(JobName);
        }

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            PrintJob printJob=printManager.print(JobName,adapter,new PrintAttributes.Builder().build());
        }

    }
}
