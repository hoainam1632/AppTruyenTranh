package com.example.apptruyentranh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.DetailActiviy;
import com.example.apptruyentranh.Model.Home;
import com.example.apptruyentranh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.ViewHoder> {
    private ArrayList<Home> mhome;
    private Context mcontext;

    public homeAdapter(ArrayList<Home> mhome, Context mcontext) {
        this.mhome = mhome;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.layout_home, parent, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        final Home home = mhome.get(position);
        holder.txtname.setText(home.getName());
        Picasso.get().load(home.getImg()).into(holder.imgPoster);

        holder.itemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, DetailActiviy.class);
                intent.putExtra("id", home.getId());
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mhome.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private TextView txtname;
        private ImageView imgPoster;
        LinearLayout itemHome;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtnameHome);
            imgPoster = itemView.findViewById(R.id.imgHome);
            itemHome = itemView.findViewById(R.id.item_home);
        }
    }
}
