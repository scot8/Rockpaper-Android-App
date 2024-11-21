package com.example.rockpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Activity3 extends AppCompatActivity {

    ScoreDB db;
    ImageButton btnCard;
    ImageButton computerImage;
    Button btnDraw;

    TextView myTextView;
    TextView totalRound;
    TextView cScore;
    TextView uScore;
    TextView timeView;
    public int keepTrack = 0;


    AssetManager assetManager;
    ArrayList<String> imageName = new ArrayList<>();
    Random rand = new Random();

    String displayImage = "None";

    Score score = new Score(0, 0);

    String computerC = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        db = ScoreDB.getInstance(this);

        //score.setComputerScore(9);
        //score.setUserScore(8);



        db.scoreDao().deleteAll();
        db.scoreDao().insert(score);


        Intent intent = getIntent();
        String playerName = intent.getStringExtra("name");
        String mode = intent.getStringExtra("mode");

        assetManager = getAssets();
        initImageAssetList();

        btnCard = findViewById(R.id.btnCard);
        btnDraw = findViewById(R.id.btnDraw);
        myTextView = findViewById(R.id.textView2);
        timeView = findViewById(R.id.textView6);
        computerImage = findViewById(R.id.computerCard);
        EditText userChoice = findViewById(R.id.userChoice);
        userChoice.setText(playerName);

        uScore = findViewById(R.id.userScore);
        cScore = findViewById(R.id.compScore);
        totalRound = findViewById(R.id.round);



        //String card = imageName.get(rand.nextInt(imageName.size()));
        //Log.i("---", card);

        //displayImage(btnCard, card);

        CountDownTimer timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeView.setText(String.valueOf(millisUntilFinished / 1000));

            }

            //this function runs when the timer finishes
            @Override
            public void onFinish() {
                //when the timer finishes, it will set the image to "questionMark"
                //imageButton.setImageResource(R.drawable.questionmark);
                computerC = computerPlay();
                Log.i("---", computerC);
                //Log.i("user" , myTextView.getText().toString());
                String value = determineWinner(myTextView.getText().toString(), computerC, score);
                //score.setUserScore(score.getUserScore());
                //score.setComputerScore(score.getComputerScore());
                //db.scoreDao().insert(score);
                List<Score> scoreList = db.scoreDao().findAllData();


                if (!scoreList.isEmpty()) {
                    Score firstItem = scoreList.get(0);
                    firstItem.setUserScore(score.getUserScore());
                    firstItem.setComputerScore(score.getComputerScore());
                    db.scoreDao().updateScore(firstItem);
                }
                Score[] scoresArray = scoreList.toArray(new Score[0]);
                Log.i("ScoreList", Arrays.toString(scoresArray));
                for (Score score : scoreList) {
                    Log.i("Score", "User Score: " + score.getUserScore() + ", Computer Score: " + score.getComputerScore());
                }



                //String scoreP = score.getUserScore().toString();

                //Log.i("score:" , scoreP);
                Log.i("----------" , value);

                if(score.declareScore() != null ){
                    totalRound.setText(score.declareScore());
                    //score = new Score(0,0);

                    if (!scoreList.isEmpty()) {
                        Score firstItem = scoreList.get(0);
                        firstItem.setUserScore(0);
                        firstItem.setComputerScore(0);
                        db.scoreDao().updateScore(firstItem);
                    }
                }
                List<Score> scoreDatabase = db.scoreDao().findAllData();
                String compDatabase = scoreDatabase.get(0).getUserScore().toString();
                String userDatabase = scoreDatabase.get(0).getUserScore().toString();


                //String valueComp = score.getComputerScore().toString();
                //String valueUser = score.getUserScore().toString();
                cScore.setText(compDatabase);
                uScore.setText(userDatabase);



                if (mode.equals("single")){
                    btnCard.setClickable(false);
                    totalRound.setText(score.declareScoreForOnePlay());
                }


            }
        };

        btnDraw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pickACard();
            }
        });

        btnCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("Clicked on image: " );

                int dotIndex = imageName.get(keepTrack).lastIndexOf('.');

                String displayName = imageName.get(keepTrack).substring(0, dotIndex);
                myTextView.setText(displayName);

                timer.start();
//                if (mode.equals("single")){
//
//
//                    //totalRound.setText(score.declareScoreForOnePlay());
//                }

            }
        });
    }

    private void pickACard() {

        if (keepTrack < imageName.toArray().length - 1) {
            keepTrack++;
            //String arrayLengthString = imageName.toArray().size.toString();
            pickNext(keepTrack);
        }
        else{
            keepTrack = 0;
            pickNext(keepTrack);
        }

//        String card = imageName.get(rand.nextInt(imageName.size()));
//        Log.i("---", card);
//        displayImage(btnCard, card);
    }

    private void pickNext(int keepTrack) {
        //myTextView.setText(imageName.get(keepTrack));
        String card = imageName.get(keepTrack);
        Log.i("---", card);
        displayImage(btnCard, card);
    }

    private String computerPlay() {

        //myTextView.setText(imageName.get(keepTrack));
        String card = imageName.get(rand.nextInt(imageName.size()));
        Log.i("---", card);
        displayImage(computerImage, card);

        int dotIndex = card.lastIndexOf('.');
        String name = card.substring(0, dotIndex);

        return name;
    }

    private void initImageAssetList() {
        try {
            String[] assets = assetManager.list("");
            for (String asset:assets) {
                if (asset.endsWith(".PNG")){
                    imageName.add(asset);
                    Log.i("+++", asset);
                }

            }

        } catch (Exception e){
            Log.i(">>>", e.getMessage());
        }
    }


    private void displayImage(ImageButton btnCard, String image){

        try {
            InputStream inputStream = assetManager.open(image);
            Bitmap bmp = BitmapFactory.decodeStream(inputStream);
            btnCard.setImageBitmap(bmp);
            inputStream.close();

        } catch(Exception e){
            Log.i("+++", "Problme getting image resource");
        }

    }

    private void play(){



    }
    private String determineWinner(String pUserChoice, String pComputerChoice, Score score){


        String c = "userChoice";
        String winner = "User Wins";

        String userChoice = pUserChoice.toLowerCase();
        Log.i("inside determine" , userChoice);
        String computerChoice = pComputerChoice.toLowerCase();
        Log.i("inside determine com" , computerChoice);


        if(userChoice.equals(computerChoice)){
            return "its a tie";
        }

        if (userChoice.equals("rock") && (computerChoice.equals("scissors") || computerChoice.equals("lizard"))){
            score.addScore(c);
            //score = new Score(score.userScore, score.computerScore);
            //db.scoreDao().insert(score);
        }else if (userChoice.equals("paper") && (computerChoice.equals("rock") || computerChoice.equals("spock"))){
            score.addScore(c);
            //db.scoreDao().insert(score);
        }else if (userChoice.equals("scissors") && (computerChoice.equals("paper") || computerChoice.equals("lizard"))){
            score.addScore(c);
            //db.scoreDao().insert(score);
        }else if (userChoice.equals("lizard") && (computerChoice.equals("spock") || computerChoice.equals("paper"))){
            score.addScore(c);
            //db.scoreDao().insert(score);
        }else if (userChoice.equals("spock") && (computerChoice.equals("scissors") || computerChoice.equals("rock"))){
            score.addScore(c);
            //db.scoreDao().insert(score);
        }else{
            winner = "Computer Wins";
            score.addScore(computerChoice);
            //db.scoreDao().insert(score);
        }



        return winner;

    }




}