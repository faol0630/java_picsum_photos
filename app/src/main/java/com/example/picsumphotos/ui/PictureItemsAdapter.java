package com.example.picsumphotos.ui;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;

//usar aca el modelo neutro(PictureItem).No entity ni DTO.

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


//    public void UpdatePictures(List<PictureItem> newPictures){
//        pictures.clear();
//        pictures.addAll(newPictures);
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.bind(pictures.get(position));
        holder.tvItemId.setText(pictures.get(position).getId());
        holder.tvItemAuthor.setText(pictures.get(position).getAuthor());
        Glide.with(holder.itemView.getContext()).
                load(pictures.get(position).getImage_url()).into(holder.ivItem);
        //click en cada item del recyclerView
        holder.itemView.getRootView().setOnClickListener(v -> onClickItemInterface.onClickItem(pictures.get(position)));
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public void setPictures(List<PictureItem> pictures){
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.TVItemId)
        TextView tvItemId;
//
        @BindView(R.id.TVItemAuthor)
        TextView tvItemAuthor;
//
        @BindView(R.id.IVitem)
        ImageView ivItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            tvItemId = itemView.findViewById(R.id.TVItemId);
//            tvItemAuthor = itemView.findViewById(R.id.TVItemAuthor);
//            ivItem = itemView.findViewById(R.id.IVitem);
        }
    }
}
