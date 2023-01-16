package com.example.mechanica.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechanica.R;

public class SecurityNPrivacy_Activity extends AppCompatActivity {

    private TextView txtView_Done;

    private RelativeLayout rel_ad_preferences, rel_dataSharing, rel_locationInfo, rel_privacy, rel_privacyPolicy, rel_contactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_nprivacy);

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


        /* onClickListener for Ads preferences section **/
        rel_ad_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                startActivity(new Intent(getApplicationContext(), Ad_Preference_Activity.class));
            }
        });

        /*  onClickListener for Data sharing with business partner section **/
        rel_dataSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                startActivity(new Intent(getApplicationContext(), DataSharing_Activity.class));
            }
        });

        /* onClickListener for Location Information section */
        rel_locationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                startActivity(new Intent(getApplicationContext(), LocationInfo_Activity.class));
            }
        });

        /* onClickListener for Privacy center section */
        rel_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                Toast.makeText(SecurityNPrivacy_Activity.this, "This section is supposed to lead you to an external website which i have not developed, thanks for your understanding.", Toast.LENGTH_SHORT).show();
            }
        });


        /* onClickListener for Privacy policy section */
        rel_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                Toast.makeText(SecurityNPrivacy_Activity.this, "This section is supposed to lead you to an external website which i have not developed, thanks for your understanding.", Toast.LENGTH_SHORT).show();
            }
        });

        /* onClickListener for Contact us section */
        rel_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lead to another page
                Toast.makeText(SecurityNPrivacy_Activity.this, "This section is supposed to lead you to an external website which i have not developed, thanks for your understanding.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {

        //---> TextView
        txtView_Done = findViewById(R.id.txtView_Done);

        //---> RelativeLayout
        rel_ad_preferences = findViewById(R.id.rel_ad_preferences);
        rel_dataSharing = findViewById(R.id.rel_dataSharing);
        rel_locationInfo = findViewById(R.id.rel_locationInfo);
        rel_privacy = findViewById(R.id.rel_privacy);
        rel_privacyPolicy = findViewById(R.id.rel_privacyPolicy);
        rel_contactus = findViewById(R.id.rel_contactus);
    }
}