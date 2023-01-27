package com.example.mechanica.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mechanica.Model.Games;
import com.example.mechanica.Model.WristExercise;
import com.example.mechanica.R;

import java.util.ArrayList;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.CardViewHolder>{

    private final ArrayList<Games> gamesArrayList;
    private final Context mContext;

    public GamesAdapter(Context mContext, ArrayList<Games> gamesArrayList) {
        this.gamesArrayList = gamesArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public GamesAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_game, parent, false);

        return new GamesAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.CardViewHolder holder, int position) {

        holder.txtView_game.setText(gamesArrayList.get(position).getGame());
        holder.imgView_game.setImageResource(gamesArrayList.get(position).getGamePic());
    }

    @Override
    public int getItemCount() {
        return gamesArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgView_game;
        private final TextView txtView_game;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView_game = itemView.findViewById(R.id.imgView_game);
            txtView_game = itemView.findViewById(R.id.txtView_game);
        }
    }
}
