package my.awesome.yppschoolapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Frame_adapter extends RecyclerView.Adapter<Frame_adapter.MyViewHolder> {

    private Context mcontext;
    private List<FrameClass> arrayList;//StoreNotice=ListData,mdata=listData.
    SharedPreferences sharedpreferences;

    ArrayList <Integer> feesList=new ArrayList<>();
    int finalAmount=0;
    int finalFee=0;
    int finalFine=0;
    int amt=0;

    String fee="0",fine="0";


    public Frame_adapter(Context mcontext, List<FrameClass> arrayList) {
        this.mcontext =  mcontext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;   //=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        LayoutInflater minflater=LayoutInflater.from(mcontext);
        view=minflater.inflate(R.layout.frame_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

       final FrameClass fm=arrayList.get(position);
        holder.month.setText(fm.getMonth());
        holder.fee.setText(fm.getFee());
        holder.fine.setText(fm.getFine());
        holder.status.setText(fm.getStatus());


       // Toast.makeText(mcontext, "fm"+fm.getStatus(), Toast.LENGTH_SHORT).show();

        if(fm.getStatus().toLowerCase().equals("paid")) {

            holder.month.setEnabled(false);
            holder.month.setChecked(true);
        }

//        holder.month.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                final boolean isChecked = holder.month.isChecked();
//                for (int i=0; i<arrayList.size();i++) {
//                    if (isChecked) {
//                        if (!arrayListUser.contains(arrayList.get(position).getMonth()))
//                            arrayListUser.add(i, arrayList.get(position).getMonth());
//                        arrayData=arrayListUser.toString().replace("[", "").replace("]", "").trim();
//                    } else {
//                        arrayListUser.remove(arrayList.get(position).getMonth());
//                        arrayData=arrayListUser.toString().replace("[", "").replace("]", "").trim();
//                    }
//                }
//                Toast.makeText(mcontext, arrayData, Toast.LENGTH_SHORT).show();
//            }
//        });


//        holder.month.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                final boolean isChecked = holder.month.isChecked();
//               // for (int i=0; i<arrayList.size();i++) {
//                    if (isChecked) {
//                        if (!feesList.contains(arrayList.get(position).getMonth()))
//                            arrayListUser.add( arrayList.get(position).getMonth());
//                           // arrayListUser.add(i, arrayList.get(position).getMonth());
//                        arrayData=arrayListUser.toString().replace("[", "").replace("]", "").trim();
//                    } else {
//                        arrayListUser.remove(arrayList.get(position).getMonth());
//                        arrayData=arrayListUser.toString().replace("[", "").replace("]", "").trim();
//                    }
//                //}
//                Toast.makeText(mcontext, arrayData, Toast.LENGTH_SHORT).show();
//            }
//        });


//        holder.month.setOnCheckedChangeListener(null);
//        holder.month.setSelected(fm.getMonth());
//        holder.month.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    fm.setSelected(true);
//                }else {
//                    fm.setSelected(false);
//                }
//            }
//        });
//        holder.month.setChecked(fm.isSelected());

//        holder.month.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//                // TODO Auto-generated method stub
//                String fee=arrayList.get(position).getFee();
//                String fine=arrayList.get(position).getFine();
//                double amount=Double.parseDouble(fee)+Double.parseDouble(fine);
//                amt=(int)amount;
//                if (isChecked) {
//                    arrayListUserstr.add(amt);
//                    Toast.makeText(buttonView.getContext(),Boolean.toString(arrayListUserstr.add(amt)), Toast.LENGTH_SHORT).show();
//                } else {
//                    arrayListUserstr.remove(amt);
//                }
//
//            }
//        });



        holder.month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean isChecked = holder.month.isChecked();
                 //for (int i=0; i<arrayList.size();i++) {
                 fee=arrayList.get(position).getFee();
                 fine=arrayList.get(position).getFine();
                double payfee=Double.parseDouble(fee);
                double payfine=Double.parseDouble(fine);



                double amount=Double.parseDouble(fee)+Double.parseDouble(fine);
                amt=(int)amount;
                    if (isChecked) {

                        finalFee+=(int)payfee;
                        finalFine+=payfine;
                         finalAmount+=amt;

                        //arrayListUser.add(amt);
                        //arrayListUser.add(i, amt);

                    } else {
//                        arrayListUser.remove(arrayList.get(position).getMonth());
//                        arrayData=arrayListUser.toString().replace("[", "").replace("]", "").trim();
                       finalFee-=payfee;
                       finalFine-=payfine;
                        finalAmount-=amt;
                        //arrayListUser.remove(amt);
                    }

                Intent intent = new Intent("custom-message");
                intent.putExtra("fee",""+finalFee);
                intent.putExtra("fine",""+finalFine);
                intent.putExtra("total",""+finalAmount);
                LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);

                // }
//                 for(int i=0;i<arrayListUser.size();i++)
//                     finalAmount+=arrayListUser.get(i);

               // Toast.makeText(mcontext, "Amount added "+finalAmount+"/-", Toast.LENGTH_SHORT).show();

//                String fee=arrayList.get(position).getFee();
//                String fine=arrayList.get(position).getFine();
//                double amount=Double.parseDouble(fee)+Double.parseDouble(fine);
//
//                int amt=(int)amount;
//                feesList.add(amt);

               // Toast.makeText(mcontext, ""+fee+"  "+fine+" "+amount, Toast.LENGTH_SHORT).show();

//                 sharedpreferences = mcontext.getSharedPreferences("Monthlyfees", Context.MODE_PRIVATE);
//
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//                editor.putString("fee", fee);
//                editor.putString("fine", fine);
//               // editor.putString("Roll", userId.getText().toString());
//                editor.apply();
           }
       });

        Intent intent = new Intent("custom-message");
        intent.putExtra("fee",""+finalFee);
        intent.putExtra("fine",""+finalFine);
        intent.putExtra("total",""+finalAmount);
        LocalBroadcastManager.getInstance(mcontext).sendBroadcast(intent);


    }
    public int getFinalFee(){
        return finalFee;
    }
    public int getFinalFine(){
        return finalFine;
    }

    public int getFinalAmount(){
        return finalAmount;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fee,fine,status;
        CheckBox month;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            month=itemView.findViewById(R.id.month);
            fee=itemView.findViewById(R.id.fee);
            fine=itemView.findViewById(R.id.fine);
            status=itemView.findViewById(R.id.status);




        }
    }
}
