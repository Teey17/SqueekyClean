package com.example.cleaningsolution;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;


public class ProgressButton
{
    private LinearLayout linearLayout;
    private ProgressBar progressBar;
    private TextView textView;
    private CardView cardView;

    Animation fade_in;

    ProgressButton(Context ct, View view){

        cardView = view.findViewById(R.id.ProgressCard);
        textView = view.findViewById(R.id.tvProgress);
        progressBar = view.findViewById(R.id.progressBar);
        linearLayout = view.findViewById(R.id.LLProgressCard);

    }

     void buttonActivated(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Please wait getting location");
     }

     @SuppressLint("ResourceAsColor")
     void buttonFinished(){
        linearLayout.setBackgroundColor(R.color.colorPrimaryDark );
        progressBar.setVisibility(View.GONE);
        textView.setText("Location Retrieved");
     }
}
