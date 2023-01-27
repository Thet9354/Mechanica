package com.example.mechanica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.mechanica.Fragments.AddProgress;
import com.example.mechanica.Fragments.Home;
import com.example.mechanica.Fragments.ViewProgress;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomePage_Activity extends AppCompatActivity {

    private RelativeLayout fragment_container;
    private ChipNavigationBar bottom_nav_bar;

    private Intent intent;

    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);

        intent = getIntent();

        initWidget();
    }

    private void initWidget() {
        fragment_container = findViewById(R.id.fragment_container);
        bottom_nav_bar = findViewById(R.id.bottom_nav_bar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
        bottom_nav_bar.setItemSelected(R.id.nav_home, true);

        getIntentData();

        bottomMenu();
    }

    private void getIntentData() {

        mName = intent.getStringExtra("Name");
    }

    private void bottomMenu() {
        bottom_nav_bar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch(i)
                {
                    case R.id.nav_home:
                        fragment = new Home();
                        break;

                    case R.id.nav_addProgress:
                        fragment = new AddProgress();
                        break;

                    case R.id.nav_viewProgress:
                        fragment = new ViewProgress();
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putString("Name", mName);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }
}