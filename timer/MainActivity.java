package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerseekBar;
    Boolean counterisActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public void resetTimer()
    {
        timerTextView.setText("0:30");
        timerseekBar.setProgress(30);
        timerseekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterisActive = false;
    }

    public void buttonClicked(View view)
    {
        if(counterisActive)
        {
          resetTimer();
        }
        else {
            counterisActive = true;
            timerseekBar.setEnabled(false);
            goButton.setText("STOP!");


            countDownTimer = new CountDownTimer(timerseekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.sound1);
                    mplayer.start();
                    resetTimer();
                }

            }.start();
        }
    }

    public void updateTimer(int secondsLeft)
    {
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes*60);

        String secondstring = Integer.toString(seconds);
        if(seconds<=9)
            secondstring = "0" +secondstring;

        timerTextView.setText(Integer.toString(minutes) +":" +secondstring);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerseekBar = (SeekBar)findViewById(R.id.timerseekBar);
        timerTextView = findViewById(R.id.timertextView);
        goButton = findViewById(R.id.button);

        timerseekBar.setMax(600);
        timerseekBar.setProgress(30);

        timerseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
