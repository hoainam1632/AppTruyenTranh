package com.example.apptruyentranh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.Model.Manga;
import com.example.apptruyentranh.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class mangaAdapter extends RecyclerView.Adapter<mangaAdapter.ViewHolder> {
    private ArrayList<Manga> mMangas;
    private Context mcontext;

    public mangaAdapter(ArrayList<Manga> mMangas, Context mcontext) {
        this.mMangas = mMangas;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_layout_manga, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Manga manga = mMangas.get(position);
        Picasso.get().load(manga.getImg())
                .into(holder.imgManga);
    }

    @Override
    public int getItemCount() {
        return mMangas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private PhotoView imgManga;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgManga = itemView.findViewById(R.id.img_manga);
        }
    }
}
