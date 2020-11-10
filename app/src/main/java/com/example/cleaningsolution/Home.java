package com.example.cleaningsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void bookServiceOnClick(View view) {
        Intent book = new Intent(Home.this,Book_Service.class);
        startActivity(book);
    }

    public void servicesOfferedOnClick(View view) {
    }

    public void galleryOnClick(View view) {
    }

    public void addressOnClick(View view) {
        Intent book = new Intent(Home.this,MyAddresses.class);
        startActivity(book);
    }
}
