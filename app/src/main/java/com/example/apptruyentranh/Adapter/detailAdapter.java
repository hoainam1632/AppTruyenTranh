package com.example.apptruyentranh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.MangaActivity;
import com.example.apptruyentranh.Model.Detail;
import com.example.apptruyentranh.R;

import java.util.ArrayList;

public class detailAdapter extends RecyclerView.Adapter<detailAdapter.ViewHolder> {
    private ArrayList<Detail> mdetail;
    private Context mcontext;

    public detailAdapter(ArrayList<Detail> mdetail, Context mcontext) {
        this.mdetail = mdetail;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_layout_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Detail detail = mdetail.get(position);
        holder.chapterDetail.setText(detail.getChapter());

        holder.itemDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, MangaActivity.class);
                intent.putExtra("id", detail.getId());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView  chapterDetail;
        LinearLayout itemDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterDetail = itemView.findViewById(R.id.txtChapter_detail);
            itemDetail = itemView.findViewById(R.id.item_detail);
        }
    }
}
