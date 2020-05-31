package com.example.funpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class IdentifyDog extends AppCompatActivity {        //creating the main class

    TextView dogName, userGuessAnswer;      //defining textviews buttons imageviews
    Button buttonNext;
    ImageView randomDogimg1, randomDogimg2, randomDogimg3, actualDog;

    ImageDatabase imgDatabase;
    DisplayDog currentDog;
    Random randomImages;

    ArrayList<DisplayDog> dogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_dog);

        buttonNext = findViewById(R.id.buttonNext);         //defining the ids of the buttons and dog images
        randomDogimg1 = findViewById(R.id.randomDogimg1);
        randomDogimg2 = findViewById(R.id.randomDogimg2);
        randomDogimg3 = findViewById(R.id.randomDogimg3);
        userGuessAnswer = findViewById(R.id.guessAnswer);
        dogName = findViewById(R.id.dogName);

        randomImages = new Random();
        imgDatabase = new ImageDatabase();
        dogList = new ArrayList();

        for (int i = 0; i < imgDatabase.dogNames.length; i++) {     //using  for loop to get the dog names and names
            DisplayDog values = new DisplayDog(imgDatabase.dogNames[i], imgDatabase.dogs[i]);   //adding dogs and their names to a common array
            dogList.add(values);
        }
        Collections.shuffle(dogList);       //shuffling to get random different values

        GenerateDog();      //generating new dog images with the method


        buttonNext.setOnClickListener(new View.OnClickListener() {      //defines what will happen when clicking the button
            @Override
            public void onClick(View view) {
                GenerateDog();                                          //generates 3 dog images when the button clicks
            }
        });

        randomDogimg1.setOnClickListener(new View.OnClickListener() {       //make images clickable with the onClickListener and implement function
            @Override
            public void onClick(View view) {
                answerCheck(randomDogimg1);
            }
        });

        randomDogimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerCheck(randomDogimg2);
            }
        });

        randomDogimg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerCheck(randomDogimg3);
            }
        });

    }

    void GenerateDog() {                    //method of displayig new 3 dog images randomly aand unique
        userGuessAnswer.setText("");
        int img1 = randomImages.nextInt(dogList.size());
        int img2 = randomImages.nextInt(dogList.size());
        int img3 = randomImages.nextInt(dogList.size());
        int a = randomImages.nextInt(3);
        currentDog = dogList.get(img1);

        while (img2 == img1 || img3 == img1) {                  //making sure the dog images are not repeating
            img2 = randomImages.nextInt(dogList.size());
            img3 = randomImages.nextInt(dogList.size());
        }

        if (a == 1) {
            randomDogimg1.setImageResource(currentDog.getImage());
            randomDogimg2.setImageResource(dogList.get(img2).getImage());
            randomDogimg3.setImageResource(dogList.get(img3).getImage());
            actualDog = randomDogimg1;
        } else if (a == 2) {
            randomDogimg1.setImageResource((dogList.get(img2).getImage()));
            randomDogimg2.setImageResource(currentDog.getImage());
            randomDogimg3.setImageResource(dogList.get(img3).getImage());
            actualDog = randomDogimg2;
        } else {
            randomDogimg1.setImageResource(dogList.get(img3).getImage());
            randomDogimg2.setImageResource(dogList.get(img2).getImage());
            randomDogimg3.setImageResource(currentDog.getImage());
            actualDog = randomDogimg3;

        }

        dogName.setText(currentDog.getName());
    }

    @SuppressLint("SetTextI18n")
    void answerCheck(ImageView img) {               //checking whether the answer is correct and validating the asnwers
        if (img == actualDog) {
            userGuessAnswer.setTextColor(Color.parseColor("#009900"));
            userGuessAnswer.setText("Correct!");
        } else {
            userGuessAnswer.setTextColor(Color.parseColor("#ff1a1a"));
            userGuessAnswer.setText("Wrong!");
        }
    }
}
