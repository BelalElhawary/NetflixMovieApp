package com.bebo.seriespro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bebo.seriespro.Common.Common;
import com.bebo.seriespro.Interface.IRecyclerItemClickListener;
import com.bebo.seriespro.Module.Episode;
import com.bebo.seriespro.Module.Season;
import com.bebo.seriespro.R;
import com.bebo.seriespro.VideoPlayerActivity;

import java.util.List;

public class MyEpisodeAdapter extends RecyclerView.Adapter<MyEpisodeAdapter.MyViewHolder> {

    Context context;
    List<Episode> episodeList;
    LayoutInflater inflater;

    public MyEpisodeAdapter(Context context, List<Episode> episodeList)
    {
        this.context = context;
        this.episodeList = episodeList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = inflater.inflate(R.layout.episode_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(new StringBuilder("Episode ").append(position+1) .append(" : ").append(episodeList.get(position).Name));

        holder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.selectedEpisodeLink = episodeList.get(position).Link;
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;

        IRecyclerItemClickListener recyclerItemClickListener;
        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) { this.recyclerItemClickListener = recyclerItemClickListener; }
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.episode_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
