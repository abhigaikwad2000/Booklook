package edu.myexample.abhi.boollook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllBooks extends AppCompatActivity {
    Button btn_me , btn_civ , btn_cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        btn_me = findViewById(R.id.btn1);
        btn_civ = findViewById(R.id.btn2);
        btn_cs = findViewById(R.id.btn3);
        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllBooks.this,MechBooks.class);
                startActivity(i);
            }
        });
        btn_civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllBooks.this,CivilBooks.class);
                startActivity(i);
            }
        });
        btn_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllBooks.this,CsBooks.class);
                startActivity(i);
            }
    });
}
}