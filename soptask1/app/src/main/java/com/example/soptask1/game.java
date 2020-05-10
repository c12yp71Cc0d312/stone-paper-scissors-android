package com.example.soptask1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class game extends AppCompatActivity {

    ConstraintLayout layout;
    ImageView rock, paper, scissor;
    String name1, name2, choice1, choice2;
    int rounds, mode, currentRound, score1, score2, turn;
    TextView turnText, roundText, score1text, score2text, nextRoundText;
    Button next;
    boolean gameOver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        layout = findViewById(R.id.game);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);
        turnText = findViewById(R.id.textView5);
        roundText = findViewById(R.id.textView6);
        currentRound = 1;
        turn = 1;
        score1text = findViewById(R.id.score1);
        score2text = findViewById(R.id.score2);
        score1 = 0;
        score2 = 0;
        next = findViewById(R.id.button2);
        nextRoundText = findViewById(R.id.nextRound);
        gameOver = false;

        Intent fromMain = getIntent();
        name1 = fromMain.getStringExtra("Player1");
        name2 = fromMain.getStringExtra("Player2");
        rounds = fromMain.getIntExtra("Rounds", 1);
        mode = fromMain.getIntExtra("Mode", 1);

        setGameScreenValues();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundResource(R.color.bg);
                if("Next".equals(next.getText())) {
                    setGameScreenValues();
                    rock.setVisibility(View.VISIBLE);
                    paper.setVisibility(View.VISIBLE);
                    scissor.setVisibility(View.VISIBLE);
                    turnText.setVisibility(View.VISIBLE);
                    roundText.setVisibility(View.VISIBLE);
                    score1text.setVisibility(View.VISIBLE);
                    score2text.setVisibility(View.VISIBLE);
                    nextRoundText.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);
                }
                else if("Result".equals(next.getText())){
                    nextRoundLayout(0);
                }
                else {
                    Intent toHome = new Intent(game.this, MainActivity.class);
                    startActivity(toHome);
                }
            }
        });

    }

    public void compareValues() {
        int roundWinner = 0;
        if(choice1.equals(choice2));
        else if(("Rock".equals(choice1) && "Scissor".equals(choice2)) || ("Paper".equals(choice1) && "Rock".equals(choice2)) || ("Scissor".equals(choice1) && "Paper".equals(choice2)) ) {
            score1++;
            roundWinner = 1;
        }
        else {
            score2++;
            roundWinner = 2;
        }
        nextRoundLayout(roundWinner);
    }

    public void nextRoundLayout(int roundWinner) {
        rock.setVisibility(View.GONE);
        paper.setVisibility(View.GONE);
        scissor.setVisibility(View.GONE);
        turnText.setVisibility(View.GONE);
        roundText.setVisibility(View.GONE);
        score1text.setVisibility(View.GONE);
        score2text.setVisibility(View.GONE);
        nextRoundText.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);


        if(gameOver) {
            next.setText("Home");
            if(mode == 1) {
                if(score1 > score2) {
                    nextRoundText.setText("You: " + score1 + "\nCPU: " + score2 + "\nYou Win!");
                }
                else if(score2 > score1){
                    nextRoundText.setText("You: " + score1 + "\nCPU: " + score2 + "\nCPU Wins!");
                }
                else {
                    nextRoundText.setText("You: " + score1 + "\nCPU: " + score2 + "\nDraw!");
                }
            }
            else {
                if(score1 > score2) {
                    nextRoundText.setText(name1 + ": " + score1 + "\n" + name2 + ": " + score2 + "\n" + name1 + " Wins!");
                }
                else if(score2 > score1){
                    nextRoundText.setText(name1 + ": " + score1 + "\n" + name2 + ": " + score2 + "\n" + name2 + " Wins!");
                }
                else {
                    nextRoundText.setText(name1 + ": " + score1 + "\n" + name2 + ": " + score2 + "\nDraw!");
                }
            }
        }

        else {
            if (roundWinner == 1) {
                if (mode == 2)
                    nextRoundText.setText(choice1 + " beats " + choice2 + "\n\n" + name1 + " wins the round");
                else {
                    nextRoundText.setText(choice1 + " beats " + choice2 + "\n\n" + "You win the round");
                    layout.setBackgroundResource(R.color.green);
                }
            } else if (roundWinner == 2) {
                if (mode == 2)
                    nextRoundText.setText(choice2 + " beats " + choice1 + "\n\n" + name2 + " wins the round");
                else {
                    nextRoundText.setText(choice2 + " beats " + choice1 + "\n\n" + "CPU wins the round");
                    layout.setBackgroundResource(R.color.red);
                }
            } else {
                nextRoundText.setText("Draw");
            }
        }

        if (currentRound > rounds) {
            gameOver = true;
            if("Next".equals(next.getText()))
                next.setText("Result");
        }

    }

    public void chooseCPUChoice() {
        Random random = new Random();
        int r = random.nextInt(3);
        if(r == 0) {
            choice2 = "Rock";
        }
        else if(r == 1) {
            choice2 = "Paper";
        }
        else {
            choice2 = "Scissor";
        }
    }

    public void optionChosen(View v) {
        String option;
        if(v.getId() == R.id.rock)
           option = "Rock";
        else if (v.getId() == R.id.paper)
            option = "Paper";
        else
            option = "Scissor";

        if (mode == 2) {
            if(turn == 1) {
                choice1 = option;
                turn++;
                turnText.setText(name2 + "'s turn");
            }
            else {
                turn = 1;
                choice2 = option;
                currentRound++;
                compareValues();
            }
        }
        else {
            if(turn == 1) {
                choice1 = option;
                turnText.setText(name2 + "'s turn");
                chooseCPUChoice();
                currentRound++;
                compareValues();
            }
        }
    }

    public void setGameScreenValues() {
        if(mode == 1)
            turnText.setText("Choose");
        else if(turn == 1)
            turnText.setText(name1 + "'s turn");
        else
            turnText.setText(name2 + "'s turn");

        roundText.setText("Round: " + currentRound + "/" + rounds);
        score1text.setText(name1 + ": " + score1);
        score2text.setText(name2 + ": " + score2);
    }
}
