package my.awesome.yppschoolapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

//import com.awesome.yppschoolapp.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText Email, Password;

    ConstraintLayout cnst;
    LinearLayout linear;
    ScrollView scrollView;
    String dirpath;
    WebView webView;



    Button LogIn ;
    String PasswordHolder, EmailHolder;
    String finalResult ;
    String HttpURL = "http://yppschool.com/login.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
  //  public static final String UserEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        LogIn = (Button) findViewById(R.id.login);

        cnst=findViewById(R.id.cnst);
        linear=findViewById(R.id.linearLayout);
        scrollView=findViewById(R.id.scrollview);




    }
        public void loginclick(View view) {

           // startActivity(new Intent(LoginActivity.this,ParentPortal.class));
                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {


                    UserLoginFunction(EmailHolder, PasswordHolder);
                    //Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(LoginActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }


    public void CheckEditTextIsEmptyOrNot(){

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }

    public void UserLoginFunction(final String email, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(LoginActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);



               // Toast.makeText(LoginActivity.this, httpResponseMsg, Toast.LENGTH_SHORT).show();


               if(httpResponseMsg.substring(0,9).equalsIgnoreCase("{\"result\"")) {
               // if(httpResponseMsg.equalsIgnoreCase("Data Matched")){

                    try {
                        JSONObject jsonObject = new JSONObject(httpResponseMsg);

                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("json", jsonObject.toString());
                           // myEdit.putString("json", httpResponseMsg);
                            myEdit.apply();
//                            Intent i2 =new Intent(LoginActivity.this,ParentPortal.class);
//
//                        i2.putExtra("json", httpResponseMsg);
//
//                        startActivity(i2);


//                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   progressDialog.dismiss();
                    finish();
                   startActivity(new Intent(LoginActivity.this,ParentPortal.class));
                }
                else{

                   // Toast.makeText(LoginActivity.this, "else part", Toast.LENGTH_SHORT).show();

                   progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("password",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email,password);
    }





//    private void takeScreenShot() {
//
//        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Signature/");
//
//        if (!folder.exists()) {
//            boolean success = folder.mkdir();
//        }
//
//        path = folder.getAbsolutePath();
//        path = path + "/" + signature_pdf_ + System.currentTimeMillis() + ".pdf";// path where pdf will be stored
//
//        View u = findViewById(R.id.scroll);
//        NestedScrollView z = (NestedScrollView) findViewById(R.id.scroll); // parent view
//        totalHeight = z.getChildAt(0).getHeight();// parent view height
//        totalWidth = z.getChildAt(0).getWidth();// parent view width
//
//        //Save bitmap to  below path
//        String extr = Environment.getExternalStorageDirectory() + "/Signature/";
//        File file = new File(extr);
//        if (!file.exists())
//            file.mkdir();
//        String fileName = signature_img_ + ".jpg";
//        myPath = new File(extr, fileName);
//        imagesUri = myPath.getPath();
//        FileOutputStream fos = null;
//        b = getBitmapFromView(u, totalHeight, totalWidth);
//
//        try {
//            fos = new FileOutputStream(myPath);
//            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//            fos.close();
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        createPdf();// create pdf after creating bitmap and saving
//
//    }

//    public void convertCertViewToImage(View view) throws IOException {
//
//        cnst.setDrawingCacheEnabled(true);
//        cnst.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        cnst.layout(0, 0, cnst.getMeasuredWidth(), cnst.getMeasuredHeight());
//        cnst.buildDrawingCache();
//        Bitmap bm = Bitmap.createBitmap(cnst.getDrawingCache());
//        cnst.setDrawingCacheEnabled(false); // clear drawing cache
//        Intent share = new Intent(Intent.ACTION_SEND);
//        share.setType("image/jpg");
//
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//
//        File f = new File(getExternalFilesDir(null).getAbsolutePath() + File.separator + "Certificate" + File.separator + "myCertificate.jpg");
//
//        try {
//            f.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FileOutputStream fo = new FileOutputStream(f);
//        fo.write(bytes.toByteArray());
//
//    }

//    public void layoutToImage(View view) {
//        // get view group using reference
//        linear = (LinearLayout) view.findViewById(R.id.linearLayout);
//        // convert view group to bitmap
//        linear.setDrawingCacheEnabled(true);
//        linear.buildDrawingCache();
//        Bitmap bm = linear.getDrawingCache();
//        Intent share = new Intent(Intent.ACTION_SEND);
//        share.setType("image/jpeg");
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//
//        File f = new File(Environment.getExternalStorageState() + File.separator + "image.jpg");
//        try {
//            f.createNewFile();
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(bytes.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void imageToPDF() throws FileNotFoundException {
//        try {
//            Document document = new Document();
//            dirpath = android.os.Environment.getExternalStorageDirectory().toString();
//            PdfWriter.getInstance(document, new FileOutputStream(dirpath + "/NewPDF.pdf")); //  Change pdf's name.
//            document.open();
//            Image img = Image.getInstance(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
//            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
//                    - document.rightMargin() - 0) / img.getWidth()) * 100;
//            img.scalePercent(scaler);
//            img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
//            document.add(img);
//            document.close();
//            Toast.makeText(this, "PDF Generated successfully!..", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//
//        }
//    }

}

