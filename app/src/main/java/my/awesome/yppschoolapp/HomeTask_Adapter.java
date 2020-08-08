package my.awesome.yppschoolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeTask_Adapter extends RecyclerView.Adapter<HomeTask_Adapter.MyViewHolder>{

    private Context mcontext;
    private List<classtask_class> arrayList;

    public HomeTask_Adapter(Context mcontext, List<classtask_class> arrayList) {
        this.mcontext = mcontext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;   //=LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        LayoutInflater minflater=LayoutInflater.from(mcontext);
        view=minflater.inflate(R.layout.list_hometask,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTask_Adapter.MyViewHolder holder, int position) {
        classtask_class fm=arrayList.get(position);
        holder.date.setText(fm.getDate());
        // holder.subject.setText(fm.getSubject());
        holder.subject.append(fm.getSubject());
        holder.task.setText(fm.getTask_desc());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date,subject,task;
        // CheckBox month;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // month=itemView.findViewById(R.id.month);
            date=itemView.findViewById(R.id.date);
            subject=itemView.findViewById(R.id.subject);
            task=itemView.findViewById(R.id.description);




        }
    }

}
