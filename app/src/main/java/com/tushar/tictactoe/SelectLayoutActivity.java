package com.tushar.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SelectLayoutActivity extends AppCompatActivity {
    TextView textView4;
    Intent intent;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_layout);
        textView4 = findViewById(R.id.textView4);
        textView4.setText("3 x 3");
        n = 3;
        Button button2 = findViewById(R.id.button2);
        intent = new Intent(this, MainActivity2.class);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("player1", new Intent(getIntent()).getStringExtra("player1").toString());
                intent.putExtra("player2", new Intent(getIntent()).getStringExtra("player2").toString());
                intent.putExtra("grid", n);
                startActivity(intent);
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.imageButtonNext) {
            textView4.setText("5 x 5");
            n = 5;
        } else {
            textView4.setText("3 x 3");
            n = 3;
        }
    }
}