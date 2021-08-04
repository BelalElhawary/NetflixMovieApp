package com.bebo.seriespro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.bebo.seriespro.Common.Common;
import com.khizar1556.mkvideoplayer.MKPlayer;

public class VideoPlayerActivity extends AppCompatActivity {

    MKPlayer mkplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setContentView(R.layout.activity_video_player);



        mkplayer = new MKPlayer(this);
        mkplayer.play(Common.selectedEpisodeLink);

        mkplayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            @Override
            public void onNextClick() {
                mkplayer.forward(0.1f);
            }
            @Override
            public void onPreviousClick() {
                mkplayer.forward(-0.1f);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mkplayer.stop();
    }
}