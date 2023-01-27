package com.example.mechanica.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mechanica.Model.Progress;
import com.example.mechanica.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PastProgressAdapter extends RecyclerView.Adapter<PastProgressAdapter.PastProgressViewHolder> {

    Context mContext;
    ArrayList<Progress> progressArrayList;

    public PastProgressAdapter(Context mContext, ArrayList<Progress> progressArrayList) {
        this.mContext = mContext;
        this.progressArrayList = progressArrayList;
    }

    @NonNull
    @Override
    public PastProgressAdapter.PastProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_progress, parent, false);

        return new PastProgressViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PastProgressAdapter.PastProgressViewHolder holder, int position) {

        Progress progress = progressArrayList.get(position);

        holder.txtView_difficulty.setText(progress.getLevel());
        holder.txtView_pullPercent.setText(progress.getPullPercent());

    }

    @Override
    public int getItemCount() {
        return progressArrayList.size();
    }

    public class PastProgressViewHolder extends RecyclerView.ViewHolder {

        TextView txtView_difficulty, txtView_pullPercent, txtView_date;

        public PastProgressViewHolder(@NonNull View itemView) {
            super(itemView);

            txtView_difficulty = itemView.findViewById(R.id.txtView_difficulty);
            txtView_pullPercent = itemView.findViewById(R.id.txtView_pullPercent);
        }
    }
}
