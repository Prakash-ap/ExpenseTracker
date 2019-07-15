package com.example.android.expensetracker;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Adapter.RecyclerAdapter;
import Adapter.WeekRecyclerAdapter;
import Database.DatabaseHandler;
import Model.ExpenseModel;
import Model.WeekModel;


public class WeeklyFragment extends Fragment {

    DatabaseHandler db;
    private RecyclerView recyclerView;
    private WeekRecyclerAdapter recyclerAdapter;
    private ExpenseModel expenseModel;
    private WeekModel weekModel;
    private ArrayList<ExpenseModel> expenseModelArrayList;
    private ArrayList<WeekModel> weekModelArrayList;
    long tempincomew1 = 0;
    long tempexpensew1 = 0;

    long tempincomew2 = 0;
    long tempexpensew2 = 0;

    long tempincomew3 = 0;
    long tempexpensew3 = 0;

    long tempincomew4 = 0;
    long tempexpensew4 = 0;

    long tempincomew5 = 0;
    long tempexpensew5 = 0;


    String income, expense, total;
    String in, ex, to;

    // int const_week = 7;
    private String week_date;
    double total_amount = 0.0;
    private static final String TAG = "WeeklyFragment";

    ArrayList<ExpenseModel> filtered_week = new ArrayList<ExpenseModel>();

    int weeknumber;
    int currentweeknumber, curryear;

