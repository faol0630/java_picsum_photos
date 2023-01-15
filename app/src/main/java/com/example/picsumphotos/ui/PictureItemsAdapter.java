package com.example.picsumphotos.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.picsumphotos.R;
import com.example.picsumphotos.data.model.PictureItem;

import java.util.ArrayList;
import java.util.List;

public class PictureItemsAdapter extends RecyclerView.Adapter<PictureItemsAdapter.ViewHolder> {

    private List<PictureItem> pictures = new ArrayList<>();

    //-------------------------------------------------------------
    private final OnClickItemInterface onClickItemInterface;

    public interface OnClickItemInterface {

        void onClickItem(PictureItem pictureItem);

    }

    //-----------------------------------------------------------

    public PictureItemsAdapter(OnClickItemInterface onClickItemInterface) {
        this.onClickItemInterface = onClickItemInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvItemId.setText(pictures.get(position).getId());
        holder.tvItemAuthor.setText(pictures.get(position).getAuthor());
        Glide.with(holder.itemView.getContext()).
                load(pictures.get(position).getImage_url()).into(holder.ivItem);
        //click on each item of the recyclerView
        holder.itemView.getRootView().setOnClickListener(v -> onClickItemInterface.onClickItem(pictures.get(position)));
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPictures(List<PictureItem> pictures){
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemId;

        TextView tvItemAuthor;

        ImageView ivItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemId = itemView.findViewById(R.id.TVItemId);
            tvItemAuthor = itemView.findViewById(R.id.TVItemAuthor);
            ivItem = itemView.findViewById(R.id.IVitem);

        }
    }
}
