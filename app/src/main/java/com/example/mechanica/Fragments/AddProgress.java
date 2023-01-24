package com.example.mechanica.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mechanica.DatabaseHelper;
import com.example.mechanica.R;

public class AddProgress extends Fragment {

    private Context mContext;

    private DatabaseHelper databaseHelper;

    public static final int TIMER = 2000;

    private AutoCompleteTextView filled_exposed;
    private com.google.android.material.textfield.TextInputLayout editTxtInputLayout;
    private com.google.android.material.textfield.TextInputEditText editTxt_pullPercent;

    private RelativeLayout rel_btn;
    private TextView btn_saveProgress;
    private LottieAnimationView btn_animation;

    private String level, pullPercent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_progress, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        filled_exposed = v.findViewById(R.id.filled_exposed);
        editTxtInputLayout = v.findViewById(R.id.editTxtInputLayout);

        rel_btn = v.findViewById(R.id.rel_btn);
        btn_saveProgress = v.findViewById(R.id.btn_saveProgress);
        btn_animation = v.findViewById(R.id.btn_animation);
        editTxt_pullPercent = v.findViewById(R.id.editTxt_pullPercent);

        initUI();

        pageDirectories();
    }

    private void pageDirectories() {

        filled_exposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                level = filled_exposed.getText().toString();
            }
        });

        rel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_animation.setVisibility(View.VISIBLE);
                btn_animation.playAnimation();
                btn_saveProgress.setVisibility(View.GONE);

                validateLevel();

                validatePullPercent();

                validateInput();

                new Handler().postDelayed(this::resetButton, TIMER);
            }

            private void resetButton()
            {
                btn_animation.pauseAnimation();
                btn_animation.setVisibility(View.GONE);
                btn_saveProgress.setVisibility(View.VISIBLE);

            }
        });
    }

    private void validateInput() {

        if (!validateLevel() | !validatePullPercent())
        {
            return;
        }
        else
        {
            new AddData().execute(level, pullPercent);
        }
    }

    private class AddData extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {

                // Get the curent date and time
                Date currentDate = new Date();

                //Set up the SimpleDataFormat object with the desired format
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String dateTimeString = dateFormat.format(currentDate);
                System.out.println(dateTimeString);

                databaseHelper = new DatabaseHelper();
                databaseHelper.addData(params[0], params[1]);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    private boolean validatePullPercent() {

        pullPercent = editTxt_pullPercent.getText().toString();

        return !pullPercent.isEmpty();
    }

    private boolean validateLevel() {

        return !level.isEmpty();
    }

    private void initUI() {

        String[] type = new String[] {"Easier Than Easy", "Novice", "Average", "Hell", "Harder than Hell"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                mContext,
                R.layout.drop_down_item,
                type
        );

        filled_exposed.setAdapter(adapter);

        System.out.println("Lol");

    }
}