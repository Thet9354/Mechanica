package com.example.mechanica.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mechanica.R;

public class Language_Activity extends AppCompatActivity {

    private ImageView btn_recommendations;

    private TextView txtView_Done, txtView_recommendation, txtView_recommendationText;

    private RelativeLayout rel_recommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        initView();

        pageDirectories();
    }

    private void pageDirectories() {

        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        txtView_recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LanguageChoice_Activity.class));
            }
        });

        txtView_recommendationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LanguageChoice_Activity.class));
            }
        });

        rel_recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LanguageChoice_Activity.class));
            }
        });
    }

    private void initView() {
        btn_recommendations = findViewById(R.id.btn_recommendations);
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_recommendation = findViewById(R.id.txtView_recommendation);
        txtView_recommendationText = findViewById(R.id.txtView_recommendationText);
        rel_recommendation = findViewById(R.id.rel_recommendation);
    }
}