package com.example.funpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

   public void launchIdentifyBreedActivity(View view) {
        //Log.d(LOG_TAG, "Button clicked!");

        Intent intent = new Intent(this, IdentifyBreed.class);
        startActivity(intent);
    }

    public void launchIdentifyDogActivity(View view) {

        Intent intent = new Intent(this, IdentifyDog.class);
        startActivity(intent);
    }

    public void launchSearchDogBreedActivity(View view) {
        Intent intent = new Intent(this,SearchBreed.class);
        startActivity(intent);
    }
}
