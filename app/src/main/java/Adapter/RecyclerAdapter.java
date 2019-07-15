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

import Database.DatabaseHandler;
import Model.ExpenseModel;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<ExpenseModel>expenseModelArrayList;
    private Context context;
    DatabaseHandler db;

    public RecyclerAdapter(ArrayList<ExpenseModel> expenseModelArrayList, Context context) {
        this.expenseModelArrayList = expenseModelArrayList;
        this.context = context;
    }

    public RecyclerAdapter() {
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.dailychild,null,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder myViewHolder, int i) {
        ExpenseModel expenseModel=expenseModelArrayList.get(i);
        myViewHolder.date.setText(expenseModel.getExpensedate());
        myViewHolder.desc.setText(expenseModel.getExpensecontent());
        myViewHolder.catg.setText(expenseModel.getExpensecategory());
        myViewHolder.acc.setText(expenseModel.getExpenseaccount());
        expenseModel=new ExpenseModel();
        db=new DatabaseHandler(context);
        expenseModelArrayList=new ArrayList<>();
        expenseModelArrayList=db.getAllExpenses();

        for(int j=0;j< expenseModelArrayList.size();j++) {
            expenseModel = expenseModelArrayList.get(i);
            if (expenseModel.getExpenseType().equals("income")) {
                myViewHolder.amount.setTextColor(Color.BLUE);
                myViewHolder.amount.setText("$"+expenseModel.getExpenseAmount());




            } else if (expenseModel.getExpenseType().equals("expenses")) {
                myViewHolder.amount.setTextColor(Color.RED);
                myViewHolder.amount.setText("$"+expenseModel.getExpenseAmount());



            }else{
                Toast.makeText(context, "No Value", Toast.LENGTH_SHORT).show();

            }
        }

                            }

    @Override
    public int getItemCount() {
        return expenseModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,desc,amount,catg,acc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.dailydate);
            desc=itemView.findViewById(R.id.dailydesc);
            amount=itemView.findViewById(R.id.dailyamount);
            catg=itemView.findViewById(R.id.dailycat);
            acc=itemView.findViewById(R.id.dailyaccount);
        }
    }
}
