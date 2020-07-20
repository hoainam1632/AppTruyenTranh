package com.example.apptruyentranh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptruyentranh.Adapter.detailAdapter;
import com.example.apptruyentranh.Model.Detail;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActiviy extends AppCompatActivity {
    private ArrayList<Detail> mdetails;
    private detailAdapter adapterDetail;
    private RecyclerView recyclerView;
    private ImageView imgDetail;
    private TextView nameDetail;
    private  TextView txtTitle;
    private TextView  authorDetail;
    private TextView  txtDate;
    private  Button btnBack;
    protected String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activiy);
        recyclerView = (RecyclerView) findViewById(R.id.rcvDetail);


        imgDetail = findViewById(R.id.img_detail);
        nameDetail = findViewById(R.id.txtname_detail);
        txtTitle = findViewById(R.id.txt_title_detail);
        txtDate = findViewById(R.id.txt_datetime_detail);
        authorDetail = findViewById(R.id.txtAuthor_detail);

        btnBack = findViewById(R.id.btn_back_detail);


        mdetails = new ArrayList<>();
        adapterDetail = new detailAdapter(mdetails, this);
        recyclerView.setAdapter(adapterDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getdata();
    }

    private void getdata() {
        Intent intent = getIntent();
        if (intent.hasExtra("id")){
             String id = intent.getStringExtra("id");
            String url = "http://192.168.43.122/Framework-Laravel/WebTruyenTranh/json/chitiet/"+id;
            getJSON(url);
        }else Toast.makeText(this, "No has data", Toast.LENGTH_SHORT).show();
    }
    protected void getJSON(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++ ){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                img = object.getString("img");
                                txtTitle.setText(object.getString("TenTruyen"));
                                nameDetail.setText(object.getString("TenTruyen"));
                                authorDetail.setText(object.getString("TacGia"));
                                txtDate.setText(object.getString("updated"));
                                Picasso.get().load(img).resize(400,600).into(imgDetail);
                                mdetails.add(new Detail(
                                        object.getString("id"),
                                        "Chapter: "+object.getString("chapter")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterDetail.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActiviy.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void BackHome(View view) {
        finish();
    }


}
