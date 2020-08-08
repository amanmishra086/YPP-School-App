package my.awesome.yppschoolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Exam_result_adapter extends RecyclerView.Adapter<Exam_result_adapter.MyViewHolder> {

    private Context mcontext;
    private List<exam_result_Class> arrayList;

    public Exam_result_adapter(Context mcontext, List<exam_result_Class> arrayList) {
        this.mcontext =  mcontext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;   //=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        LayoutInflater minflater=LayoutInflater.from(mcontext);
        view=minflater.inflate(R.layout.list_results,parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull Exam_result_adapter.MyViewHolder holder, int position) {

         exam_result_Class fm=arrayList.get(position);
        holder.term.setText(fm.getTerm());
        holder.grade.setText(fm.getGrade());
        holder.remarks.setText(fm.getRemarks());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView term,grade,remarks;
       // CheckBox month;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           // month=itemView.findViewById(R.id.month);
            term=itemView.findViewById(R.id.term);
            grade=itemView.findViewById(R.id.grade);
            remarks=itemView.findViewById(R.id.remarks);




        }
    }
}
