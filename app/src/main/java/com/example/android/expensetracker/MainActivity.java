package com.example.android.expensetracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DialogInterface.OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton floatingActionButton;
    BottomNavigationView bottomNavigationView;
  //  BottomSheetBehavior bottomSheetBehavior_addtoDb;
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout adddata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ExpenseTracker");*/

        adddata=findViewById(R.id.adddata_layout);


        floatingActionButton=findViewById(R.id.fab);


        loadFragment(new TransFragment());


        bottomNavigationView=findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // AddDataMethod();
                Intent intent=new Intent(MainActivity.this,AddingDataActivity.class);
                startActivity(intent);
            }
        });

       /* bottomSheetBehavior_addtoDb=BottomSheetBehavior.from(adddata);
        bottomSheetBehavior_addtoDb.setHideable(true);
        bottomSheetBehavior_addtoDb.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior_addtoDb.setSkipCollapsed(false);
        bottomSheetBehavior_addtoDb.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i) {

                    case BottomSheetBehavior.STATE_HIDDEN:

                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:

                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:

                        break;
                }

                }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }*/
      //  });

    }

 /*   private void AddDataMethod() {
        if (bottomSheetBehavior_addtoDb.getState() !=BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior_addtoDb.setState(BottomSheetBehavior.STATE_EXPANDED);
        }else{
            bottomSheetBehavior_addtoDb.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }*/

    private boolean loadFragment(Fragment fragment) {
        if(fragment !=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameholder,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment=null;
        switch (menuItem.getItemId()){
            case R.id.transid:
                fragment=new TransFragment();
                floatingActionButton.setVisibility(View.VISIBLE);

                break;

            case R.id.stats:
                fragment=new StatusFragment();
                floatingActionButton.setVisibility(View.GONE);

                break;

            case R.id.settings:
                fragment=new SettingsFragment();
                floatingActionButton.setVisibility(View.GONE);

                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }



}
