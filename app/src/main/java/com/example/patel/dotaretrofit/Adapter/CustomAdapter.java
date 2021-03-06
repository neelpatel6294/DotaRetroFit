package com.example.patel.dotaretrofit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patel.dotaretrofit.MainActivity;
import com.example.patel.dotaretrofit.Model.Heroes;
import com.example.patel.dotaretrofit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Heroes> heroData;
    Context context;
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {

        void onListItemClick(Heroes movie);
    }
    public CustomAdapter(MainActivity mainActivity,List<Heroes> heroData, ListItemClickListener listItemClickListener) {
        this.heroData = heroData;
//        this.context = context;
        mOnClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Heroes item = heroData.get(position);
        context = viewHolder.mImage.getContext();
        Picasso.get().load("https://api.opendota.com"
                + item.getImage()).into(viewHolder.mImage);



    }

    @Override
    public int getItemCount() {
        return heroData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Heroes result = heroData.get(adapterPosition);
            mOnClickListener.onListItemClick(result);

        }
    }
}
