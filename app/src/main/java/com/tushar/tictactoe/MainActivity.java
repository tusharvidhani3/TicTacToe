package com.tushar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    char[][] val;
    int c;
    String player1,player2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val = new char[3][3];
        c = 0;
        Intent intent=getIntent();
        player1=intent.getStringExtra("player1");
        if(player1.isEmpty())
            player1="X";
        player2=intent.getStringExtra("player2");
        if(player2.isEmpty())
            player2="O";
        textView = findViewById(R.id.textView);
        textView.setText(player1+" turn");
    }

    public void onClick(View v) {
        int rc = 0, cc = 0, d1 = 0, d2 = 0;
        ImageView w;
        w = (ImageView) v;
        String bid = getResources().getResourceEntryName(v.getId());
        int row = Integer.parseInt(bid.substring(9, 10));
        int col = Integer.parseInt(bid.substring(10));
        char curr;
        if (c % 2 == 0) {
            w.setImageResource(R.drawable.x);
            val[row][col] = 'x';
            textView.setText(player2+" turn");
        }
        else {
            w.setImageResource(R.drawable.o);
            val[row][col] = 'o';
            textView.setText(player1+" turn");
        }
        curr = val[row][col];
        c++;
        v.setOnClickListener(null);
        for (int i = 0; i < 3; i++) {
            if (val[row][i] == curr)
                rc++;
            if (val[i][col] == curr)
                cc++;
            if (val[i][i] == curr)
                d1++;
            if (val[i][2 - i] == curr)
                d2++;
        }
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.game_over_dialog);
        dialog.setCancelable(false);
        TextView dialogTextView = dialog.findViewById(R.id.textView);
        Button dialogButton=dialog.findViewById(R.id.button);
        if (rc == 3 || cc == 3 || d1 == 3 || d2 == 3) {
            if(curr=='x') {
                textView.setText(player1 + " WON");
                dialogTextView.setText(player1 + " WON");
            }
            else {
                textView.setText(player2 + " WON");
                dialogTextView.setText(player2 + " WON");
            }
            dialog.show();
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                    startActivity(getIntent());
                }
            });
        }
        else if(c==9) {
            textView.setText("DRAW");
            dialogTextView.setText("Match DRAW");
            dialog.show();
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                    startActivity(getIntent());
                }
            });
        }
    }
}