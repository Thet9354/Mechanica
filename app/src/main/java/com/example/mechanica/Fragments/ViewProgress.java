package com.example.mechanica.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.mechanica.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ViewProgress extends Fragment {

    private BarChart bar_chart;
    private PieChart pie_chart;

    private androidx.recyclerview.widget.RecyclerView rv_pastProgress;

    private Context mContext;

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

        bar_chart = v.findViewById(R.id.bar_chart);
        pie_chart = v.findViewById(R.id.pie_chart);

        rv_pastProgress = v.findViewById(R.id.rv_pastProgress);

        initUI();
    }

    private void initUI() {

        //Initialize array list
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //Use for loop
        for (int i = 0; i<12; i++)
        {
            //Convert to float
            float value = (float) (i*10.0);
            //Initialize bar chart
            BarEntry barEntry = new BarEntry(i, value);
            //Initialize pie chart entry
            PieEntry pieEntry = new PieEntry(i,value);
            //Add values in array list
            barEntries.add(barEntry);
            pieEntries.add(pieEntry);
        }

        //Initial pie data set
        BarDataSet barDataSet = new BarDataSet(barEntries, "Progress");
        //Set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //Hide draw values
        barDataSet.setDrawValues(false);
        //Set pie data
        bar_chart.setData(new BarData(barDataSet));
        //Set animation
        bar_chart.animateY(3000);
        //Hide description
        bar_chart.getDescription().setText("Progress Chart");
        bar_chart.getDescription().setTextColor(Color.BLUE);

        //Initial pie data set
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Student");
        //Set colors
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //Set pie data
        pie_chart.setData(new PieData(pieDataSet));
        //Set animation
        pie_chart.animateXY(3000, 3000);
        //Hide description
        pie_chart.getDescription().setEnabled(false);


        //Init RecyclerView



    }
}