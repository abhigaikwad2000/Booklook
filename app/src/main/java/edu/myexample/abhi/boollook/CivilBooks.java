package edu.myexample.abhi.boollook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CivilBooks extends AppCompatActivity {
    ListView books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil_books);
        books = findViewById(R.id.books1);
        String[] values = new String[]{
                "Mechanical Engineering for Makers"," Mechatronics: Electronic Control Systems in Mechanical § Electrical Engineering",
                "The Way Things Work Now"," People Skills for Engineers","Mechanical Engineer's Pocket Book"," Aerodynamics for Engineers",
                "Gears and Gear Cutting for Home Machinists"," DeGarmo's Materials and Processes in Manufacturing"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                android.R.id.text1,values);
        books.setAdapter(adapter);
    }
}