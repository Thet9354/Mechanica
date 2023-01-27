package com.example.mechanica.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mechanica.Adapter.ArticleAdapter;
import com.example.mechanica.Adapter.GamesAdapter;
import com.example.mechanica.Adapter.WristExerciseAdapter;
import com.example.mechanica.Model.Article;
import com.example.mechanica.Model.Games;
import com.example.mechanica.Model.WristExercise;
import com.example.mechanica.R;
import com.example.mechanica.Settings.SettingsPage_Activity;
import com.example.mechanica.SpaceItemDecoration;
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


public class Home extends Fragment {

    private Context mContext;

    private ImageView btn_setting;

    private androidx.recyclerview.widget.RecyclerView rv_exercise, rv_games, rv_articles;

    private WristExerciseAdapter wristExerciseAdapter;
    private final ArrayList<WristExercise> wristExerciseArrayList = new ArrayList<>();

    private GamesAdapter gamesAdapter;
    private final ArrayList<Games> gamesArrayList = new ArrayList<>();

    private ArticleAdapter articleAdapter;
    private final ArrayList<Article> articleArrayList = new ArrayList<>();

    int[] exercise = {R.drawable.flexion, R.drawable.hand_flip, R.drawable.radial,
            R.drawable.extenor, R.drawable.flexor};

    int[] game = {R.drawable.subway_surfer, R.drawable.super_mario, R.drawable.pac_man,
            R.drawable.temple_run, R.drawable.donkey_kong, R.drawable.nfs, R.drawable.valorant};

    int[] blogArticle = {R.drawable.article1, R.drawable.article2, R.drawable.article3,
            R.drawable.article4, R.drawable.article5};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

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

        //ImageView
        btn_setting = v.findViewById(R.id.btn_setting);

        //RecyclerView
        rv_exercise = v.findViewById(R.id.rv_exercise);
        rv_games = v.findViewById(R.id.rv_games);
        rv_articles = v.findViewById(R.id.rv_articles);

        initWristExercise();

        initGames();

        initBlogArticles();


        pageDirectories();
    }

    private void initBlogArticles() {

        //for better performance of recyclerview.

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        rv_articles.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        rv_articles.setHasFixedSize(true);

        articleAdapter = new ArticleAdapter(getContext(), articleArrayList);
        rv_articles.setAdapter(articleAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_articles.setLayoutManager(llm);

        new loadArticle().execute();
    }

    private void initGames() {

        //for better performance of recyclerview.

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        rv_games.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        rv_games.setHasFixedSize(true);

        gamesAdapter = new GamesAdapter(getContext(), gamesArrayList);
        rv_games.setAdapter(wristExerciseAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_games.setLayoutManager(llm);

        new loadGame().execute();
    }

    private void initWristExercise() {

        //for better performance of recyclerview.

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        rv_exercise.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        rv_exercise.setHasFixedSize(true);

        wristExerciseAdapter = new WristExerciseAdapter(getContext(), wristExerciseArrayList);
        rv_exercise.setAdapter(wristExerciseAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_exercise.setLayoutManager(llm);

        new loadExercise().execute();
    }

    WristExercise wristExercise;
    Games games;
    Article article;


    class loadExercise extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] exerciseTitle = getResources().getStringArray(R.array.exercise_title);

                for (int i = 0 ; i < exerciseTitle.length; i++)
                {
                    wristExercise = new WristExercise();

                    wristExercise.setExercise(exerciseTitle[i]);
                    wristExercise.setExerciseImg(exercise[i]);

                    wristExerciseArrayList.add(wristExercise);
                    wristExercise = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

//            pgbPopulardestination.setVisibility(View.GONE);

            if (wristExerciseArrayList != null && wristExerciseArrayList.size() > 0) {
                wristExerciseAdapter = new WristExerciseAdapter(mContext, wristExerciseArrayList);
                rv_exercise.setAdapter(wristExerciseAdapter);
                wristExerciseAdapter.notifyDataSetChanged();
            }
        }
    }

    class loadGame extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] gameTitle = getResources().getStringArray(R.array.game_title);

                for (int i = 0 ; i < gameTitle.length; i++)
                {
                    games = new Games();

                    games.setGame(gameTitle[i]);
                    games.setGamePic(game[i]);

                    gamesArrayList.add(games);
                    games = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

//            pgbPopulardestination.setVisibility(View.GONE);

            if (gamesArrayList != null && gamesArrayList.size() > 0) {
                gamesAdapter = new GamesAdapter(mContext, gamesArrayList);
                rv_games.setAdapter(gamesAdapter);
                gamesAdapter.notifyDataSetChanged();
            }
        }
    }

    class loadArticle extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] articleTitle = getResources().getStringArray(R.array.article_title);

                for (int i = 0 ; i < articleTitle.length; i++)
                {
                    article = new Article();

                    article.setArticle(articleTitle[i]);
                    article.setArticlePic(blogArticle[i]);

                    articleArrayList.add(article);
                    article = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            if (articleArrayList != null && articleArrayList.size() > 0) {
                articleAdapter = new ArticleAdapter(mContext, articleArrayList);
                rv_articles.setAdapter(articleAdapter);
                articleAdapter.notifyDataSetChanged();
            }
        }
    }




    private void pageDirectories() {

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SettingsPage_Activity.class);
                startActivity(intent);
            }
        });
    }


}