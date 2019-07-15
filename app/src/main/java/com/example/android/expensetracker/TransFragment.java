package com.example.android.expensetracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Adapter.DialogClass;
import Database.DatabaseHandler;
import Model.ExpenseModel;


public class TransFragment extends Fragment  {

    DatabaseHandler db;
    private ArrayList<ExpenseModel> expenseModelArrayList;
    ExpenseModel expenseModel;
    long tempincome=0;
    long tempexpense=0;
    long totalexpense=0;
    TextView income,expense,total;
    String in,ex,to;
    ImageView filter;
    TextView maindate;
    Calendar myCalendar;
    String monthYearStr;
    SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");






    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_trans, container, false);
        maindate=view.findViewById(R.id.datepicker);
        myCalendar = Calendar.getInstance();
        String month=new SimpleDateFormat("MMMM yyyy").format(myCalendar.getTime());

        maindate.setText(month);
        ViewPager viewPager = view.findViewById(R.id.transviewpager);
       setupViewPager(viewPager);
        TabLayout tabLayout = view.findViewById(R.id.transtablayout);
        tabLayout.setupWithViewPager(viewPager);
        db=new DatabaseHandler(getContext());
        expenseModel=new ExpenseModel();
        income=view.findViewById(R.id.incomevalue);
        expense=view.findViewById(R.id.expensesvalue);
        total=view.findViewById(R.id.totalvalue);
        filter=view.findViewById(R.id.filter);




        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.filtermenu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.weeklyid:

                                Toast.makeText(getContext(), "Week Frament Comes Here", Toast.LENGTH_SHORT).show();
                                break;

                                case R.id.monthly:
                                Toast.makeText(getContext(), "Month Frament Comes Here", Toast.LENGTH_SHORT).show();
                                break;



                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

        final DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

               // updateLabel();
            }

        };

        maindate.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              DialogClass pickerDialog = new DialogClass();
                                              pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                                                  @Override
                                                  public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                                                      monthYearStr = year + "-" + (month + 1) + "-" + i2;
                                                      maindate.setText(formatMonthYear(monthYearStr));


                                                  }
                                              });
                                              pickerDialog.show(getChildFragmentManager(), "MonthYearPickerDialog");
                                          }


              /*  new DatePickerDialog(getContext(), datepicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
*/
                                      });





        expenseModelArrayList=new ArrayList<>();
      //  expenseModel=db.getExpenseAmount(0);
        expenseModelArrayList=db.getAllExpenses();
        expenseModel=new ExpenseModel();

        for(int i=0;i< expenseModelArrayList.size();i++) {
            expenseModel = expenseModelArrayList.get(i);
            if (expenseModel.getExpenseType().equals("income")) {
                in=expenseModel.getExpenseAmount();
                if(in.equals("")){
                   // Toast.makeText(getContext(), "Enter the amount", Toast.LENGTH_SHORT).show();
                }else {

                    tempincome += Long.parseLong(in);
                }

            } else if (expenseModel.getExpenseType().equals("expenses")) {
                ex=expenseModel.getExpenseAmount();
                if(ex.equals("")){
                 //   Toast.makeText(getContext(), "Enter the amount", Toast.LENGTH_SHORT).show();

                }else {
                    tempexpense = tempexpense + Long.parseLong(ex);
                }

            }else{
                Toast.makeText(getContext(), "No Value", Toast.LENGTH_SHORT).show();

        }


        }

        totalexpense=tempincome-tempexpense;
        income.setText(String.valueOf(tempincome));
        expense.setText(String.valueOf(tempexpense));
        total.setText(String.valueOf(totalexpense));



        return view;

    }

    String formatMonthYear(String str) {
        Date date = null;
        try {
            date = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

   /* private void updateLabel() {

        String myFormat = "MMMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        datePicker.setText(sdf.format(myCalendar.getTime()));

      *//*  week= myCalendar.get(Calendar.WEEK_OF_YEAR);
        year= myCalendar.get(Calendar.YEAR);
        month=new SimpleDateFormat("MMMM").format(myCalendar.getTime());*//*
    }
*/
    private void setupViewPager(ViewPager viewPager) {

        Adapter adapter = new Adapter(getChildFragmentManager());

        if(maindate.equals("")) {
            Toast.makeText(getContext(), "Select the Month First", Toast.LENGTH_SHORT).show();

        }else{
            adapter.addFragment(new DailyFragment(), "Daily");
            adapter.addFragment(new WeeklyFragment(), "Weekly");
            adapter.addFragment(new MonthlyFragment(), "Monthly");
            viewPager.setAdapter(adapter);
        }


    }

    static class Adapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmenttitle = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmenttitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmenttitle.get(position);
        }
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.weeklyid:

                Toast.makeText(getContext(), "Week Frament Comes Here", Toast.LENGTH_SHORT).show();

                *//*FragmentManager fragmentManager = getChildFragmentManager();;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                fragmentTransaction.commit();*//*
                return true;
            case R.id.monthly:
                Toast.makeText(getContext(), "Month Frament Comes Here", Toast.LENGTH_SHORT).show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

}
