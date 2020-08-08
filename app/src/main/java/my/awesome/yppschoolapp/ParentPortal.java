package my.awesome.yppschoolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.awesome.yppschoolapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ParentPortal extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_portal);

        ParentPortal.this.setTitle("Dashboard");

//        bottomNavigationView=findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
//
//                switch (menuItem.getItemId()) {
//                    case R.id.home:
//
//                        break;
//                    case R.id.logout:
//
//                        finish();
//                        startActivity(new Intent(ParentPortal.this,LoginActivity.class));
//                        break;
//
//                }
//
//            }
//        });
//
//
//
//
//
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.nav_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        alert = new AlertDialog.Builder(this);

        int id = item.getItemId();
        if (id == R.id.logout) {
            //Toast.makeText(this, "logout selected", Toast.LENGTH_SHORT).show();

            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    finish();
                    startActivity(new Intent(ParentPortal.this, LoginActivity.class));

                }
            });
            alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = alert.create();
            dialog.setMessage("are you sure want to exit");
            dialog.show();


            return true;
        }
        if(id==R.id.home){
            Toast.makeText(this, "Already At Home Page", Toast.LENGTH_SHORT).show();
            return true;
        }

        return true;
    }




    public void onclicknotice(View view) {


        startActivity(new Intent(ParentPortal.this,NoticeList.class));

    }

    public void onclickattendance(View view) {

        startActivity(new Intent(ParentPortal.this,DisplayAttendance.class));

    }

    //@Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//        switch (menuItem.getItemId()) {
//            case R.id.page_1:
//
//
//            case R.id.page_2:
//                startActivity(new Intent(ParentPortal.this,LoginActivity.class));
//        }
//        return true;
//    }

    public void MyProfile(View view) {
        startActivity(new Intent(ParentPortal.this,StudentProfile.class));
    }

    public void payment(View view) {
       // startActivity(new Intent(ParentPortal.this,AddFees.class));
        startActivity(new Intent(ParentPortal.this,PaymentTab.class));
    }

    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ParentPortal.super.onBackPressed();
                        quit();
                    }
                }).create().show();
    }


    public void quit() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(start);
    }

    public void SyllabusClick(View view) {
        //Toast.makeText(this, "No Classwork Found", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ParentPortal.this,ClassTask.class));
    }

    public void HomeWorkClick(View view) {
       // Toast.makeText(this, "No Homework Found", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ParentPortal.this,HomeTask.class));
    }

    public void ccaresult(View view) {
        //Toast.makeText(this, "Not Uploaded yet", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ParentPortal.this,cca_result.class));
    }

    public void examresult(View view) {
       // Toast.makeText(this, "Not Uploaded yet", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ParentPortal.this,exam_result.class));
    }
}
