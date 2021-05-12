package edu.myexample.abhi.boollook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashBoard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toobar;
    LinearLayout search,allbooks,newbooks,review;
    TextView navname,navemail;
    View mHeaderView;
    private FirebaseAuth auth;
    DatabaseReference reff;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        search=findViewById(R.id.booksearch);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        auth=FirebaseAuth.getInstance();
        allbooks=findViewById(R.id.allbooks);

        newbooks=findViewById(R.id.newbooks);
        review=findViewById(R.id.review);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navmenu);
        mHeaderView=navigationView.getHeaderView(0);
        navname = (TextView) mHeaderView.findViewById(R.id.navname);
        navemail= (TextView) mHeaderView.findViewById(R.id.navemail);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id)
                {
                    case R.id.logout:
                        AlertDialog.Builder alertdialog=new AlertDialog.Builder(DashBoard.this);
                        alertdialog.setIcon(R.drawable.ic_baseline_logout_24);
                        alertdialog.setTitle("Exit App");
                        alertdialog.setMessage("Are you sure to Exit App?");
                        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(DashBoard.this,Login.class);
                                startActivity(i);
                                finish();
                                dialog.cancel();
                            }
                        });
                        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getApplicationContext(),"Welcome Back",Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert=alertdialog.create();
                        alert.show();
                        return  true;

                }
                return true;
            }
        });


        toobar=findViewById(R.id.tool);
        setSupportActionBar(toobar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toobar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        reff=FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name=snapshot.child("Name").getValue().toString();
                String email=snapshot.child("email").getValue().toString();
                //Toast.makeText(DashBoard.this,""+name+email,Toast.LENGTH_LONG).show();
                navname.setText(name);
                navemail.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       allbooks.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(DashBoard.this,AllBooks.class);
               startActivity(i);
           }
       });
       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(DashBoard.this,BookSearch.class);
               startActivity(i);
           }
       });


    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();

        }
    }



}