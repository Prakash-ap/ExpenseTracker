package com.example.android.expensetracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import Database.DatabaseHandler;
import Model.Ex_Acc_Model;
import Model.Ex_Catg_model;
import Model.ExpenseModel;
import Model.In_Acc_Model;
import Model.In_Catg_model;


public class AddExpenseFragment extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener {
    private EditText examount,excontent;
    private TextView exdate;
    Calendar exmyCalendar;
    private Spinner exaccount,excategory;
    List<String> exaccountlist;
    List<String>excategorylist;
    private Context context;
    Button save;
    String edate,eamount,econtent,eaccount,ecategory;
    DatabaseHandler db;
    ExpenseModel expenseModel;
    List<ExpenseModel>expenseModelList;
    String type;
    ArrayAdapter<String> exaccadapter;
    ArrayAdapter<String> excatadapter;
    int week,year;
    String month;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private Boolean isFabOpen = false;
    Ex_Acc_Model ex_acc_model;
    ArrayList<Ex_Acc_Model>ex_acc_modelArrayList;
    Ex_Catg_model ex_catg_model;
    ArrayList<Ex_Catg_model>ex_catg_modelArrayList;
    String exacctype,excattype;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_expense, container, false);

            exdate=view.findViewById(R.id.expensedate);
            exaccount=view.findViewById(R.id.expenseaccount);
            excategory=view.findViewById(R.id.expensecategory);
            examount=view.findViewById(R.id.expenseamount);
            excontent=view.findViewById(R.id.expensedescription);

        fab=view.findViewById(R.id.expensefab);
        fab1 = (FloatingActionButton)view.findViewById(R.id.expensefab1);
        fab2 = (FloatingActionButton)view.findViewById(R.id.expensefab2);

        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        ex_acc_modelArrayList=new ArrayList<>();
        ex_catg_modelArrayList=new ArrayList<>();



            save=view.findViewById(R.id.saveexpensebutton);
           db=new DatabaseHandler(getContext());
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    edate=exdate.getText().toString();
                    eamount=examount.getText().toString();
                    econtent=excontent.getText().toString();
                    type="expenses";

                    if(edate.equals("")&&eamount.equals("")&&econtent.equals("")&&(edate.equals("")||eamount.equals("")||econtent.equals(""))){
                        // snackbar=Snackbar.make(coordinatorLayout,"Enter the Credentials",Snackbar.LENGTH_LONG).setAction("Action",null).show();

                        Toast.makeText(getContext(), "Enter the Credentials", Toast.LENGTH_SHORT).show();
                    }else {
                        expenseModelList=new ArrayList<>();
                        expenseModel=new ExpenseModel();
                        expenseModel.setExpensedate(edate);
                        expenseModel.setExpenseaccount(eaccount);
                        expenseModel.setExpensecategory(ecategory);
                        expenseModel.setExpenseAmount(eamount);
                        expenseModel.setExpensecontent(econtent);
                        expenseModel.setExpenseType(type);
                        expenseModel.setExpenseWeek(String.valueOf(week));
                        expenseModel.setExpenseMonth(month);
                        expenseModel.setExpenseYear(String.valueOf(year));
                        expenseModelList.add(expenseModel);
                       // Collections.sort(expenseModelList);
                        db.addExpense(expenseModel);


                        Log.d("Insert", "Inserting from Expense : " +expenseModel);

                      //  Toast.makeText(getContext(), "Entered Values: "+expenseModel, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        getContext().startActivity(intent);
                    }

                }
            });

            exmyCalendar = Calendar.getInstance();



        exaccountlist=new ArrayList<String >();
        exaccountlist.add("Select your Account");
        exaccountlist.add("Account");
        exaccountlist.add("Cash");
        exaccountlist.add("Card");
        loadecAccounts();

       exaccadapter=new ArrayAdapter<String >(getContext(),android.R.layout.simple_spinner_item,exaccountlist);
        exaccadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            exaccount.setAdapter(exaccadapter);


        loadexCatType();

          excatadapter=new ArrayAdapter<String >(getContext(),android.R.layout.simple_spinner_item,excategorylist);
        excatadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            excategory.setAdapter(excatadapter);


            final DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    exmyCalendar.set(Calendar.YEAR, year);
                    exmyCalendar.set(Calendar.MONTH, monthOfYear);
                    exmyCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }

            };

            exdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(getContext(), datepicker, exmyCalendar
                            .get(Calendar.YEAR), exmyCalendar.get(Calendar.MONTH),
                            exmyCalendar.get(Calendar.DAY_OF_MONTH)).show();


                }
            });

            exaccount.setOnItemSelectedListener(this);
            excategory.setOnItemSelectedListener(this);



            return view;
        }

    private void loadexCatType() {

        excategorylist=new ArrayList<String>();
        excategorylist.add("Select Category");
        excategorylist.add("Food");
        excategorylist.add("Social Life");
        excategorylist.add("Gift");
        excategorylist.add("Self-development");
        excategorylist.add("Apparel");
        excategorylist.add("Culture");
        excategorylist.add("House-held");
        excategorylist.add("Health");
        excategorylist.add("Beauty");
        excategorylist.add("Education");
        excategorylist.add("Other");

        ex_catg_model=new Ex_Catg_model();
        ex_catg_modelArrayList=db.getAllExCatType();
        for (int i=0;i<ex_catg_modelArrayList.size();i++){
            ex_catg_model=new Ex_Catg_model();
            excattype=ex_catg_model.getEx_cat_type();
            excategorylist.add(excattype);
        }
    }

    private void loadecAccounts() {
        exaccountlist=new ArrayList<String >();
        exaccountlist.add("Select your Account");
        exaccountlist.add("Account");
        exaccountlist.add("Cash");
        exaccountlist.add("Card");
        ex_acc_model=new Ex_Acc_Model();
        ex_acc_modelArrayList=db.getAllExAccType();
        for (int i=0;i<ex_acc_modelArrayList.size();i++){
            ex_acc_model=new Ex_Acc_Model();
            exacctype=ex_acc_model.getEx_acc_type();
            exaccountlist.add(exacctype);

        }
    }

    private void updateLabel() {
            String myFormat = "MM/dd/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

            exdate.setText(sdf.format(exmyCalendar.getTime()));
            week= exmyCalendar.get(Calendar.WEEK_OF_YEAR);
            year= exmyCalendar.get(Calendar.YEAR);
            month=new SimpleDateFormat("MMMM").format(exmyCalendar.getTime());
        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()){
                case R.id.expenseaccount:
                    eaccount=exaccount.getSelectedItem().toString();
                    break;
                case R.id.expensecategory:
                    ecategory=excategory.getSelectedItem().toString();
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.expensefab:

                animateFAB();
                break;
            case R.id.expensefab1:
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.bottomsheetcatg_layout, null);
                final EditText catedt=(EditText)alertLayout.findViewById(R.id.edt_category);
                final Button catbtn=(Button)alertLayout.findViewById(R.id.btn_category);
                AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
                alert.setView(alertLayout);
                AlertDialog dialog=alert.create();


                catbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String catval=catedt.getText().toString();

                        if(catval.equals("")){
                            Toast.makeText(getContext(), "Enter the Catgory", Toast.LENGTH_SHORT).show();
                        }else {
                            ex_catg_model = new Ex_Catg_model();
                            ex_catg_model.setEx_cat_type(catval);
                            db.addExCatg(ex_catg_model);

                            dialog.dismiss();
                            Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();

                        }


                    }
                });
                dialog.show();
                //loadData();



                Toast.makeText(getContext(), "Income fab", Toast.LENGTH_SHORT).show();

                break;
            case R.id.expensefab2:

                LayoutInflater inflater1 = getLayoutInflater();
                View alertLayout1 = inflater1.inflate(R.layout.bottomsheetaccount_layout, null);
                final EditText accedt=(EditText)alertLayout1.findViewById(R.id.edt_account);
                final Button accbtn=(Button)alertLayout1.findViewById(R.id.btn_account);
                AlertDialog.Builder alert1=new AlertDialog.Builder(getContext());
                alert1.setView(alertLayout1);
                AlertDialog dialog1=alert1.create();


                accbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String accval=accedt.getText().toString();

                        if(accval.equals("")){
                            Toast.makeText(getContext(), "Enter the Catgory", Toast.LENGTH_SHORT).show();
                        }else {
                            ex_acc_model = new Ex_Acc_Model();
                            ex_acc_model.setEx_acc_type(accval);
                            db.addExAcc(ex_acc_model);

                            dialog1.dismiss();
                            Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();

                        }


                    }
                });
                dialog1.show();
                //loadData();

                Toast.makeText(getContext(), "Account fab", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }
}



