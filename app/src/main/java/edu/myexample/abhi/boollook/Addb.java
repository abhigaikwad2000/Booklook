package edu.myexample.abhi.boollook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class Addb extends AppCompatActivity {
Button add;
    EditText name,author,dep;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addb);
        name=findViewById(R.id.bkname);
        add=findViewById(R.id.AddBook);
        progressBar=findViewById(R.id.progressBar3);
        author=findViewById(R.id.bkauthor);
        dep=findViewById(R.id.bkdepartment);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()) {
                    name.setError("Enter Book Name");
                }
                if (author.getText().toString().isEmpty()) {
                    author.setError("Enter Author Name");
                }
                if (dep.getText().toString().isEmpty()) {
                    dep.setError("Enter department Name");
                }else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    String nm=name.getText().toString();
                    String auth=author.getText().toString();
                    String depa=dep.getText().toString();

                    Book bk= new Book(auth,depa,nm);
                    FirebaseDatabase.getInstance().getReference("Book").child(nm).setValue(bk).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(Addb.this, "Book Added Successful", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(Addb.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);

                            }
                        }
                    });

                }
            }
        });




    }
}