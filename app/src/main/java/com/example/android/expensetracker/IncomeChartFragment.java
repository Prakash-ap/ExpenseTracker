package com.example.android.expensetracker;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import Adapter.RecyclerIncomeChartAdapter;
import Database.DatabaseHandler;
import Model.ExpenseModel;
import Model.In_Acc_Model;
import Model.In_Chart_Catg;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class IncomeChartFragment extends Fragment {
    PieChartView pieChartView;
    DatabaseHandler db;
    ExpenseModel expenseModel;
    ArrayList<ExpenseModel>expenseModelList;
    ArrayList<ExpenseModel>expenseModelListvalue;
    String catg,month;
    int year;
    ArrayList<In_Acc_Model>in_acc_modelArrayList;
    ArrayList<In_Chart_Catg>in_chart_catgArrayList;
    In_Chart_Catg in_chart_catg;
    In_Acc_Model in_acc_model;
    String in;
    long tempincome=0;
    RecyclerView recyclerView;
    RecyclerIncomeChartAdapter recyclerIncomeChartAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_income_chart, container, false);
       // String[]incomecat=db.getAllInType();
        pieChartView=view.findViewById(R.id.chart);

        db=new DatabaseHandler(getActivity());



       // expenseModel=expenseModelList.get(1);
        Calendar calendar=Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month=new SimpleDateFormat("MMMM").format(calendar.getTime());
        in_chart_catgArrayList=new ArrayList<>();
        in_chart_catgArrayList=db.getAllInChartCat();
        in_chart_catg=new In_Chart_Catg();

        recyclerView=view.findViewById(R.id.incomechartrelativelayout);




/*
for(int i=0;i<in_chart_catgArrayList.size();i++){
    in_chart_catg=in_chart_catgArrayList.get(i);
    in_chart_catg.setIn_chartcat_type(in_chart_catg.getIn_chartcat_type());
    in_chart_catg.setOn_chart_amount(in_chart_catg.getOn_chart_amount());


}
*/

        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(15, Color.BLUE).setLabel("Salary"));
        pieData.add(new SliceValue(25, Color.GRAY).setLabel("Allowance"));
        pieData.add(new SliceValue(10, Color.RED).setLabel("Bonus"));
        pieData.add(new SliceValue(60, Color.MAGENTA).setLabel("Other"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        //pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);


        in_chart_catgArrayList=new ArrayList<>();
        in_chart_catgArrayList=db.getAllInChartCat();


        recyclerIncomeChartAdapter=new RecyclerIncomeChartAdapter(getContext(),in_chart_catgArrayList);
        final RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerIncomeChartAdapter);






       /* for (int i=0;i<in_chart_catgArrayList.size();i++){

            in_chart_catg=in_chart_catgArrayList.get(i);

            in_chart_catg.setIn_chartcat_type(in_chart_catg.getIn_chartcat_type());
            in_chart_catg.setOn_chart_amount(in_chart_catg.getOn_chart_amount());
            in_chart_catgArrayList.add(in_chart_catg);


*/

//            in_acc_model=in_acc_modelArrayList.get(i);
            /*if(year==year){
                if (month==month){
                    if(expenseModel.getExpenseType().equals("income")&&expenseModel.getExpensecategory().equals("Salary")){

                        in = expenseModel.getExpenseAmount();
                        if (in.equals("")) {
                            tempincome=0;

                        } else {*/


                         //   tempincome += Long.parseLong(in);




        return view;


    }
                }
                /*if()
        String cat=expenseModel.getExpensecategory()

        expenseModelListvalue.add(expenseModel.getExpensecategory())*/
