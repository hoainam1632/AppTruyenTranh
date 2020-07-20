package com.example.apptruyentranh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.DetailActiviy;
import com.example.apptruyentranh.Model.List;
import com.example.apptruyentranh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder>  {
    private Context mcontext;
    private ArrayList<List> mList;

    public listAdapter(Context mcontext, ArrayList<List> mList) {
        this.mcontext = mcontext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final List myList = mList.get(position);
            holder.txtTitle.setText(myList.getTitle());
            holder.txtCategory.setText(myList.getCategory());
            holder.txtNewChapter.setText(myList.getNewChapter());

            Picasso.get().load(myList.getImg()).into(holder.img);

        holder.itemSreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, DetailActiviy.class);
                intent.putExtra("id", myList.getId());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void searchList(ArrayList<List> newList){
        mList = newList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtCategory, txtNewChapter;
        ImageView img;
        CardView itemSreach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title_list);
            txtCategory = itemView.findViewById(R.id.txt_theloai_list);
            txtNewChapter = itemView.findViewById(R.id.txt_newChapter_list);
            img = itemView.findViewById(R.id.img_list);
            itemSreach = itemView.findViewById(R.id.item_list);
        }
    }

}
