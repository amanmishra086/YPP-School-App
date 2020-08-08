package my.awesome.yppschoolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import my.awesome.yppschoolapp;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

    private Context mcontext;
    private List<StoreAttendanceClass> arrayList;//StoreNotice=ListData,mdata=listData.

    public AttendanceAdapter(Context mcontext, List<StoreAttendanceClass> arrayList) {
        this.mcontext =  mcontext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;   //=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        LayoutInflater minflater=LayoutInflater.from(mcontext);
        view=minflater.inflate(R.layout.list_attendance,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        StoreAttendanceClass sn=arrayList.get(position);
        holder.date.setText(sn.getDate());
        holder.status.setText(sn.getStatus());
        holder.remarks.setText(sn.getRemarks());

//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(mcontext,NoticeDetail.class);
//                intent.putExtra("Subject",arrayList.get(position).getSubject());
//                intent.putExtra("Notice",arrayList.get(position).getNotice());
//                mcontext.startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date,status,remarks;
        //TextView tv_Notice;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date=(TextView)itemView.findViewById(R.id.rv_date);
            status=(TextView)itemView.findViewById(R.id.rv_status);
            remarks=(TextView)itemView.findViewById(R.id.rv_remarks);
           // linearLayout=(LinearLayout)itemView.findViewById(R.id.linearLayout_id);



        }
    }

}
