package com.bebo.seriespro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bebo.seriespro.Adapter.MyEpisodeAdapter;
import com.bebo.seriespro.Adapter.MySeasonAdapter;
import com.bebo.seriespro.Common.Common;

public class SeasonDetails extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_details);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_episodes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        recyclerView.setAdapter(new MyEpisodeAdapter(getBaseContext(), Common.showSelected.Season.get(Common.seasonIndex).Episodes));

    }
}