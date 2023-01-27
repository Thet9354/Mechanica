package com.example.mechanica.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.mechanica.Adapter.PastProgressAdapter;
import com.example.mechanica.Model.Progress;
import com.example.mechanica.R;
import com.example.mechanica.SpaceItemDecoration;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewProgress extends Fragment {


    private androidx.recyclerview.widget.RecyclerView rv_pastProgress;

    private Context mContext;

    private String mName;

    PastProgressAdapter pastProgressAdapter;

    ArrayList<Progress> progressArrayList;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://mechanica-1674603366861-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_progress, container, false);

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

        rv_pastProgress = v.findViewById(R.id.rv_pastProgress);

        getBundleData();

        initRecView();
    }

    private void getBundleData() {

        Bundle bundle = getArguments();
        mName = bundle.getString("Name");
    }

    private void initRecView() {


        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        rv_pastProgress.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        rv_pastProgress.setHasFixedSize(true);
        rv_pastProgress.setLayoutManager(new LinearLayoutManager(mContext));

        firestoreDB = FirebaseFirestore.getInstance();
        progressArrayList = new ArrayList<Progress>();
        pastProgressAdapter = new PastProgressAdapter(mContext, progressArrayList);

        rv_pastProgress.setAdapter(pastProgressAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {

        firestoreDB.collection("user").orderBy("Level", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null)
                        {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges())
                        {
                            if (dc.getType() == DocumentChange.Type.ADDED)
                            {
                                progressArrayList.add(dc.getDocument().toObject(Progress.class));
                            }
                            pastProgressAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


}