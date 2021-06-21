package edu.myexample.abhi.boollook;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class reviewadapter extends RecyclerView.Adapter<reviewadapter.ViewHolder>  {
    private List<Reviews> listData;

    public reviewadapter(List<Reviews> listData) {
        this.listData = listData;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardholder,parent,false);
        return new ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reviews ld=listData.get(position);
        holder.bkauthor.setText("Author : "+ld.getAuthor());
        holder.bkreview.setText("Review :"+ld.getData());
        holder.bkusername.setText("Username :"+ld.getUsername());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView bkusername;
        private final TextView bkauthor;
        private final TextView bkreview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bkauthor=(TextView)itemView.findViewById(R.id.bookname);
            bkreview=(TextView)itemView.findViewById(R.id.bookauthor);
            bkusername=(TextView)itemView.findViewById(R.id.bookdepartment);




        }
    }
    //reviewadapter.startListing();
}