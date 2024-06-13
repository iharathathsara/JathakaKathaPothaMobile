package com.initezz.jathakakathapotha.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.initezz.jathakakathapotha.R;
import com.initezz.jathakakathapotha.StoryActivity;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {
    private List<String> pdfNames;
    Context context;
    public StoriesAdapter(Context context,List<String> pdfNames){
        this.context=context;
        this.pdfNames=pdfNames;
    }

    public void setFilteredList(Context context,List<String> pdfNames){
        this.context=context;
        this.pdfNames=pdfNames;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.story_name.setText(pdfNames.get(position));
        holder.story_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, StoryActivity.class);
                intent.putExtra("name",pdfNames.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pdfNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView story_name;
        CardView story_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            story_name=itemView.findViewById(R.id.story_name_text);
            story_card=itemView.findViewById(R.id.story_card);
        }
    }
}
