package edu.myexample.abhi.boollook;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewholder extends RecyclerView.ViewHolder {
     TextView bkusername;
     TextView bkauthor;
     TextView bkreview;
    public MyViewholder(@NonNull View itemView) {
        super(itemView);
        bkauthor=(TextView)itemView.findViewById(R.id.author);
        bkreview=(TextView)itemView.findViewById(R.id.review);
        bkusername=(TextView)itemView.findViewById(R.id.username);
    }
}
