package com.example.android.expensetracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import Adapter.RecyclerAdapter;
import Database.DatabaseHandler;
import Model.ExpenseModel;


public class DailyFragment extends Fragment {
    DatabaseHandler db;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ExpenseModel expenseModel;
    private ArrayList<ExpenseModel>expenseModelArrayList;
    private boolean sortAscending=true;
    private boolean unSorted=true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_daily, container, false);
        db=new DatabaseHandler(getContext());


        recyclerView=view.findViewById(R.id.dailyrecycler);

        expenseModelArrayList=new ArrayList<>();
        expenseModelArrayList=db.getAllExpenses();
        expenseModel=new ExpenseModel();




       // Collections.sort(expenseModelArrayList);
       /* Collection<ExpenseModel> expenses = Collections2.filter(expenseModel, expenseModel -> expenseModel.;

        Collection<String> filtered = Collections2.filter(list,
                Predicates.containsPattern("How"));*/

        //Collections.reverse(expenseModelArrayList);

       /* if(unSorted)Collections.sort(expenseModelArrayList);
        else Collections.reverse(expenseModelArrayList);*/

      /*  sortAscending=!sortAscending;
        unSorted=false;*/


      //  sortStringList();

        recyclerAdapter=new RecyclerAdapter(expenseModelArrayList,getContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
        return view;

        }



    /*    class StringDateComparater implements Comparator<String>{





            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            @Override
            public int compare(String lhs, String rhs) {
                try {
                    return dateFormat.parse(lhs).compareTo(dateFormat.parse(rhs));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }


    private void sortStringList() {
       *//* Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);*//*

       expenseModelArrayList=new ArrayList<>();
       expenseModelArrayList=db.getAllExpenses();
       Collections.sort(expenseModelArrayList, new Comparator<String>() {
           @Override
           public int compare(String lhs, String rhs) {

               SimpleDateFormat format = new SimpleDateFormat(
                       "dd-MM-yyyy");
               int compareResult = 0;
               try {
                   Date arg0Date = format.parse(lhs);
                   Date arg1Date = format.parse(rhs);
                   compareResult = arg0Date.compareTo(arg1Date);
               } catch (ParseException e) {
                   e.printStackTrace();
                   compareResult
               }
               return compareResult;
           }
       });

        ArrayList<ExpenseModel>expenseModels=new ArrayList<>();
        Date currentDate=new Date();
        expenseModels=db.getAllExpenses();


        for(ExpenseModel expenseModel:expenseModelArrayList){

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date=null;
            try {
                date = formatter.parse(expenseModel.getExpensedate());
            } catch (ParseException e) {

            }
            if(currentDate.before(date)) {
                expenseModels.add(expenseModel);
            }

        }



    }*/


}
