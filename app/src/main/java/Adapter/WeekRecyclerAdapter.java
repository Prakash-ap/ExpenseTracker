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
import Model.WeekModel;

public class WeekRecyclerAdapter extends RecyclerView.Adapter<WeekRecyclerAdapter.MyViewHolder> {
    private ArrayList<WeekModel> weekModelArrayList;
    private ArrayList<ExpenseModel>expenseModelArrayList;
    ExpenseModel expenseModel;
    WeekModel weekModel;
    private Context context;
    String week;
    long tempincome=0;
    long tempexpense=0;
    long totalexpense=0;
    TextView income,expense,total;
    DatabaseHandler db;
    String in,ex,to;
    int weeknumber;
    int currentweeknumber;


    public WeekRecyclerAdapter(ArrayList<WeekModel> weekModelArrayList, Context context) {
        this.weekModelArrayList = weekModelArrayList;
        this.context = context;
       // expenseModelArrayList=new ArrayList<>();

    }

    public WeekRecyclerAdapter() {
    }

    @NonNull
    @Override
    public WeekRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.weeklychild, null, false);
        return new WeekRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekRecyclerAdapter.MyViewHolder myViewHolder, int i) {
        weekModel=weekModelArrayList.get(i);
      //  expenseModel=expenseModelArrayList.get(i);




     //  WeekModel expenseModel = weekModelArrayList.get(i);


            myViewHolder.date1.setText(weekModel.getDate());
           /* myViewHolder.date2.setText(weekModel.getDate());
            myViewHolder.date3.setText(weekModel.getDate());
            myViewHolder.date4.setText(weekModel.getDate());
            myViewHolder.date5.setText(weekModel.getDate());
*/
        myViewHolder.income1.setText(weekModel.getWeektotalincome());
        myViewHolder.expense1.setText(weekModel.getWeektotalexpense());
        myViewHolder.income1.setTextColor(Color.BLUE);
        myViewHolder.expense1.setTextColor(Color.RED);
/*
        myViewHolder.income2.setText(weekModel.getWeektotalincome());
        myViewHolder.expense2.setText(weekModel.getWeektotalexpense());
        myViewHolder.income2.setTextColor(Color.BLUE);
        myViewHolder.expense2.setTextColor(Color.RED);*/

      /*  myViewHolder.income3.setText(weekModel.getWeektotalincome());
        myViewHolder.expense3.setText(weekModel.getWeektotalexpense());
        myViewHolder.income3.setTextColor(Color.BLUE);
        myViewHolder.expense3.setTextColor(Color.RED);

        myViewHolder.income4.setText(weekModel.getWeektotalincome());
        myViewHolder.expense4.setText(weekModel.getWeektotalexpense());
        myViewHolder.income4.setTextColor(Color.BLUE);
        myViewHolder.expense4.setTextColor(Color.RED);

        myViewHolder.income5.setText(weekModel.getWeektotalincome());
        myViewHolder.expense5.setText(weekModel.getWeektotalexpense());
        myViewHolder.income5.setTextColor(Color.BLUE);
        myViewHolder.expense5.setTextColor(Color.RED);
*/


          /*  if(weeknumber==currentweeknumber) {

                for (int j = 0; j < expenseModelArrayList.size(); j++) {

                    expenseModel = expenseModelArrayList.get(i);
                    if (expenseModel.getExpenseType().contains("income")) {
                        in = expenseModel.getExpenseAmount();
                        if (in.equals("")) {

                        } else {



                            tempincome += Long.parseLong(in);
                        }
                    } else if (expenseModel.getExpenseType().contains("expenses")) {
                        ex = expenseModel.getExpenseAmount();
                        if (ex.equals("")) {

                        } else {
                            tempexpense += Long.parseLong(ex);
                        }
                    }
                }
            }else {

            }
*/







        }




    @Override
    public int getItemCount() {
        return weekModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date1,income1,expense1;
        TextView date2,income2,expense2;
        TextView date3,income3,expense3;
        TextView date4,income4,expense4;
        TextView date5,income5,expense5;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date1 = itemView.findViewById(R.id.weeklydate1);
            income1 = itemView.findViewById(R.id.weeklyincome1);
            expense1 = itemView.findViewById(R.id.weeklyexpense1);

           /* date2 = itemView.findViewById(R.id.weeklydate2);
            income2 = itemView.findViewById(R.id.weeklyincome2);
            expense2 = itemView.findViewById(R.id.weeklyexpense2);

            date3 = itemView.findViewById(R.id.weeklydate3);
            income3 = itemView.findViewById(R.id.weeklyincome3);
            expense3 = itemView.findViewById(R.id.weeklyexpense3);

            date4 = itemView.findViewById(R.id.weeklydate4);
            income4 = itemView.findViewById(R.id.weeklyincome4);
            expense4 = itemView.findViewById(R.id.weeklyexpense4);

            date5 = itemView.findViewById(R.id.weeklydate5);
            income5= itemView.findViewById(R.id.weeklyincome5);
            expense5 = itemView.findViewById(R.id.weeklyexpense5);*/

        }
    }
}