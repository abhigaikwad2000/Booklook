package edu.myexample.abhi.boollook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    TextView Name,Author,Dep;
    EditText editText;
    RecyclerView rvr;
    Button button;
    DatabaseReference ref;
    private reviewadapter adapter;
    private List<Reviews> listData2;
    private FirebaseRecyclerOptions<Reviews> options;
    private FirebaseRecyclerAdapter<Reviews,MyViewholder> rvradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Name=findViewById(R.id.textView);
        button=findViewById(R.id.submit);
        Author=findViewById(R.id.textView2);
        Dep=findViewById(R.id.textView3);
        editText=findViewById(R.id.reviewinput);
        rvr = (RecyclerView) findViewById(R.id.rv2);
        rvr.setHasFixedSize(true);
        rvr.setLayoutManager(new LinearLayoutManager(this));

        String name=getIntent().getExtras().getString("name");
        String author=getIntent().getExtras().getString("author");
        String dep=getIntent().getExtras().getString("dep");
        String username2=getIntent().getExtras().getString("username");
        Toast.makeText(BookActivity.this,username2, Toast.LENGTH_LONG).show();
        ref=FirebaseDatabase.getInstance().getReference("Reviews").child(name);



        Name.setText(name);
        Author.setText(author);
        Dep.setText(dep);
        listData2 = new ArrayList<>();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty()) {
                    editText.setError("Enter Review");
                } else {
                    String reviewId = FirebaseDatabase.getInstance().getReference("Reviews").child(name).push()
                            .getKey();

                    String review = editText.getText().toString().trim();
                    //String username=getIntent().getExtras().getString("username");

                    Reviews r = new Reviews(author,review,username2);
                    FirebaseDatabase.getInstance().getReference("Reviews").child(name).child(reviewId).setValue(r).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(BookActivity.this, "Successful", Toast.LENGTH_LONG).show();
                            //rvr.setAdapter(new reviewadapter(listData2).notifyDataSetChanged());


                        }
                    });

                }
            }


        });

        options=new FirebaseRecyclerOptions.Builder<Reviews>().setQuery(ref,Reviews.class).build();
        rvradapter=new FirebaseRecyclerAdapter<Reviews, MyViewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewholder holder, int position, @NonNull Reviews model) {
                //Reviews ld=listData2.get(position);
                holder.up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(BookActivity.this, "hii", Toast.LENGTH_LONG).show();
                        //Toast.makeText(BookActivity.this, "hii"+model.getKey(), Toast.LENGTH_LONG).show();
                        //ref=FirebaseDatabase.getInstance().getReference().child("Reviews").child(name).child(model.getKey()).child("author");
                       // int k=10;
                       // ref.setValue(k);


                    }
                });
                holder.bkauthor.setText(model.getAuthor());
                holder.bkreview.setText(model.getData());
                holder.bkusername.setText(model.getUsername());


            }


            @NonNull
            @Override
            public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardholder2,parent,false);
                return new MyViewholder(view);

            }


        };
        rvradapter.startListening();
        rvr.setAdapter(rvradapter);

           /* final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Reviews").child(name);
            nm.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                            Reviews l = npsnapshot.getValue(Reviews.class);
                            // Toast.makeText(BookActivity.this,""+l.getData(),Toast.LENGTH_LONG).show();
                            listData2.add(l);
                            Toast.makeText(BookActivity.this, "" + l.getUsername(), Toast.LENGTH_LONG).show();
                        }

                        rvr.setAdapter(new reviewadapter(listData2));



                        // adapter = new Myadapter(listData,this);
                        //rv.setAdapter(adapter);
                        // adapter = new Myadapter(listData);
                        //rv.setAdapter(adapter);
                    } else {
                        Toast.makeText(BookActivity.this, "adapter problem", Toast.LENGTH_LONG).show();
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

    }


}