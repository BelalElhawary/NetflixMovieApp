package com.bebo.seriespro.Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bebo.seriespro.Common.Common;
import com.bebo.seriespro.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ShowDetails extends AppCompatActivity {

    TextView show_name;
    TextView show_summary;
    ImageView show_image;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        show_name = (TextView)findViewById(R.id.show_name);
        show_summary = (TextView)findViewById(R.id.show_summary);
        show_image = (ImageView)findViewById(R.id.show_image);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_seasons);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        recyclerView.setAdapter(new MySeasonAdapter(getBaseContext(), Common.showSelected.Season));


        Picasso.get().load(Common.showSelected.Image).into(show_image);
        show_name.setText(Common.showSelected.Name);
        show_summary.setText(Common.showSelected.Summary);
    }
}