package my.awesome.yppschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class cca_certificate extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cca_certificate);

        Intent intent=getIntent();
        String id=intent.getExtras().getString("certificate_id");

        cca_certificate.this.setTitle("CCA-Certificate");

        webView=findViewById(R.id.webview);


        webView.loadUrl("http://yppschool.com/erp/index.php/mobileapi/view_cca_certificate/"+id);
        WebSettings webSettings=webView.getSettings();
        webSettings.setAllowContentAccess(true);

        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());

    }

    public void download_certificate(View view) {

        Context context=cca_certificate.this;
        PrintManager printManager=(PrintManager)cca_certificate.this.getSystemService(context.PRINT_SERVICE);
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
