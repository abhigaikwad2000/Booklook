package edu.myexample.abhi.boollook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookSearch extends AppCompatActivity{
    private List<Book> listData;
    RecyclerView rv;
    SearchView searchView;
    private Myadapter adapter;
    String username2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        username2=getIntent().getExtras().getString("username");
        Toast.makeText(this,username2,Toast.LENGTH_LONG).show();

        searchView=findViewById(R.id.search);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();

        final DatabaseReference nm = FirebaseDatabase.getInstance().getReference("Book");
        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Book l = npsnapshot.getValue(Book.class);
                       // Toast.makeText(BookSearch.this,""+l.getName(),Toast.LENGTH_LONG).show();
                        listData.add(l);
                    }

                    rv.setAdapter(new Myadapter(listData, new rvonclickinterface() {
                        @Override
                        public void onclick(int position) {
                            Book ld=listData.get(position);
                            String name=ld.getName();
                            String author=ld.getAuthor();
                            String dep=ld.getDepartment();
                            //Toast.makeText(BookSearch.this,"Book name"+name,Toast.LENGTH_LONG).show();
                            Intent i=new Intent(BookSearch.this,BookActivity.class);
                            i.putExtra("name",name);
                            i.putExtra("author",author);
                            i.putExtra("dep",dep);
                            i.putExtra("username",username2);

                            startActivity(i);
                        }
                    }));


                            // adapter = new Myadapter(listData,this);
                            //rv.setAdapter(adapter);
                            // adapter = new Myadapter(listData);
                            //rv.setAdapter(adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



      // adapter = new Myadapter(listData,this);
       //rv.setAdapter(adapter);

    }


    @Override
    protected void onStart() {


        if(searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
        super.onStart();
    }

    private void search(String newText) {
        ArrayList<Book> mylist=new ArrayList<>();
        for(Book object: listData)
        {
            if(object.getName().toLowerCase().contains(newText.toLowerCase()) || object.getAuthor().toLowerCase().contains(newText.toLowerCase())||object.getDepartment().toLowerCase().contains(newText.toLowerCase()))
            {
                mylist.add(object);
            }
        }
        rv.setAdapter(new Myadapter(mylist, new rvonclickinterface() {
            @Override
            public void onclick(int position) {

                Book ld=mylist.get(position);
                String name=ld.getName();

                String author=ld.getAuthor();
                String dep=ld.getDepartment();
                //Toast.makeText(BookSearch.this,"Book name"+name,Toast.LENGTH_LONG).show();
                Intent i=new Intent(BookSearch.this,BookActivity.class);
                i.putExtra("name",name);
                i.putExtra("author",author);
                i.putExtra("dep",dep);
                i.putExtra("username",username2);
                startActivity(i);
            }
        }));

    }

}