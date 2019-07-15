package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.expensetracker.R;

import java.util.ArrayList;
import java.util.Calendar;

import Database.DatabaseHandler;
import Model.ExpenseModel;
import Model.MonthModel;

public class MonthlyRecyclerAdapter extends RecyclerView.Adapter<MonthlyRecyclerAdapter.MyViewHolder> {
    long tempincome=0;
    long tempexpense=0;
    long totalexpense=0;
    TextView income,expense,total;
    DatabaseHandler db;
    String in,ex,to;
    private ArrayList<ExpenseModel> expenseModelArrayList;
    private ArrayList<MonthModel> monthModelArrayList;
    private Context context;
    MonthModel monthModel;

    /*public MonthlyRecyclerAdapter(ArrayList<ExpenseModel> expenseModelArrayList, Context context) {
        this.expenseModelArrayList = expenseModelArrayList;
        this.context = context;
    }*/

    public MonthlyRecyclerAdapter(ArrayList<MonthModel> monthModelArrayList, Context context) {
        this.monthModelArrayList = monthModelArrayList;
        this.context = context;
    }

    public MonthlyRecyclerAdapter() {
    }

    @NonNull
    @Override
    public MonthlyRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.monthlychild, null, false);
        return new MonthlyRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyRecyclerAdapter.MyViewHolder myViewHolder, int i) {

       monthModel=monthModelArrayList.get(i);
        //  expenseModel=expenseModelArrayList.get(i);




        //  WeekModel expenseModel = weekModelArrayList.get(i);


        myViewHolder.date.setText(monthModel.getMonth());

        myViewHolder.income.setText(monthModel.getMonthtotalincome());
        myViewHolder.expense.setText(monthModel.getMonthtotalexpense());
        myViewHolder.income.setTextColor(Color.BLUE);
        myViewHolder.expense.setTextColor(Color.RED);




    }

    @Override
    public int getItemCount() {
        return monthModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, income, expense;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.monthlydate);
            income = itemView.findViewById(R.id.monthlyincome);
            expense = itemView.findViewById(R.id.monthlyexpense);

        }
    }
}
