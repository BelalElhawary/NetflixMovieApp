package com.bebo.seriespro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bebo.seriespro.Common.Common;
import com.bebo.seriespro.Interface.IRecyclerItemClickListener;
import com.bebo.seriespro.Module.Show;
import com.bebo.seriespro.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyShowAdapter extends RecyclerView.Adapter<MyShowAdapter.MyViewHolder> {

    Context context;
    List<Show> showList;
    LayoutInflater inflater;

    public MyShowAdapter(Context context, List<Show> showList)
    {
        this.context = context;
        this.showList = showList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = inflater.inflate(R.layout.show_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(showList.get(position).Image).into(holder.image);
        holder.name.setText(showList.get(position).Name);

        holder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.showSelected = showList.get(position);
                Intent intent = new Intent(context, ShowDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageView image;

        IRecyclerItemClickListener recyclerItemClickListener;
        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) { this.recyclerItemClickListener = recyclerItemClickListener; }
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.show_name);
            image = (ImageView)itemView.findViewById(R.id.show_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
