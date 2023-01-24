package com.example.mechanica.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mechanica.Adapter.TextSizeAdapter;
import com.example.mechanica.CustomSpinner;
import com.example.mechanica.Model.TextSizeData;
import com.example.mechanica.R;

public class TextSize_Activity extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener{

    private TextView txtView_Done;

    private androidx.appcompat.widget.SwitchCompat defaultTextSize_switch, customizeTextSize_switch;

    private com.example.mechanica.CustomSpinner textSize_spinner;

    private TextSizeAdapter textSizeAdapter;

    private LinearLayout linearLayout_textSize;

    private String mTextSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_size);

        initView();

        spinnerSetUp();

        pageDirectories();
    }

    private void pageDirectories() {

        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        /* onCheckChangedListener for switch */
        defaultTextSize_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                customizeTextSize_switch.setChecked(!b);
            }
        });

        customizeTextSize_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    defaultTextSize_switch.setChecked(false);

                    //-->Spinner becomes visible
                    linearLayout_textSize.setVisibility(View.VISIBLE);
                }
                else
                {
                    defaultTextSize_switch.setChecked(true);

                    //-->Spinner becomes gone
                    linearLayout_textSize.setVisibility(View.GONE);
                }
            }
        });
    }

    private void spinnerSetUp() {
        textSizeAdapter = new TextSizeAdapter(getApplicationContext(), TextSizeData.getTextSizeList());
        textSize_spinner.setAdapter(textSizeAdapter);
        textSize_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                mTextSize = String.valueOf(parent.getItemAtPosition(i));
                if (mTextSize == "14")
                    Toast.makeText(TextSize_Activity.this, "Your text size for this app has been changed to 14", Toast.LENGTH_SHORT).show();
                else if (mTextSize == "16")
                    Toast.makeText(TextSize_Activity.this, "Your text size for this app has been changed to 16", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mTextSize = textSize_spinner.getSelectedItem().toString();
    }

    private void initView() {
        txtView_Done = findViewById(R.id.txtView_Done);
        defaultTextSize_switch = findViewById(R.id.defaultTextSize_switch);
        customizeTextSize_switch = findViewById(R.id.customizeTextSize_switch);
        linearLayout_textSize = findViewById(R.id.linearLayout_textSize);
        textSize_spinner = findViewById(R.id.textSize_spinner);
    }

    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        textSize_spinner.setBackground(getResources().getDrawable(R.drawable.bg_spinner_vocation_up));
        textSize_spinner.setBackground(getResources().getDrawable(R.drawable.bg_spinner_vocation_up));
    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        textSize_spinner.setBackground(getResources().getDrawable(R.drawable.bg_spinner_vocation_up));
        textSize_spinner.setBackground(getResources().getDrawable(R.drawable.bg_spinner_vocation_up));
    }
}