package com.bebo.seriespro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bebo.seriespro.Adapter.MyShowAdapter;
import com.bebo.seriespro.Adapter.MySliderAdapter;
import com.bebo.seriespro.Common.Common;
import com.bebo.seriespro.Module.Show;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderPager;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference show;
    SliderView sliderView;

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = FirebaseDatabase.getInstance("https://series-pro-9d973-default-rtdb.europe-west1.firebasedatabase.app").getReference("Show");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_show);
        sliderView = (SliderView)findViewById(R.id.imageSlider);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        recyclerView.setLayoutManager(horizontalLayoutManager);

        LoadSliderImages();
        LoadShowList();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void LoadSliderImages()
    {
        List<String> pics = new ArrayList<>();
        pics.add("https://inculture.microsoft.com/wp-content/uploads/prod/2019/07/st3-all-up-logo-3200x1800.jpg");
        pics.add("https://alumniyat.net/wp-content/uploads/2021/02/xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg");
        pics.add("https://www.annahar.com/ContentFilesArchive/135283Image1-1180x677_d.jpg?version=796835");

        sliderView.setSliderAdapter(new MySliderAdapter(this, pics));
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.startAutoCycle();
    }

    private void LoadShowList(){
        show.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<Show> shows = new ArrayList<>();
                for (DataSnapshot showSnapShot : snapshot.getChildren()) {
                    Show showElement = showSnapShot.getValue(Show.class);
                    shows.add(showElement);
                }
                Common.showList = shows;
                OnShowLoadDone();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void OnShowLoadDone(){
        recyclerView.setAdapter(new MyShowAdapter(this, Common.showList));
    }
}