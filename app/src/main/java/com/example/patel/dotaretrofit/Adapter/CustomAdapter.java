package com.example.patel.dotaretrofit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patel.dotaretrofit.Model.Heroes;
import com.example.patel.dotaretrofit.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Heroes> heroData;
    private Context context;

    public CustomAdapter(List<Heroes> heroData, Context context) {
        this.heroData = heroData;
        this.context = context;
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


        viewHolder.name.setText(item.getName());
        viewHolder.attribute.setText(item.getAttribute());
        viewHolder.attackType.setText(item.getAttackType());

    }

    @Override
    public int getItemCount() {
        return heroData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView name, attribute, attackType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            attribute = itemView.findViewById(R.id.attribute);
            attackType = itemView.findViewById(R.id.attackType);


        }
    }
}
