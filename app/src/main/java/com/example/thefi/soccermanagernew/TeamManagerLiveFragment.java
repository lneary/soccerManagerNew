package com.example.thefi.soccermanagernew;


import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamManagerLiveFragment extends Fragment {
    CountDownTimerPausable cdtp;
    TextView clock;
    ImageButton refresh;
    ImageButton pausePlay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_team_manager_live, container, false);

        clock = (TextView) rootview.findViewById(R.id.liveGameTimer);
        refresh = (ImageButton) rootview.findViewById(R.id.liveRefreshTimer);
        pausePlay = (ImageButton) rootview.findViewById(R.id.livePlayPauseTimer);

        cdtp = new CountDownTimerPausable(2400000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long  sec = millisUntilFinished /1000 + 1;
                if (sec%60 == 0)
                    clock.setText(sec/60+" : 00");
                else
                    clock.setText(sec/60+" : "+sec%60);


            }

            @Override
            public void onFinish() {
                reset();
            }
        };

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        pausePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cdtp.isPaused){
                   cdtp.start();
                   pausePlay.setImageResource(R.drawable.ic_pause_black_24dp);
                }
                else{
                    cdtp.pause();
                    pausePlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                }
            }
        });

        return rootview;
    }

    public void reset(){
        cdtp.cancel();
        cdtp = null;
        clock.setText("40 : 00");
        pausePlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        cdtp = new CountDownTimerPausable(2400000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long  sec = millisUntilFinished /1000 + 1;
                if (sec%60 == 0)
                    clock.setText(sec/60+" : 00");
                else
                    clock.setText(sec/60+" : "+sec%60);
            }

            @Override
            public void onFinish() {
                reset();
            }
        };
    }

}
