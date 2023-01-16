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

import java.util.ArrayList;

public class PastProgressAdapter extends RecyclerView.Adapter<PastProgressAdapter.CardViewHolder>{

    private Context mcontext;

    private ArrayList<Progress> progressArrayList;

    public PastProgressAdapter(Context mcontext, ArrayList<Progress> progressArrayList) {
        this.mcontext = mcontext;
        this.progressArrayList = progressArrayList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private PastProgressAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(PastProgressAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mcontext;
    }

    @NonNull
    @Override
    public PastProgressAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_progress, parent, false);

        return new PastProgressAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PastProgressAdapter.CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return progressArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cv_pastProgress;

        private TextView txtView_difficulty, txtView_pullPercent, txtView_date;

        public CardViewHolder(@NonNull View v) {
            super(v);

            //CardView
            cv_pastProgress = v.findViewById(R.id.cv_pastProgress);

            //TexView
            txtView_difficulty = v.findViewById(R.id.txtView_difficulty);
            txtView_pullPercent = v.findViewById(R.id.txtView_pullPercent);
            txtView_date = v.findViewById(R.id.txtView_date);


        }
    }
}
