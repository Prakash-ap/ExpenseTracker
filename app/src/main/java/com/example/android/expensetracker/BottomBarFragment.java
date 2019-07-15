package com.example.android.expensetracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class BottomBarFragment extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    public static final String TRANS_TITILE="TRANSACTION";
    public static final String STATUS_TITILE="STATUS";
    public static final String SETTINGS_TITILE="SETTINGS";


    private BottomNavigationView bottomNavigationView;
    private List<BottomBarFragment> fragments=new ArrayList<>(3);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottombarfragment);

        loadFragment(new TransFragment());

        bottomNavigationView=findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

      //  switchFragment(0,TRANS_TITILE);
    }

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

  /*  private void switchFragment(int i, String transTitile) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameholder,fragments.get(i),transTitile).commit();
    }*/


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment=null;
        switch (menuItem.getItemId()){


            case R.id.transid:
                fragment=new TransFragment();
                break;

                case R.id.stats:
               fragment=new StatusFragment();
               break;

            case R.id.settings:
              fragment=new SettingsFragment();
              break;
        }
        return loadFragment(fragment);
    }
}
