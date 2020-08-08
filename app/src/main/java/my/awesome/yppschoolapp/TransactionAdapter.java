package my.awesome.yppschoolapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.awesome.yppschoolapp.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder>{


    private Context mcontext;
    private List<TransHistoryClass> arrayList;//StoreNotice=ListData,mdata=listData.

    public TransactionAdapter(Context mcontext, List<TransHistoryClass> arrayList) {
        this.mcontext =  mcontext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;   //=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        LayoutInflater minflater=LayoutInflater.from(mcontext);
        view=minflater.inflate(R.layout.list_transaction,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        TransHistoryClass sn=arrayList.get(position);
        holder.t1.append(sn.getReceipt_no());
        holder.t2.setText(sn.getReceipt_date());
        holder.t3.setText(sn.getTotal_amount());
        holder.t4.setText(sn.getReceipt_status());
        holder.t5.setText(sn.getPayment_mode());



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,PaymentReceipt.class);
                intent.putExtra("no",arrayList.get(position).getReceipt_no());
                intent.putExtra("date",arrayList.get(position).getReceipt_date());
                intent.putExtra("total",arrayList.get(position).getTotal_amount());
                intent.putExtra("status",arrayList.get(position).getReceipt_status());
                intent.putExtra("mode",arrayList.get(position).getPayment_mode());
                intent.putExtra("name",arrayList.get(position).getStu_name());
                intent.putExtra("class",arrayList.get(position).getStu_class());
                intent.putExtra("session",arrayList.get(position).getStu_session());
                mcontext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView t1,t2,t3,t4,t5;
        //TextView tv_Notice;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            t1=(TextView)itemView.findViewById(R.id.receiptno);
            t2=(TextView)itemView.findViewById(R.id.receiptdate);
            t3=(TextView)itemView.findViewById(R.id.totalamount);
            t4=(TextView)itemView.findViewById(R.id.receiptstatus);
            t5=(TextView)itemView.findViewById(R.id.paymode);
             linearLayout=(LinearLayout)itemView.findViewById(R.id.linearLayout_transaction);



        }
    }

}
