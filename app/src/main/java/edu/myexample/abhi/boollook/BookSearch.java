package edu.myexample.abhi.boollook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookSearch extends AppCompatActivity {
    private List<Book> listData;
    RecyclerView rv;
    SearchView searchView;
    private Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
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
                        listData.add(l);
                    }
                    adapter = new Myadapter(listData);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        Myadapter myadapter=new Myadapter(mylist);
        rv.setAdapter(myadapter);

    }
}