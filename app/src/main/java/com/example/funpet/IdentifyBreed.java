package com.example.funpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IdentifyBreed extends AppCompatActivity {      //creating the identify Breed class

    ImageView imageView;
    TextView correctAnswer, userAnswer;
    DisplayDog currentDog;
    Random randomImage;             //defining the buttons, spinner, textviews and imageview
    ImageDatabase imgDatabase;
    Button btnSubmit;
    Spinner spinnerDogs;

    List<String> dogNames_list;
    ArrayList<DisplayDog> dogImage_list;    //setting up arrays to sort dog images and their names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identifythebreed);

        imgDatabase = new ImageDatabase();
        randomImage = new Random();
        dogImage_list = new ArrayList<>();
        dogNames_list = new ArrayList<>();

        imageView = findViewById(R.id.randomImage_iv);      //defining the ids of the xml tags
        correctAnswer = findViewById(R.id.correctDogAnswer);
        userAnswer = findViewById(R.id.userBreedAnswer);
        spinnerDogs = findViewById(R.id.spinnerNames);
        btnSubmit = findViewById(R.id.btn_userGuessSubmit);

        for (int i = 0; i < imgDatabase.dogNames.length; i++) {       //using a loop to get all the images and the names
            DisplayDog values = new DisplayDog(imgDatabase.dogNames[i], imgDatabase.dogs[i]);
            dogImage_list.add(values);              //adding them to the new arrayList
            dogNames_list.add(values.getName());
        }

        Collections.sort(dogNames_list);        //sort the correct names of dog breeds inside the arrayList
        Collections.shuffle(dogImage_list);     //shuffling the dog images


        ArrayAdapter<String> items_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dogNames_list);
        items_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //adding dog names to the spinner


        GenerateDog();       //generating new Dog image

        spinnerDogs.setAdapter(items_adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {      //implement button functions
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (btnSubmit.getText().equals("Submit")) {
                    String userSelectedBreed = spinnerDogs.getSelectedItem().toString();

                    if (currentDog.getName().equals(userSelectedBreed)) {                    //checking whether answer is correct and validate the asnwer
                        userAnswer.setTextColor(Color.parseColor("#009900"));
                        userAnswer.setText("CORRECT !!");
                        btnSubmit.setText("NEXT");
                    } else {
                        userAnswer.setTextColor(Color.parseColor("#ff1a1a"));
                        userAnswer.setText("WRONG !!");
                        correctAnswer.setTextColor(Color.parseColor("#0000cc"));    //if the answer is wrong, setting a text to diplay the correct breed
                        correctAnswer.setText("Correct Breed is : " + currentDog.getName());
                        btnSubmit.setText("Next");
                    }

                } else {
                    GenerateDog();
                }

            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void GenerateDog() {                                      //Generating a new dog from arrayList
        int list = randomImage.nextInt(dogImage_list.size());
        currentDog = dogImage_list.get(list);
        imageView.setImageResource(currentDog.getImage());
        correctAnswer.setText("");
        userAnswer.setText("");
        btnSubmit.setText("Submit");


    }

}
