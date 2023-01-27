package com.example.mechanica.Fragments;

import android.content.Context;
import android.content.Intent;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mechanica.DatabaseHelper;
import com.example.mechanica.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddProgress extends Fragment {

    private Context mContext;


    public static final int TIMER = 2000;

    private AutoCompleteTextView filled_exposed;
    private com.google.android.material.textfield.TextInputLayout editTxtInputLayout;
    private com.google.android.material.textfield.TextInputEditText editTxt_pullPercent;

    private RelativeLayout rel_btn;
    private TextView btn_saveProgress;
    private LottieAnimationView btn_animation;

    private String level, pullPercent;

    private String mName;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://mechanica-1674603366861-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

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

        getBundleData();

        initUI();

        pageDirectories();
    }

    private void getBundleData() {

        Bundle bundle = getArguments();
        mName = bundle.getString("Name");
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
            AddData();
        }
    }

    private void AddData()  {

                //Add data in the firebase
//                databaseReference.child(mName).child("User's Progress Details").child("Level").setValue(level);
//                databaseReference.child(mName).child("User's Progress Details").child("PullPercent").setValue(pullPercent);





                //Implementing data into google firebase firestore
                Map<String, Object> user = new HashMap<>();
                user.put("Level", level);
                user.put("PullPercent", pullPercent);

                firestoreDB.collection("user")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(mContext, "Progress Added", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });

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


    }
}