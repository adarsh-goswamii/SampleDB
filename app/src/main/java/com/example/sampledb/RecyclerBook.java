package com.example.sampledb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerBook extends RecyclerView.Adapter<RecyclerBook.ViewHolder>{

    public interface DeleteBook {
        void onDeleteBookResult(int bookId);
    }

    private DeleteBook deleteBook;
    private ArrayList<Book> arrayList;
    private Context context;

    public RecyclerBook(Context context) {
        this.context = context;
        this.arrayList= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context).asBitmap().load(arrayList.get(position).getImage_url()).into(holder.img);
        holder.name.setText(arrayList.get(position).getName());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, BookDesc.class);
                intent.putExtra("book_id", arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this book?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try{
                                    deleteBook= (DeleteBook) context;
                                    deleteBook.onDeleteBookResult(arrayList.get(position).getId());
                                }
                                catch (ClassCastException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                builder.create().show();

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setArrayList(ArrayList<Book> arrayList) {
        this.arrayList= arrayList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parent;
        ImageView img;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent= itemView.findViewById(R.id.parent);
            img= itemView.findViewById(R.id.img);
            name= itemView.findViewById(R.id.name);
        }
    }
}
