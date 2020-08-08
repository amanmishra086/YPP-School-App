package my.awesome.yppschoolapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    private Context mcontext;
    private List<NoticeClass> arrayList;//StoreNotice=ListData,mdata=listData.

    public NoticeAdapter(Context mcontext,List<NoticeClass> arrayList) {
        this.mcontext =  mcontext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;   //=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        LayoutInflater minflater=LayoutInflater.from(mcontext);
        view=minflater.inflate(R.layout.recyclerviewnoticeitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        NoticeClass sn=arrayList.get(position);
//        if(sn.getTitle().equals(":")){
//            holder.subject.setText("No Current Notice Found");
//            holder.notice_no.setText("00");
//        }
        holder.subject.setText(sn.getTitle());
        holder.notice_no.setText(sn.getNo());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mcontext,NoticeDetails.class);
                intent.putExtra("Subject",arrayList.get(position).getTitle());
                intent.putExtra("Notice",arrayList.get(position).getDescription());
                mcontext.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subject,notice_no;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            notice_no=(TextView)itemView.findViewById(R.id.rv_notice_no);
            subject=(TextView)itemView.findViewById(R.id.rv_subject);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearLayout);

        }
    }
}