    int year;
    String month, currmonth;
    boolean found=false;
    int week1,week2,week3,week4,week5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekly, container, false);
        db = new DatabaseHandler(getContext());


        recyclerView = view.findViewById(R.id.weeklyrecycle);
        expenseModelArrayList = new ArrayList<>();
        expenseModelArrayList = db.getAllExpenses();
        Calendar calendar = Calendar.getInstance();
        currentweeknumber = calendar.get(Calendar.WEEK_OF_YEAR);
        curryear = calendar.get(Calendar.YEAR);
        currmonth = new SimpleDateFormat("MMMM").format(calendar.getTime());
        int ndays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int[] weeks = new int[ndays];
        for (int i = 0; i < ndays; i++) {
            weeks[i] = Integer.parseInt(String.format("%d", calendar.get(Calendar.WEEK_OF_YEAR)));
            calendar.add(Calendar.DATE, 5);
        }
       week1=weeks.length;
      /* week2=weeks.
       week3=weeks.
      week4=weeks.
       week5=weeks*/



        weekModel=new WeekModel();
        weekModelArrayList=new ArrayList<>();


        for (int j = 0; j < expenseModelArrayList.size(); j++) {

            expenseModel = expenseModelArrayList.get(j);
            weeknumber= Integer.parseInt(expenseModel.getExpenseWeek());
            year = Integer.parseInt(expenseModel.getExpenseYear());
            month = expenseModel.getExpenseMonth();
            for (int weekno : weeks) {



                if (weekno == weeknumber)


                    found = true;

            }


            if (year == curryear) {
                if (month.equals(currmonth)) {

                        if (found) {

                            if (expenseModel.getExpenseType().equals("income")) {
                                in = expenseModel.getExpenseAmount();
                                if (in.equals("")) {

                                } else {


                                    tempincomew1 += Long.parseLong(in);






                                }
                            } else if (expenseModel.getExpenseType().equals("expenses")) {
                                ex = expenseModel.getExpenseAmount();
                                if (ex.equals("")) {

                                } else {
                                    tempexpensew1 += Long.parseLong(ex);


                                 /*   weekModel.setWeektotalincome(String.valueOf(tempincomew1));
                                    weekModel.setWeektotalexpense(String.valueOf(tempexpensew1));
                                    weekModel.setDate(String.valueOf(weeknumber));


                                    weekModelArrayList.add(weekModel);
                                    recyclerAdapter = new WeekRecyclerAdapter(weekModelArrayList, getContext());
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                                    recyclerView.setAdapter(recyclerAdapter);
*/


                                }
                            }



                        } else {
                        }
                    }

                } else {

                }



                }
        weekModel.setDate(String.valueOf(weeknumber));

        weekModel.setWeektotalincome(String.valueOf("$"+tempincomew1));
         weekModel.setWeektotalexpense(String.valueOf("$"+tempexpensew1));
        weekModelArrayList.add(weekModel);


        recyclerAdapter = new WeekRecyclerAdapter(weekModelArrayList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
        return view;


        }

    }












      /*  expenseModelArrayList = new ArrayList<>();
        expenseModelArrayList = db.getAllExpenses();
        expenseModel = new ExpenseModel();

         expenseModel=expenseModelArrayList;
     //   expenseModel = expenseModelArrayList.get(expenseModelArrayList.size()-1);
        WeekModel weekModel=new WeekModel();
        weekModelArrayList=new ArrayList<>();


        Calendar c = Calendar.getInstance();
        weeknumber = c.get(Calendar.WEEK_OF_YEAR);
      //  currentweeknumber = Integer.parseInt(expenseModel.getExpenseWeek());

        if (currentweeknumber == weeknumber) {
            for (int i = 0; i < expenseModelArrayList.size(); i++) {


                if (expenseModel.getExpenseType().equals("income")) {
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {
                        // Toast.makeText(getContext(), "Enter the amount", Toast.LENGTH_SHORT).show();
                    } else {
                        tempincome += Long.parseLong(in);
                        weekModel.setWeektotalincome(String.valueOf(tempincome));
                        weekModelArrayList.add(weekModel);


                  *//*  myViewHolder.income.setText(String.valueOf(tempincome));
                    myViewHolder.income.setTextColor(Color.BLUE);*//*


                    }


                } else if (expenseModel.getExpenseType().equals("expenses")) {
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {
                        //   Toast.makeText(getContext(), "Enter the amount", Toast.LENGTH_SHORT).show();


                    } else {
                        tempexpense = tempexpense + Long.parseLong(ex);
                        weekModel.setWeektotalincome(String.valueOf(tempexpense));
                        weekModelArrayList.add(weekModel);

                    *//*myViewHolder.expense.setText(String.valueOf(tempexpense));
                    myViewHolder.expense.setTextColor(Color.RED);
*//*
                    }

                }

                else {
                    Toast.makeText(getContext(), "No Value", Toast.LENGTH_SHORT).show();

                }
            }
        }else{

        }


                totalexpense=tempincome-tempexpense;
            income = String.valueOf(tempincome);
            expense = String.valueOf(tempexpense);



            recyclerAdapter = new WeekRecyclerAdapter(weekModelArrayList,getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(recyclerAdapter);
            return view;

        }
*/

        //  expenseModel.getExpensedate();


      /*  if (const_week != 0)
        {
            week_date = selectDate(const_week);
        }


        filterMethod();

        return view;
    }

    private void filterMethod() {

        for (int i = 0 ; i < expenseModelArrayList.size(); i++)
        {
            ExpenseModel expenseModel = expenseModelArrayList.get(i);

            if (expenseModel.getExpensedate().equals(week_date))
            {
                filtered_week.add(expenseModel);

                Log.d(TAG, "filterMethod: "+filtered_week);

                total_amount = total_amount +  Double.valueOf(expenseModel.getExpenseAmount());
                if (expenseModel.getExpenseType().equals("income")) {
                    in=expenseModel.getExpenseAmount();

                    tempincome += Long.parseLong(in);

                } else if (expenseModel.getExpenseType().equals("expenses")) {
                    ex=expenseModel.getExpenseAmount();
                    tempexpense =tempexpense +  Long.parseLong(ex);

                }else {
                    Toast.makeText(getContext(), "No Value", Toast.LENGTH_SHORT).show();

                }


            }

        }
*/
        //   }




    /*private String selectDate(int start) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, - start);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/YY");
//        Date date = calendar.getTimeInMillis();
        String end = format.format(calendar.getTime());
        Log.d("", "selected30Dates: " + end);

        const_week --;

        return end;


    }
*/



          /* weeknumber = Integer.parseInt(expenseModel.getExpenseWeek());

            if (weeknumber == 1) {

                if (expenseModel.getExpenseType().equals("income")) {
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {

                    } else {


                        tempincomew1 += Long.parseLong(in);


                    }
                } else if (expenseModel.getExpenseType().equals("expenses")) {
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {

                    } else {
                        tempexpensew1 += Long.parseLong(ex);


                        //tempexpensew1=0;
                    }
                }


            } else if (weeknumber == 2) {


                if (expenseModel.getExpenseType().equals("income")) {
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {

                    } else {


                        tempincomew2 += Long.parseLong(in);

                    }
                } else if (expenseModel.getExpenseType().equals("expenses")) {
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {

                    } else {
                        tempexpensew2 += Long.parseLong(ex);

                    }
                }


            } else if (weeknumber == 5) {


                if (expenseModel.getExpenseType().equals("income")) {
                    in = expenseModel.getExpenseAmount();
                    if (in.equals("")) {

                    } else {


                        tempincomew2 += Long.parseLong(in);

                    }
                } else if (expenseModel.getExpenseType().equals("expenses")) {
                    ex = expenseModel.getExpenseAmount();
                    if (ex.equals("")) {

                    } else {
                        tempexpensew2 += Long.parseLong(ex);

                    }
                }*//*else if (weeknumber == 3) {
                            if (expenseModel.getExpenseType().equals("income")) {
                                in = expenseModel.getExpenseAmount();
                                if (in.equals("")) {

                                } else {


                                    tempincomew3 += Long.parseLong(in);
                                   weekModel.setWeektotalincome(String.valueOf(tempincomew3));
                                }
                            } else if (expenseModel.getExpenseType().equals("expenses")) {
                                ex = expenseModel.getExpenseAmount();
                                if (ex.equals("")) {

                                } else {
                                    tempexpensew3 += Long.parseLong(ex);
                                    weekModel.setWeektotalexpense(String.valueOf(tempexpensew3));
                                }
                            }

                        } else if (weeknumber == 4) {
                            if (expenseModel.getExpenseType().equals("income")) {
                                in = expenseModel.getExpenseAmount();
                                if (in.equals("")) {

                                } else {


                                    tempincomew4 += Long.parseLong(in);
                                  //  weekModel.setWeektotalincome(String.valueOf(tempincomew4));
                                }
                            } else if (expenseModel.getExpenseType().equals("expenses")) {
                                ex = expenseModel.getExpenseAmount();
                                if (ex.equals("")) {

                                } else {
                                    tempexpensew4 += Long.parseLong(ex);
                                  //  weekModel.setWeektotalincome(String.valueOf(tempexpensew4));
                                }
                            }
                        } else if (weeknumber == 5) {
                            if (expenseModel.getExpenseType().equals("income")) {
                                in = expenseModel.getExpenseAmount();
                                if (in.equals("")) {

                                } else {


                                    tempincomew5 += Long.parseLong(in);
                                  //  weekModel.setWeektotalincome(String.valueOf(tempincomew5));
                                }
                            } else if (expenseModel.getExpenseType().equals("expenses")) {
                                ex = expenseModel.getExpenseAmount();
                                if (ex.equals("")) {

                                } else {
                                    tempexpensew5 += Long.parseLong(ex);
                                  //  weekModel.setWeektotalincome(String.valueOf(tempexpensew5));
                                }
                            }*/
                       /* }else {

                        }*/

                        /*weekModel.setWeektotalincome(String.valueOf(tempincomew1));
                        weekModel.setWeektotalexpense(String.valueOf(tempexpensew1));*/
               /* weekModel = new WeekModel();
                weekModelArrayList = new ArrayList<>();

                weekModel.setWeektotalincome(String.valueOf(tempincomew2));
                weekModel.setWeektotalexpense(String.valueOf(tempexpensew2));
                weekModel.setDate(String.valueOf(weeknumber));


                weekModelArrayList.add(weekModel);*/



                           /* weekModel.setWeektotalincome(String.valueOf(tempincomew1));
                            weekModel.setWeektotalexpense(String.valueOf(tempexpensew1));

        weekModel.setWeektotalincome(String.valueOf(tempincomew2));
        weekModel.setWeektotalexpense(String.valueOf(tempexpensew2));

        weekModel.setWeektotalincome(String.valueOf(tempincomew3));
        weekModel.setWeektotalexpense(String.valueOf(tempexpensew3));

        weekModel.setWeektotalincome(String.valueOf(tempincomew4));
        weekModel.setWeektotalexpense(String.valueOf(tempexpensew4));

        weekModel.setWeektotalincome(String.valueOf(tempincomew5));
        weekModel.setWeektotalexpense(String.valueOf(tempexpensew5));
*/
