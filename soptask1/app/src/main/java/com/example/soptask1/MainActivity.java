package com.example.soptask1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView pvp, pvc;
    TextView enter1, enter2, enterRound;
    EditText name1, name2, round;
    Button play;
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pvp = findViewById(R.id.pvp);
        pvc = findViewById(R.id.pvc);
        enter1 = findViewById(R.id.textView2);
        enter2 = findViewById(R.id.textView3);
        enterRound = findViewById(R.id.textView4);
        name1 = findViewById(R.id.player1);
        name2 = findViewById(R.id.player2);
        round = findViewById(R.id.rounds);
        play = findViewById(R.id.button);


        pvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterRound.setVisibility(View.VISIBLE);
                round.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                enter1.setVisibility(View.INVISIBLE);
                name1.setVisibility(View.GONE);
                enter2.setVisibility(View.GONE);
                name2.setVisibility(View.GONE);
                mode = 1;
            }
        });

        pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter1.setVisibility(View.VISIBLE);
                name1.setVisibility(View.VISIBLE);
                enter2.setVisibility(View.VISIBLE);
                name2.setVisibility(View.VISIBLE);
                enterRound.setVisibility(View.VISIBLE);
                round.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                mode = 2;
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1, p2;
                if(mode == 1) {
                    p1 = "You";
                    p2 = "CPU";
                }
                else {
                    p1 = String.valueOf(name1.getText());
                    p2 = String.valueOf(name2.getText());
                }
                Intent toGame = new Intent(MainActivity.this, game.class);
                toGame.putExtra("Player1", p1);
                toGame.putExtra("Player2", p2);
                toGame.putExtra("Rounds", Integer.parseInt(String.valueOf(round.getText())));
                toGame.putExtra("Mode", mode);
                if(p1.length() == 0 || p2.length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter player name(s)", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(toGame);
                }
            }
        });

    }

}
