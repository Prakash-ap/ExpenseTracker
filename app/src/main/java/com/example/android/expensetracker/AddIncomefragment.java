package com.example.android.expensetracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
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
import Model.ExpenseModel;
import Model.In_Acc_Model;
import Model.In_Catg_model;
import Model.In_Chart_Catg;


public class AddIncomefragment extends Fragment implements AdapterView.OnItemSelectedListener,View.OnClickListener {

    private EditText amount,content;
    private TextView date;
     Calendar myCalendar;
     private Spinner account,category;
     List<String>accountlist;
     List<String>categorylist;
     ArrayList<In_Catg_model>in_catg_modelArrayList;
     In_Catg_model in_catg_model;
     In_Catg_model in_catg_model1;
     In_Catg_model in_catg_model2;
     In_Catg_model in_catg_model3;
     In_Catg_model in_catg_model4;
     In_Catg_model in_catg_model5;
     In_Catg_model in_catg_model6;
     In_Catg_model in_catg_model7;
     private Context context;
     String odate,oamount,ocontent,oaccount,ocategory;
     Button save;
     CoordinatorLayout coordinatorLayout;
    Snackbar snackbar;
    DatabaseHandler db;
    ExpenseModel expenseModel;
    List<ExpenseModel>expenseModelList;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String MyPreferences="IncomePrefs";
    String type;
    ArrayAdapter<String> accadapter;
    ArrayAdapter<String> catadapter;
    int week,year;
    String cattype,acctype;
    String catamount;
    String month;
    FloatingActionButton incomefab;
    ArrayList<In_Acc_Model>in_acc_modelArrayList;
    In_Acc_Model in_acc_model;
    In_Acc_Model in_acc_model1;
    In_Acc_Model in_acc_model2;
    In_Acc_Model in_acc_model3;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    In_Chart_Catg in_chart_catg;
    In_Chart_Catg in_chart_catg1;
    In_Chart_Catg in_chart_catg2;
    In_Chart_Catg in_chart_catg3;
    In_Chart_Catg in_chart_catg4;
    ArrayList<In_Chart_Catg>in_chart_catgArrayList;
  //  ArrayList<ExpenseModel> result;
    long charincome=0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_add_income, container, false);
        date=view.findViewById(R.id.incomedate);
        account=view.findViewById(R.id.incomeaccount);
        category=view.findViewById(R.id.incomecategory);
        amount=view.findViewById(R.id.incomeamount);
        content=view.findViewById(R.id.incomedescription);
        save=view.findViewById(R.id.saveincomebutton);

        //result=new ArrayList<ExpenseModel>();

        incomefab=view.findViewById(R.id.incomefab);
        fab1 = (FloatingActionButton)view.findViewById(R.id.incomefab1);
        fab2 = (FloatingActionButton)view.findViewById(R.id.incomefab2);

        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);

        account.setOnItemSelectedListener(this);
        category.setOnItemSelectedListener(this);

        incomefab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        db=new DatabaseHandler(getContext());
       /* in_chart_catg=new In_Chart_Catg();
        in_chart_catgArrayList=new ArrayList<>();*/
       loadcat();


        loadaccData();

        accadapter=new ArrayAdapter<String >(getContext(),android.R.layout.simple_spinner_item,accountlist);
        accadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        account.setAdapter(accadapter);



        //categorylist=new ArrayList<String>();
        loadData();

        catadapter=new ArrayAdapter<String >(getContext(),android.R.layout.simple_spinner_item,categorylist);
        catadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        category.setAdapter(catadapter);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odate=date.getText().toString();
                oamount=amount.getText().toString();
                ocontent=content.getText().toString();
                type="income";

                if(odate.equals("")&&oamount.equals("")&&ocontent.equals("")){
                  // snackbar=Snackbar.make(coordinatorLayout,"Enter the Credentials",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                    Toast.makeText(getContext(), "Enter the Credentials", Toast.LENGTH_SHORT).show();
                }else {
                    expenseModelList=new ArrayList<>();
                    expenseModel=new ExpenseModel();
                    expenseModel.setExpensedate(odate);
                    expenseModel.setExpenseaccount(oaccount);
                    expenseModel.setExpensecategory(ocategory);
                    expenseModel.setExpenseAmount(oamount);
                    expenseModel.setExpensecontent(ocontent);
                    expenseModel.setExpenseType(type);
                    expenseModel.setExpenseWeek(String.valueOf(week));
                    expenseModel.setExpenseMonth(month);
                    expenseModel.setExpenseYear(String.valueOf(year));
                    expenseModelList.add(expenseModel);
                  //  Collections.sort(expenseModelList);
                    db.addExpense(expenseModel);
                  loadcat();

                    Log.d("Insert", "Inserting from Income: " +expenseModel);

                    //Toast.makeText(getContext(), "Entered Values: \n" +expenseModel, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                                                     getContext().startActivity(intent);
                }



            }
        });

         myCalendar = Calendar.getInstance();
       //  result=db.getAllExpenses();



      
        final DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), datepicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });




        return view;
    }

    private void loadcat() {
        categorylist=new ArrayList<String>();
        in_catg_model=new In_Catg_model();
        in_catg_modelArrayList=new ArrayList<>();
        in_catg_modelArrayList=db.getAllInType();

        if (in_catg_modelArrayList.size() == 0) {
            in_catg_model = new In_Catg_model();
            in_catg_model.setIn_cat_type("Allowance");
            in_catg_model.setIn_cat_amount(String.valueOf(0));
            db.addInCatg(in_catg_model);

            in_catg_model2 = new In_Catg_model();
            in_catg_model2.setIn_cat_type("Salary");
            in_catg_model2.setIn_cat_amount(String.valueOf(0));
            db.addInCatg(in_catg_model2);

            in_catg_model3 = new In_Catg_model();
            in_catg_model3.setIn_cat_type("Petty Cash");
            in_catg_model3.setIn_cat_amount(String.valueOf(0));
            db.addInCatg(in_catg_model3);

            in_catg_model4 = new In_Catg_model();
            in_catg_model4.setIn_cat_type("Bonus");
            in_catg_model4.setIn_cat_amount(String.valueOf(0));
            db.addInCatg(in_catg_model4);

            in_catg_model5 = new In_Catg_model();
            in_catg_model5.setIn_cat_type("Other");
            in_catg_model5.setIn_cat_amount(String.valueOf(0));
            db.addInCatg(in_catg_model5);

            in_catg_modelArrayList=db.getAllInType();
            for(int i=0;i<in_catg_modelArrayList.size();i++){
                in_catg_model=in_catg_modelArrayList.get(i);
                cattype=in_catg_model.getIn_cat_type();
                catamount=in_catg_model.getIn_cat_amount();

                categorylist.add(cattype);
            }


            catadapter=new ArrayAdapter<String >(getContext(),android.R.layout.simple_spinner_item,categorylist);
            catadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            category.setAdapter(catadapter);



        }
        else{

            in_catg_model=new In_Catg_model();
            in_catg_modelArrayList=new ArrayList<>();
            in_catg_modelArrayList=db.getAllInType();
            for(int i=0;i<in_catg_modelArrayList.size();i++){
                in_catg_model=in_catg_modelArrayList.get(i);

                cattype=in_catg_model.getIn_cat_type();
                catamount=in_catg_model.getIn_cat_amount();

                in_catg_model.setIn_cat_type(cattype);
                in_catg_model.setIn_cat_amount(catamount);

                db.addInCatg(in_catg_model);
                categorylist.add(cattype);

            }


            catadapter=new ArrayAdapter<String >(getContext(),android.R.layout.simple_spinner_item,categorylist);
            catadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            category.setAdapter(catadapter);

        }
    }


    // private void loadInChartDb() {




        /*in_chart_catgArrayList = new ArrayList<>();

        in_chart_catgArrayList=db.getAllInChartCat();
        if(in_chart_catgArrayList.size()==0) {
            in_chart_catg = new In_Chart_Catg();
            in_chart_catg.setIn_chartcat_type("Allowance");
            in_chart_catg.setOn_chart_amount("0");
            db.addChartCat(in_chart_catg);
            in_chart_catg1 = new In_Chart_Catg();
            in_chart_catg1.setIn_chartcat_type("Salary");
            in_chart_catg1.setOn_chart_amount("0");
            db.addChartCat(in_chart_catg1);

            in_chart_catg2 = new In_Chart_Catg();
            in_chart_catg2.setIn_chartcat_type("Petty Cash");
            in_chart_catg2.setOn_chart_amount("0");
            db.addChartCat(in_chart_catg2);

            in_chart_catg3 = new In_Chart_Catg();
            in_chart_catg3.setIn_chartcat_type("Bonus");
            in_chart_catg3.setOn_chart_amount("0");
            db.addChartCat(in_chart_catg3);


            in_chart_catg4 = new In_Chart_Catg();
            in_chart_catg4.setIn_chartcat_type("Other");
            in_chart_catg4.setOn_chart_amount("0");
            db.addChartCat(in_chart_catg4);*/

    /* {
            in_chart_catgArrayList=db.getAllInChartCat();

            for(int i=0;i<in_chart_catgArrayList.size();i++){
                in_chart_catg=in_chart_catgArrayList.get(i);
                if(in_chart_catg.getIn_chartcat_type().equals(ocategory)){
                    charincome+=Long.valueOf(oamount);
                    in_chart_catg.setIn_chartcat_type(ocategory);
                    in_chart_catg.setOn_chart_amount(String.valueOf(charincome));
                    db.updateCharIncome(in_chart_catg);
                }else{
                    in_chart_catg.setIn_chartcat_type(ocategory);
                    in_chart_catg.setOn_chart_amount(oamount);
                    db.addChartCat(in_chart_catg);
                }
            }


        }*//*else{

            for(int i=0;i<in_chart_catgArrayList.size();i++){
                in_chart_catg=in_chart_catgArrayList.get(i);

                if( in_chart_catg.getIn_chartcat_type().equals(ocategory)){
                    charincome=Long.valueOf(in_chart_catg.getOn_chart_amount());
                    charincome+=Long.valueOf(oamount);
                    in_chart_catg.setIn_chartcat_type(ocategory);
                    in_chart_catg.setOn_chart_amount(String.valueOf(charincome));
                    db.updateCharIncome(in_chart_catg);
                }else{
                    in_chart_catg.setIn_chartcat_type(ocategory);
                    in_chart_catg.setOn_chart_amount(oamount);
                    db.addChartCat(in_chart_catg);



                }*/
                /*in_chart_catg.setIn_chartcat_type(ocategory);
                in_chart_catg.setOn_chart_amount(oamount);
                db.addChartCat(in_chart_catg);
*/



  //  }


    private void loadaccData() {
        categorylist=new ArrayList<String>();

        accountlist = new ArrayList<>();
        in_acc_model = new In_Acc_Model();
        in_acc_modelArrayList = new ArrayList<>();
        in_acc_modelArrayList = db.getAllInAccType();


            for (int i = 0; i < in_acc_modelArrayList.size(); i++) {
                in_acc_model = in_acc_modelArrayList.get(i);
                acctype = in_acc_model.getIn_acc_type();
                accountlist.add(acctype);


            }
            accadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, accountlist);
            accadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            account.setAdapter(accadapter);


        }


    private void loadData() {
        categorylist=new ArrayList<String>();
        in_catg_model=new In_Catg_model();
        in_catg_modelArrayList=new ArrayList<>();
        in_catg_modelArrayList=db.getAllInType();

            for(int i=0;i<in_catg_modelArrayList.size();i++){
                in_catg_model=in_catg_modelArrayList.get(i);
                cattype=in_catg_model.getIn_cat_type();
                    categorylist.add(cattype);
                    }
            catadapter=new ArrayAdapter<String >(getContext(),android.R.layout.simple_spinner_item,categorylist);
            catadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            category.setAdapter(catadapter);


        }







    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        date.setText(sdf.format(myCalendar.getTime()));
        week= myCalendar.get(Calendar.WEEK_OF_YEAR);
        year= myCalendar.get(Calendar.YEAR);
        month=new SimpleDateFormat("MMMM").format(myCalendar.getTime());
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (parent.getId()){
            case R.id.incomeaccount:

                oaccount=account.getSelectedItem().toString();


                break;
            case R.id.incomecategory:

                ocategory=category.getSelectedItem().toString();

                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getContext(), "select any cat/acc", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.incomefab:

                animateFAB();
                break;
            case R.id.incomefab1:
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
                            in_catg_model = new In_Catg_model();
                            in_catg_model.setIn_cat_type(catval);
                            in_catg_modelArrayList=new ArrayList<>();
                            in_catg_modelArrayList=db.getAllInType();
                          //  in_catg_model.setIn_cat_type();

                                db.addInCatg(in_catg_model);
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                                loadData();
                        }


                    }
                });
               dialog.show();




                Toast.makeText(getContext(), "Income fab", Toast.LENGTH_SHORT).show();

                break;
            case R.id.incomefab2:

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
                            in_acc_model = new In_Acc_Model();
                            in_acc_model.setIn_acc_type(accval);
                            if(accval.matches(in_acc_model.getIn_acc_type())){
                                Toast.makeText(getActivity(), "Can't be Added", Toast.LENGTH_SHORT).show();
                            }else {
                                db.addInAcc(in_acc_model);

                                dialog1.dismiss();
                                Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                                loadaccData();
                            }


                        }


                    }
                });
                dialog1.show();

                Toast.makeText(getContext(), "Account fab", Toast.LENGTH_SHORT).show();

                break;
        }

    }


    public void animateFAB(){

        if(isFabOpen){

            incomefab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {

            incomefab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
