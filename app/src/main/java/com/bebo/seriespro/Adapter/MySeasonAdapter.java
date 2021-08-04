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
import com.bebo.seriespro.Module.Season;
import com.bebo.seriespro.Module.Show;
import com.bebo.seriespro.R;
import com.bebo.seriespro.SeasonDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MySeasonAdapter extends RecyclerView.Adapter<MySeasonAdapter.MyViewHolder> {

    Context context;
    List<Season> seasonList;
    LayoutInflater inflater;

    public MySeasonAdapter(Context context, List<Season> seasonList)
    {
        this.context = context;
        this.seasonList = seasonList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = inflater.inflate(R.layout.season_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ep.setText(new StringBuilder("Episodes : ").append(seasonList.get(position).Episodes.size()));
        holder.name.setText(new StringBuilder("Season ").append(position+1).append(" : ").append(seasonList.get(position).Name));

        holder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.seasonIndex = position;
                Intent intent = new Intent(context, SeasonDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seasonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView ep;

        IRecyclerItemClickListener recyclerItemClickListener;
        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) { this.recyclerItemClickListener = recyclerItemClickListener; }
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.season_name);
            ep = (TextView)itemView.findViewById(R.id.season_ep);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
