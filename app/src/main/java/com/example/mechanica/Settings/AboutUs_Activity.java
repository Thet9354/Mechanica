package com.example.mechanica.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mechanica.R;

public class AboutUs_Activity extends AppCompatActivity {

    private Button btn_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        initView();

        pageDirectories();
    }

    private void pageDirectories() {
        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });
    }

    private void initView() {
        btn_getStarted = findViewById(R.id.btn_getStarted);
    }
}