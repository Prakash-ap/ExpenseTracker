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

import java.util.ArrayList;
import java.util.List;

import Adapter.RecyclerExChartAdapter;
import Adapter.RecyclerIncomeChartAdapter;
import Database.DatabaseHandler;
import Model.Ex_Chart_Catg;
import Model.In_Catg_model;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class ExpenseChartFragment extends Fragment {
    PieChartView pieChartView;
    RecyclerView recyclerView;
    RecyclerExChartAdapter recyclerExChartAdapter;
    Ex_Chart_Catg ex_chart_catg;
    ArrayList<Ex_Chart_Catg>ex_chart_catgArrayList;
    DatabaseHandler db;
    In_Catg_model in_catg_model;
    ArrayList<In_Catg_model>in_catg_modelArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_expense_chart, container, false);
        pieChartView=view.findViewById(R.id.chart);
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(15, Color.BLUE).setLabel("Q1: $10"));
        pieData.add(new SliceValue(25, Color.GRAY).setLabel("Q2: $4"));
        pieData.add(new SliceValue(10, Color.RED).setLabel("Q3: $18"));
        pieData.add(new SliceValue(60, Color.MAGENTA).setLabel("Q4: $28"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
//        pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);

        recyclerView=view.findViewById(R.id.expensechartrelativelayout);
        db=new DatabaseHandler(getContext());
        in_catg_model=new In_Catg_model();
        in_catg_modelArrayList=new ArrayList<>();
        in_catg_modelArrayList=db.getAllInType();






  ex_chart_catgArrayList=db.getAllExChartCat();
      recyclerView=view.findViewById(R.id.expensechartrelativelayout);
//
        recyclerExChartAdapter=new RecyclerExChartAdapter(getContext(),ex_chart_catgArrayList);
        final RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerExChartAdapter);




        return view;
    }

}
