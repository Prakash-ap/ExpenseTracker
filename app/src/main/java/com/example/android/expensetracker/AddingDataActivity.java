package com.example.android.expensetracker;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DatabaseHandler;
import Model.In_Acc_Model;
import Model.In_Catg_model;

public class AddingDataActivity extends AppCompatActivity {
    ImageView addcatg;
    DatabaseHandler db;
    In_Catg_model in_catg_model;
    ArrayList<In_Catg_model>in_catg_modelArrayList;
    In_Acc_Model in_acc_model;
    ArrayList<In_Acc_Model>in_acc_modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_data);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("ExpenseTracker");

        LinearLayout accbottomsheet,catgbottomsheet;
        db=new DatabaseHandler(this);



        TabLayout tabLayout=findViewById(R.id.tablayout);
        ViewPager viewPager=findViewById(R.id.viewPager);



        addcatg=findViewById(R.id.addcatg);

        addcatg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getApplicationContext(),v);
                MenuInflater inflater=popupMenu.getMenuInflater();
                inflater.inflate(R.menu.addcatg,popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.catid:

                                final AlertDialog dialogBuilder = new AlertDialog.Builder(AddingDataActivity.this).create();
                                LayoutInflater inflater = AddingDataActivity.this.getLayoutInflater();
                                View dialogView = inflater.inflate(R.layout.bottomsheetcatg_layout, null);

                                final EditText editText = (EditText) dialogView.findViewById(R.id.edt_category);

                                Button button = (Button) dialogView.findViewById(R.id.btn_category);

                                button.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View view) {

                                        String catval=editText.getText().toString();
                                       if(catval.equals("")){
                                           Toast.makeText(AddingDataActivity.this, "Enter the category", Toast.LENGTH_SHORT).show();
                                           }else{
                                           in_catg_model=new In_Catg_model();


                                           in_catg_model.setIn_cat_type(catval);

                                           db.addInCatg(in_catg_model);
                                           Toast.makeText(AddingDataActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();

                                           dialogBuilder.dismiss();
                                           finish();

                                       }
                                         }
                                });

                                dialogBuilder.setView(dialogView);
                                dialogBuilder.show();




                               /* if (catg_bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                                    catg_bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                                   // btnBottomSheet.setText("Close sheet");
                                } else {
                                    catg_bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                  //  btnBottomSheet.setText("Expand sheet");
                                }*/
                                Toast.makeText(AddingDataActivity.this, "Add Category", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.accid:

                                final AlertDialog dialog=new AlertDialog.Builder(AddingDataActivity.this).create();
                                LayoutInflater layoutInflater=AddingDataActivity.this.getLayoutInflater();
                                View dialogAccView=layoutInflater.inflate(R.layout.bottomsheetaccount_layout,null,false);
                                final  EditText accedt=(EditText)dialogAccView.findViewById(R.id.edt_account);
                                final Button accbtn=(Button)dialogAccView.findViewById(R.id.btn_account);

                                accbtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String accval=accedt.getText().toString();
                                        if(accval.equals("")){
                                            Toast.makeText(AddingDataActivity.this, "Enter Acctype", Toast.LENGTH_SHORT).show();
                                        }else{

                                            in_acc_model=new In_Acc_Model();
                                            in_acc_model.setIn_acc_type(accval);
                                            db.addInAcc(in_acc_model);
                                            Toast.makeText(AddingDataActivity.this, "Added Account Successfully", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                            finish();

                                        }
                                    }
                                });
                                dialog.setView(dialogAccView);
                                dialog.show();

                                /*if (acc_bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                                    acc_bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                    // btnBottomSheet.setText("Close sheet");
                                } else {
                                    acc_bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                    //  btnBottomSheet.setText("Expand sheet");
                                }*/
                                Toast.makeText(AddingDataActivity.this, "Add Account", Toast.LENGTH_SHORT).show();
                                break;

                        }

                        return true;
                    }
                });
                popupMenu.show();

            }
        });

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new AddIncomefragment(),"AddIncome");
        viewPagerAdapter.addFragment(new AddExpenseFragment(),"AddExpense");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

/*
public void AddAccount(View view){
    Toast.makeText(this, "Pressed Add Account", Toast.LENGTH_SHORT).show();

}

public void AddCatg(View view){
    Toast.makeText(this, "Pressed Add Category", Toast.LENGTH_SHORT).show();


}
*/

}
