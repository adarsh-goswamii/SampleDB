package com.example.sampledb;

import android.content.Context;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(arrayList.get(position).getImage_url()).into(holder.img);
        holder.name.setText(arrayList.get(position).getName());
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
