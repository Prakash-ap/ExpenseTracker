package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.expensetracker.R;

import java.util.ArrayList;

import Model.Ex_Catg_model;
import Model.Ex_Chart_Catg;
import Model.In_Chart_Catg;

public class RecyclerExChartAdapter extends RecyclerView.Adapter<RecyclerExChartAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Ex_Chart_Catg>ex_chart_catgArrayList;

    public RecyclerExChartAdapter(Context context, ArrayList<Ex_Chart_Catg> ex_chart_catgArrayList) {
        this.context = context;
        this.ex_chart_catgArrayList = ex_chart_catgArrayList;
    }

    @NonNull
    @Override
    public RecyclerExChartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.expensechartchild,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerExChartAdapter.MyViewHolder myViewHolder, int i) {
        Ex_Chart_Catg ex_chart_catg=ex_chart_catgArrayList.get(i);
        myViewHolder.category.setText(ex_chart_catg.getEx_chartcat_type());
        myViewHolder.category_amount.setText(ex_chart_catg.getEx_chart_amount());

    }

    @Override
    public int getItemCount() {
        return ex_chart_catgArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView category,category_amount;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            category=itemView.findViewById(R.id.catexpensechart);
            category_amount=itemView.findViewById(R.id.amtexpensechart);
        }
    }
}
