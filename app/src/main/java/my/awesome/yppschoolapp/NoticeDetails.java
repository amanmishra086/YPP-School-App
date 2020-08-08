package my.awesome.yppschoolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NoticeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);

        NoticeDetails.this.setTitle("Notice Details");

       TextView subject=findViewById(R.id.Subject_detail);
        TextView notice=findViewById(R.id.Notice_detail);


        Intent intent=getIntent();
        final String Sub=intent.getExtras().getString("Subject");
        String Not=intent.getExtras().getString("Notice");

        subject.setText(Sub);
        notice.setText(Not);
    }
}
