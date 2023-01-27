package com.example.mechanica.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mechanica.Model.WristExercise;
import com.example.mechanica.R;

import java.util.ArrayList;

public class WristExerciseAdapter extends RecyclerView.Adapter<WristExerciseAdapter.CardViewHolder>{

    private final ArrayList<WristExercise> wristExerciseArrayList;
    private final Context mContext;

    public WristExerciseAdapter(Context mContext, ArrayList<WristExercise> wristExerciseArrayList) {
        this.wristExerciseArrayList = wristExerciseArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public WristExerciseAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_wrist, parent, false);

        return new WristExerciseAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WristExerciseAdapter.CardViewHolder holder, int position) {

        holder.txtView_wristExercise.setText(wristExerciseArrayList.get(position).getExercise());
        holder.imgView_wristExercise.setImageResource(wristExerciseArrayList.get(position).getExerciseImg());

    }

    @Override
    public int getItemCount() {
        return wristExerciseArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgView_wristExercise;
        private final TextView txtView_wristExercise;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView_wristExercise = itemView.findViewById(R.id.imgView_wristExercise);
            txtView_wristExercise = itemView.findViewById(R.id.txtView_wristExercise);

        }
    }
}
