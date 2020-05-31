package com.example.funpet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class SearchBreed extends AppCompatActivity {
    ViewFlipper img_flipepr;
    EditText textBreedNameInput;
    Button btnSubmit, btnStop;
    TextView textWrongInput;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_breed);

        btnSubmit = findViewById(R.id.buttonSubmit);
        btnStop = findViewById(R.id.buttonStop);
        textBreedNameInput = findViewById(R.id.textBreedName);
        textWrongInput = findViewById(R.id.textWrongMsg);
        img_flipepr = findViewById(R.id.imgFlipper);



        int images[] = {R.drawable.beagle_1, R.drawable.beagle_2, R.drawable.beagle_3, R.drawable.beagle_4, R.drawable.beagle_5};


        for (int image : images) {
            flipperImages(image);
        }


                }




        public void flipperImages(int image) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);

            img_flipepr.addView(imageView);
            img_flipepr.setFlipInterval(5000);
            img_flipepr.setAutoStart(true);

            img_flipepr.setInAnimation(this, android.R.anim.slide_in_left);
            img_flipepr.setOutAnimation(this, android.R.anim.slide_out_right);


        }

}
