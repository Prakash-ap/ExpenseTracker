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

import Model.ExpenseModel;
import Model.In_Chart_Catg;

public class RecyclerIncomeChartAdapter extends RecyclerView.Adapter<RecyclerIncomeChartAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<In_Chart_Catg>in_chart_catgArrayList;

    public RecyclerIncomeChartAdapter(Context context, ArrayList<In_Chart_Catg> in_chart_catgArrayList) {
        this.context = context;
        this.in_chart_catgArrayList = in_chart_catgArrayList;
    }

    @NonNull
    @Override
    public RecyclerIncomeChartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.incomechartchild,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerIncomeChartAdapter.MyViewHolder myViewHolder, int i) {
        In_Chart_Catg in_chart_catg=in_chart_catgArrayList.get(i);
        myViewHolder.category.setText(in_chart_catg.getIn_chartcat_type());
        myViewHolder.category_amount.setText(in_chart_catg.getOn_chart_amount());

    }

    @Override
    public int getItemCount() {
        return in_chart_catgArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView category,category_amount;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            category=itemView.findViewById(R.id.catincomechart);
            category_amount=itemView.findViewById(R.id.amtincomechart);
        }
    }
}
