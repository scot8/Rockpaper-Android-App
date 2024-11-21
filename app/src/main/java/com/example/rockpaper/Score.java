package com.example.rockpaper;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Score{

    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name="userScore")
    Integer userScore;

    @ColumnInfo(name="computerScore")
    Integer computerScore;


    Score (Integer userScore, Integer computerScore){
        this.userScore = userScore;
        this.computerScore = computerScore;
    }

    public String declareScore(){
        if (userScore == 10 || computerScore == 10){
            if (userScore > computerScore){

                return "User won";
            }
            else{
                return "computer won";
            }
        }
        return null;
    }

    public String declareScoreForOnePlay(){

        if (userScore.equals(computerScore)){
            return "Its a tie";
        }
           else if (userScore > computerScore){

                return "User won";
            }
            else{
                return "computer won";
            }
    }
    public Integer getUserScore(){return userScore;}

    public Integer getComputerScore(){return computerScore;}

    public void setUserScore(Integer userScore){this.userScore = userScore;}

    public void setComputerScore(Integer computerScore){this.computerScore = computerScore;}

    public void addScore(String value){

        if (value.equals("userChoice")){
            this.userScore++;
        }else{
            this.computerScore++;
        }

    }

    @NonNull
    @Override
    public String toString(){
        return "Score{" + "User score='" + userScore + '\'' + ", Computer score=" + computerScore +
                '}';
    }


}

