package com.example.apptruyentranh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.DetailActiviy;
import com.example.apptruyentranh.ListActivity;
import com.example.apptruyentranh.Model.Category;
import com.example.apptruyentranh.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {
   private Context mcontext;
   private ArrayList<Category> mcategory;

    public categoryAdapter(Context mcontext, ArrayList<Category> mcategory) {
        this.mcontext = mcontext;
        this.mcategory = mcategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_layout_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Category category = mcategory.get(position);
            holder.txtCategory.setText(category.getCategory());
            holder.itemCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, ListActivity.class);
                    intent.putExtra("id", category.getId());
                    mcontext.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mcategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategory;
        LinearLayout itemCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategory = itemView.findViewById(R.id.txt_category);
            itemCategory = itemView.findViewById(R.id.item_category);
        }
    }
}
