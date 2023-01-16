package com.example.mechanica.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechanica.Model.userSettingModel;
import com.example.mechanica.R;
import com.onesignal.OneSignal;

public class SettingsPage_Activity extends AppCompatActivity {

    private ImageView btn_logOut;

    private RelativeLayout rel_security, rel_textSize, rel_language, rel_feedback, rel_aboutUs, rel_additionalResources, rel_logOut;

    androidx.appcompat.widget.AppCompatButton btn_editProfile;

    private androidx.appcompat.widget.SwitchCompat nightMode_switch, notifications_Switch, privateAcc_switch;



    // OneSignal Cloud Messaging
    private static final String ONESIGNAL_APP_ID = "842b345f-b122-4ae4-9ec2-020e803cdcad";


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String selected;

    private userSettingModel settingModel;

    private String CHECKEDITEM = "checked_item";

    private String personName;
    private String personEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        initWidget();

        pageDirectories();

    }

    private void pageDirectories() {

        /** OnClickListener for Edit Profile**/
        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add the intent leading to the Edit Profile page
            }
        });

        rel_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add the intent leading to the security and privacy page
                startActivity(new Intent(SettingsPage_Activity.this, SecurityNPrivacy_Activity.class));
            }
        });

        rel_textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add the intent leading to the Text Size page
                startActivity(new Intent(getApplicationContext(), TextSize_Activity.class));
            }
        });

        rel_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add the intent leading to the language page
                startActivity(new Intent(getApplicationContext(), Language_Activity.class));
            }
        });

        rel_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add the intent leading to the feedback page
                startActivity(new Intent(getApplicationContext(), Feedback_Activity.class));
            }
        });

        rel_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add the intent leading to the About Us page
                startActivity(new Intent(getApplicationContext(), AboutUs_Activity.class));
            }
        });

        rel_additionalResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdditionalResources_Activity.class));
            }
        });

        rel_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add the intent leading to the Log Out page
            }
        });


    }

    private void oneSignalSetup() {
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }

    private void updateView() {
//        final Drawable purple_background = ContextCompat.getDrawable(this, R.drawable.purple_background);
//        final Drawable white_standard_background = ContextCompat.getDrawable(this, R.drawable.white_standard_background);
        final Drawable round_back_white10_20 = ContextCompat.getDrawable(SettingsPage_Activity.this, R.drawable.round_back_white10_20);


//        if (settingModel.getCustomTheme().equals(userSettingModel.DARK_THEME))
//        {
//            parentView.setBackground(purple_background);
//            darkModeChanges();
//            // Add if needed
//        }
//        else
//        {
//            parentView.setBackground(white_standard_background);
//            lightModeChanges();
//        }
    }


//    private void showDialog()
//    {
//        String[] themes = theme
//    }
//
//    private int getCheckedItem()
//    {
//        return sharedPreferences.getInt(CHECKEDITEM, 0);
//    }
//
//    private void setCheckedItem(int i)
//    {
//        editor.putInt(checkedItem, i);
//        editor.apply();
//
//    }

    private void googleSignOut() {

    }

    private void initWidget() {

        //RelativeLayout
        rel_security = findViewById(R.id.rel_security);
        rel_textSize = findViewById(R.id.rel_textSize);
        rel_language = findViewById(R.id.rel_language);
        rel_feedback = findViewById(R.id.rel_feedback);
        rel_aboutUs = findViewById(R.id.rel_aboutUs);
        rel_additionalResources = findViewById(R.id.rel_additionalResources);
        rel_logOut = findViewById(R.id.rel_logOut);

        //Button
        btn_editProfile = findViewById(R.id.btn_editProfile);

        //Switch
        nightMode_switch = findViewById(R.id.nightMode_switch);
        notifications_Switch = findViewById(R.id.notifications_Switch);
        privateAcc_switch = findViewById(R.id.privateAcc_switch);


    }
}