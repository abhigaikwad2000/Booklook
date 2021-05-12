package edu.myexample.abhi.boollook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText uname,pass,name,phon;
    Button reg,log;
    private FirebaseAuth auth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        uname=findViewById(R.id.usernamesignup);
        pass=findViewById(R.id.passwordsignup);
        progressBar=findViewById(R.id.progressBar2);
        name=findViewById(R.id.Name);
        auth=FirebaseAuth.getInstance();
        phon=findViewById(R.id.Phon);
        reg=findViewById(R.id.register);
        log=findViewById(R.id.log);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = uname.getText().toString().trim();
                String Pass = pass.getText().toString().trim();
                String Name = name.getText().toString().trim();
                String Phon = phon.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);
                if (Name.isEmpty()) {
                    name.setError("Enter Name");
                    name.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    uname.setError("Please provide Valid Email");
                    uname.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);

                }
                if(pass.getText().toString().length()<6)
                {
                    pass.setError("Password length must be grater than 6 letters");
                    pass.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                if(Phon.isEmpty())
                {
                    phon.setError("pls Provide phon no");
                    phon.requestFocus();
                    progressBar.setVisibility(View.INVISIBLE);
                }

                else
                {
                    auth.createUserWithEmailAndPassword(email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user u = new user(email, Pass, Name, Phon);

                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                            Intent i=new Intent(Signup.this,Login.class);
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.INVISIBLE);

                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });

                }


            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Signup.this,Login.class);
                startActivity(i);
            }
        });

    }
}