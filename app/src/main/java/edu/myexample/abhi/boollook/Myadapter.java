package edu.myexample.abhi.boollook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>  {
    private List<Book> listData;
    rvonclickinterface clickinterface;
    public Myadapter(List<Book> listData, rvonclickinterface clickinterface) {
        this.listData = listData;
        this.clickinterface=clickinterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardholder,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book ld=listData.get(position);
        holder.bkname.setText("Book Name : "+ld.getName());
        holder.bkauthor.setText("Author Name :"+ld.getAuthor());
        holder.bkdepartment.setText("Department :"+ld.getDepartment());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bkname,bkauthor,bkdepartment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bkname=(TextView)itemView.findViewById(R.id.bookname);
            bkauthor=(TextView)itemView.findViewById(R.id.bookauthor);
            bkdepartment=(TextView)itemView.findViewById(R.id.bookdepartment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickinterface.onclick(getAdapterPosition());
                }
            });



        }
    }
}
