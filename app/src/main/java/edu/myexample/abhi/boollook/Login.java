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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button login, signup;
    EditText uname, pass;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

//hii git
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        uname = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Uname = uname.getText().toString().trim();
                String Pass = pass.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);

                if(!Patterns.EMAIL_ADDRESS.matcher(Uname).matches())
                {
                    uname.setError("Please provide Valid Email");
                }
                if(pass.getText().toString().length()<6)
                {
                    pass.setError("Password length must be grater than 6 letters");
                }
                else {
                    mAuth.signInWithEmailAndPassword(Uname, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                Intent i=new Intent(Login.this,DashBoard.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(Login.this, "Invalid Email or Password Login Unsuccessful", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //String abc=currentUser.getDisplayName();
        if (currentUser != null) {
            //  updateUI(currentUser);
            //Toast.makeText(Login.this, "Already logged in ", Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();
        }

    }
}