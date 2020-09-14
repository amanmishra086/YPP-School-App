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

public class exam_result_items extends AppCompatActivity {

    private WebView webView;

    private String name,cnic,status,class1;

    String html4="";
    String DATA_URL = "http://yppschool.com/examResultItems.php?id=";

    String JSON_ARRAY = "result";
    String id = "";
    String exam_id = "";

    String str2="";
    String str5="";
    //String ReceiptNo,Receiptdate,totalamt,paymentstatus,paymentmode,stu_name,strClassname,stu_session;

    String student_class;
    String term,grade,remarks,class_name;
    String session_id,  dob,  parent_name,  promoted_next,  exam_date,  work_education_grade,
            art_education_grade,  health_education_grade,  discipline_grade,  stud_name;

    ArrayList<exam_result_item_class> arrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_result_items);



        String str="";
        arrayList=new ArrayList<exam_result_item_class>();

        Intent intent=getIntent();
        term=intent.getExtras().getString("term");
        grade=intent.getExtras().getString("grade");
        remarks=intent.getExtras().getString("remarks");
        class_name=intent.getExtras().getString("class_name");
        session_id=intent.getExtras().getString("session");
        dob=intent.getExtras().getString("dob");
        parent_name=intent.getExtras().getString("parent");
        promoted_next=intent.getExtras().getString("promoted");
        exam_date=intent.getExtras().getString("exam_date");
        work_education_grade=intent.getExtras().getString("work_grade");
        art_education_grade=intent.getExtras().getString("art_grade");
        health_education_grade=intent.getExtras().getString("health_grade");
        discipline_grade=intent.getExtras().getString("discipline_grade");
        stud_name=intent.getExtras().getString("name");
        exam_id=intent.getExtras().getString("id");

        if(class_name.equals("7"))student_class="NURSERY";
        else if(class_name.equals("8"))student_class="L.K.G";
        else if(class_name.equals("9"))student_class="U.K.G";
        else if(class_name.equals("10"))student_class="STD-01";
        else if(class_name.equals("11"))student_class="STD-02";
        else if(class_name.equals("12"))student_class="STD-03";
        else if(class_name.equals("13"))student_class="STD-04";
        else if(class_name.equals("14"))student_class="STD-05";
        else if(class_name.equals("15"))student_class="STD-06";
        else if(class_name.equals("16"))student_class="STD-07";
        else if(class_name.equals("17"))student_class="STD-08";
        else if(class_name.equals("18"))student_class="STD-09";
        else if(class_name.equals("19"))student_class="STD-10";
        else if(class_name.equals("22"))student_class="STD-11(core)";
        else if(class_name.equals("23"))student_class="STD-11(core & entrance)";
        else if(class_name.equals("24"))student_class="STD-12(core)";
        else if(class_name.equals("25"))student_class="STD-11";
        else student_class="";

        webView=findViewById(R.id.webview);
        exam_result_items.this.setTitle(term);

        getData(str);



    }

    public void getData(final String str) {



        id=exam_id;

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
                        Toast.makeText(exam_result_items.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
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

                exam_result_item_class history2=new exam_result_item_class(ob.getString("subject_name")
                        ,ob.getString("periodic_test"),ob.getString("note_book"),ob.getString("subject_enrichment")
                        ,ob.getString("annual_examination"),ob.getString("marks_obtained"),ob.getString("grade"));


                str5+=  "\t<tr>\n" +
                        "\t\t<th align=\"left\">"+history2.getSubject()+"</th>\n" +
                        "\t\t<th align=\"right\">"+history2.getPerodic_text()+"</th>\n" +
                        "\t\t<th align=\"right\">"+history2.getNotebook()+"</th>\n" +
                        "\t\t<th align=\"right\">"+history2.getSubject_enrichment()+"</th>\n" +
                        "\t\t<th align=\"right\">"+history2.getAnnual_examination()+"</th>\n" +
                        "\t\t<th align=\"right\">"+history2.getMarks_obtained()+"</th>\n" +
                        "\t\t<th align=\"right\">"+history2.getGrade()+"</th>\n" +
                        "\t</tr>\n" ;



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        String html5="<p align=\"right\" width=\"100%\">AFF No: 1530218/16<br/>NOC No: 14890/18</p>\n" +
                "<h1 align=\"center\" style=\"font-size: 25px; font-weight: bold; margin-top: 10px; margin-bottom: 5px;\">YOUNG PHOENIX PUBLIC SCHOOL</h1>\n" +
                "<p align=\"center\" width=\"100%\">\n" +
                "\t<img src=\"http://yppschool.com/img/logo1.png\" alt=\"Young Phoenix\" width=\"100\" style=\"margin-bottom:20px;\"><br/><br/>\n" +
                "</p>\n" +
                "<p align=\"center\" width=\"100%\">\n" +
                "\t<b>Affiliated to CBSE, New Delhi<br/>\n" +
                "\tDAY - CUM - RESIDENCIAL<br/>\n" +
                "\tGopinathpur, Bhubaneswar - 2, Ph. No.(0674) 2343851, Mob. : 8658599505<br/>\n" +
                "\tACHIEVEMENT PROFILE OF CONTINUOUS 'N' COMPREHENSIVE EVALUATION</b>\n" +
                "</p>\n" +
                "<p align=\"center\" width=\"100%\">\n" +
                "\t<b>ACADEMIC SESSION : 2019-2020<br/>\n" +
                "\tReport Card for "+student_class+"<br/>\n" +
                "\tDate: "+exam_date+"</b>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t<b>Students's Name: "+stud_name+"<br/>\n" +
                "\tMother's / Father's / Guardian's Name : "+parent_name+"<br/>\n" +
                "\tDate of Birth : "+dob+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Class - "+student_class+"</b>\n" +
                "</p>\n" +
                "<table class=\"table table-bordered table-condensed\" border=\"1\" width=\"100%\" cellpadding=\"3\">\n" +
                "\t<tr>\n" +
                "\t\t<th align=\"center\"><b>Scholastic Areas:</b></th>\n" +
                "\t\t<th colspan=\"6\" align=\"center\"><b>"+term+"</b></th>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th align=\"left\"><b>Sub Name:</b></th>\n" +
                "\t\t<th align=\"center\"><b>Periodic Test(10)</b></th>\n" +
                "\t\t<th align=\"center\"><b>Note Book(5)</b></th>\n" +
                "\t\t<th align=\"center\"><b>Subject Enrichment(5)</b></th>\n" +
                "\t\t<th align=\"center\"><b>Annual Examination(80)</b></th>\n" +
                "\t\t<th align=\"center\"><b>Marks Obtained(100)</b></th>\n" +
                "\t\t<th align=\"center\"><b>Grade</b></th>\n" +
                "\t</tr>\n" +str5+


                "</table>\n" +
                "<br/><br/>\n" +
                "<table border=\"1\" width=\"100%\" style=\"margin-top: 5px\" cellpadding=\"2\">\n" +
                "\t<tr>\n" +
                "\t\t<th align=\"left\" width=\"80%\">Co-Scholastic Areas [on a 5-point(A-E) grading scale]</th>\n" +
                "\t\t<th align=\"left\" width=\"20%\">Grade</th>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th align=\"left\">Work Education (Pre-vocational Education)</th>\n" +
                "\t\t<td>"+work_education_grade+"</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th align=\"left\">Art Education</th>\n" +
                "\t\t<td>"+art_education_grade+"</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th align=\"left\">Health & Physical Education</th>\n" +
                "\t\t<td>"+health_education_grade+"</td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<th align=\"left\">Discipline: [on a 5-point (A-E) grading scale]</th>\n" +
                "\t\t<td>"+discipline_grade+"</td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "<p><b>Class Teacher's Remarks:</b> <u>"+remarks+"</u> </p>\n" +
                "<p><b>Promoted to Class:</b> <u>"+promoted_next+"</u> </p>\n" +
                "<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>\n" +
                "<table border=\"0\" style=\"margin-top: 25px\">\n" +
                "\t<tr>\n" +
                "\t\t<td width=\"33%\"><b>Signature of Class Teacher</b></td>\n" +
                "\t\t<td width=\"33%\"><b>Signature of Principal</b></td>\n" +
                "\t\t<td width=\"33%\"><b>Signature of Parents</b></td>\n" +
                "\t</tr>\n" +
                "</table>\n" +
                "<p align=\"center\">INSTRUCTIONS</p>\n" +
                "<p>Grading scale for scholastics areas: Grades are awarded on 8 point grading scale as follows - </p>\n" +
                "<table border=\"0\" style=\"margin-top: 25px\">\n" +
                "\t<tr>\n" +
                "\t\t<td>\n" +
                "\t\t\t<table border=\"1\" style=\"margin-top: 5px; float: left;\" cellpadding=\"2\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<th><b>MARKS RANGE</b></th>\n" +
                "\t\t\t\t\t<th><b>GRADE</b></th>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>91-100</td>\n" +
                "\t\t\t\t\t<td>A1</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>81-90</td>\n" +
                "\t\t\t\t\t<td>A2</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>71-80</td>\n" +
                "\t\t\t\t\t<td>B1</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>61-70</td>\n" +
                "\t\t\t\t\t<td>B2</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td>\n" +
                "\t\t<td>\n" +
                "\t\t\t<table border=\"1\" style=\"margin-top: 5px; float: left;\" cellpadding=\"2\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<th>MARKS RANGE</th>\n" +
                "\t\t\t\t\t<th>GRADE</th>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>51-60</td>\n" +
                "\t\t\t\t\t<td>C1</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>41-50</td>\n" +
                "\t\t\t\t\t<td>C2</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>33-40</td>\n" +
                "\t\t\t\t\t<td>D</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td>32 & Below</td>\n" +
                "\t\t\t\t\t<td>E (Failed)</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</td>\n" +
                "\t</tr>\n" +
                "</table>";
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadDataWithBaseURL(null,html5,"text/html","utf-8",null);

    }


    public void CreatePdf(View view) {

        Context context=exam_result_items.this;
        PrintManager printManager=(PrintManager)exam_result_items.this.getSystemService(context.PRINT_SERVICE);
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
