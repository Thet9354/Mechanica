package com.example.mechanica.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mechanica.R;

public class LanguageChoice_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done, txtView_languages, question;

    private androidx.appcompat.widget.SwitchCompat english_switch, chinese_Switch, malay_Switch, tamil_Switch;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);


        initView();

        pageDirectories();

    }

    private void pageDirectories() {
        /* onClickListener of back and done */
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        /* switches for each languages */
        english_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    chinese_Switch.setChecked(false);
                    malay_Switch.setChecked(false);
                    tamil_Switch.setChecked(false);
                    txtView_Done.setText("Done");
                    txtView_languages.setText("Languages");
                    question.setText("Which_language_do_you_speak");
                }
                else
                    onResume();
            }
        });

        chinese_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    english_switch.setChecked(false);
                    malay_Switch.setChecked(false);
                    tamil_Switch.setChecked(false);
                    txtView_Done.setText("完毕");
                    txtView_languages.setText("语言");
                    question.setText("你说哪种语言");
                }
                else
                {
                    onResume();
                }
            }
        });

        malay_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    english_switch.setChecked(false);
                    chinese_Switch.setChecked(false);
                    tamil_Switch.setChecked(false);
                    txtView_Done.setText("Selesai");
                    txtView_languages.setText("Bahasa");
                    question.setText("Bahasa manakah anda bercakap?");
                }
                else
                    onResume();
            }
        });

        tamil_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    english_switch.setChecked(false);
                    chinese_Switch.setChecked(false);
                    malay_Switch.setChecked(false);
                    txtView_Done.setText("முடிந்தது");
                    txtView_languages.setText("மொழிகள்");
                    question.setText("நீங்கள் எந்த மொழியில் பேசுகிறீர்கள்?");
                }
                else
                    onResume();
            }
        });

        if (!chinese_Switch.isChecked() | !malay_Switch.isChecked() | !tamil_Switch.isChecked())
            english_switch.setChecked(true);
    }

    private void initView() {
        txtView_Done = findViewById(R.id.txtView_Done);
        english_switch = findViewById(R.id.english_switch);
        chinese_Switch = findViewById(R.id.chinese_Switch);
        malay_Switch = findViewById(R.id.malay_Switch);
        tamil_Switch = findViewById(R.id.tamil_Switch);

        txtView_languages = findViewById(R.id.txtView_languages);
        question = findViewById(R.id.question);
    }
}