package my.awesome.yppschoolapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PayPendingFee_adapter extends RecyclerView.Adapter<PayPendingFee_adapter.MyViewHolder> {

    private Context mcontext;
    private List<FrameClass> arrayList;//StoreNotice=ListData,mdata=listData.
    SharedPreferences sharedpreferences;

    ArrayList<Integer> feesList=new ArrayList<>();
    int finalAmount=0;
    int amt=0;




    public PayPendingFee_adapter(Context mcontext, List<FrameClass> arrayList) {
        this.mcontext =  mcontext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;   //=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        LayoutInflater minflater=LayoutInflater.from(mcontext);
        view=minflater.inflate(R.layout.item_pending_fee,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final FrameClass fm = arrayList.get(position);
        holder.month.setText(fm.getMonth());
        holder.fee.setText(fm.getFee());
        holder.fine.setText(fm.getFine());
        holder.total.setText(fm.getStatus());
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }


        public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView month,fee,fine,total;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            month=itemView.findViewById(R.id.month);
            fee=itemView.findViewById(R.id.fee);
            fine=itemView.findViewById(R.id.fine);
            total=itemView.findViewById(R.id.total);




        }
    }

}
