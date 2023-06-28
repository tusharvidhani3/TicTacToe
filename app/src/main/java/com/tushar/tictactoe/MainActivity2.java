package com.tushar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    char[][] val;
    int c;
    String player1, player2;
    TextView textView;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        n = intent.getIntExtra("grid", 3);
        if (n == 3)
            setContentView(R.layout.activity_main2);
        else
            setContentView(R.layout.activity_main3);
        val = new char[n][n];
        c = 0;
        player1 = intent.getStringExtra("player1");
        if (player1.isEmpty())
            player1 = "X";
        player2 = intent.getStringExtra("player2");
        if (player2.isEmpty())
            player2 = "O";
        textView = findViewById(R.id.textView);
        textView.setText(player1 + " turn");
    }

    public void onClick(View v) {
        int rc = 0, cc = 0, d1 = 0, d2 = 0;
        ImageView w;
        w = (ImageView) v;
        String bId = getResources().getResourceEntryName(v.getId());
        int row = Integer.parseInt(bId.substring(9, 10));
        int col = Integer.parseInt(bId.substring(10));
        char curr;
        if (c % 2 == 0) {
            w.setImageResource(R.drawable.x);
            val[row][col] = 'x';
            textView.setText(player2 + " turn");
        } else {
            w.setImageResource(R.drawable.o);
            val[row][col] = 'o';
            textView.setText(player1 + " turn");
        }
        curr = val[row][col];
        c++;
        v.setOnClickListener(null);
        for (int i = 0; i < n; i++) {
            if (val[row][i] == curr)
                rc++;
            if (val[i][col] == curr)
                cc++;
            if (val[i][i] == curr)
                d1++;
            if (val[i][n - 1 - i] == curr)
                d2++;
        }
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.game_over_dialog);
        dialog.setCancelable(false);
        TextView dialogTextView = dialog.findViewById(R.id.textView);
        Button dialogButton = dialog.findViewById(R.id.button);
        if (rc == n || cc == n || d1 == n || d2 == n) {
            if (curr == 'x') {
                textView.setText(player1 + " WON");
                dialogTextView.setText(player1 + " WON");
            } else {
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
        } else if (c == n * n) {
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