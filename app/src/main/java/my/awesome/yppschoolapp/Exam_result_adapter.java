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
    public void onBindViewHolder(@NonNull Exam_result_adapter.MyViewHolder holder, final int position) {

         exam_result_Class fm=arrayList.get(position);
        holder.term.setText(fm.getTerm());
        holder.grade.setText(fm.getGrade());
        holder.remarks.setText(fm.getRemarks());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,exam_result_items.class);
                intent.putExtra("term",arrayList.get(position).getTerm());
                intent.putExtra("grade",arrayList.get(position).getGrade());
                intent.putExtra("remarks",arrayList.get(position).getRemarks());
                intent.putExtra("class_name",arrayList.get(position).getClass_name());
                intent.putExtra("session",arrayList.get(position).getSession_id());
                intent.putExtra("dob",arrayList.get(position).getDob());
                intent.putExtra("parent",arrayList.get(position).getParent_name());
                intent.putExtra("promoted",arrayList.get(position).getPromoted_next());
                intent.putExtra("exam_date",arrayList.get(position).getExam_date());
                intent.putExtra("work_grade",arrayList.get(position).getWork_education_grade());
                intent.putExtra("art_grade",arrayList.get(position).getArt_education_grade());
                intent.putExtra("health_grade",arrayList.get(position).getHealth_education_grade());
                intent.putExtra("discipline_grade",arrayList.get(position).getDiscipline_grade());
                intent.putExtra("name",arrayList.get(position).getStu_name());
                intent.putExtra("id",arrayList.get(position).getId());

                mcontext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        TextView term,grade,remarks;
       // CheckBox month;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           // month=itemView.findViewById(R.id.month);
            term=itemView.findViewById(R.id.term);
            grade=itemView.findViewById(R.id.grade);
            remarks=itemView.findViewById(R.id.remarks);

            linearLayout=itemView.findViewById(R.id.linearLayout_result);


        }
    }
}
