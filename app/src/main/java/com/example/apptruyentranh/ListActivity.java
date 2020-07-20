package com.example.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptruyentranh.Adapter.listAdapter;
import com.example.apptruyentranh.Model.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<List> mlist;
    private listAdapter adapter;
    private RecyclerView recyclerView;
    Button btnBack;
    TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.rcv_list);
        txtTitle = findViewById(R.id.txt_title_list);
        btnBack = findViewById(R.id.btn_back_list);
        mlist = new ArrayList<>();
        getdata();
        adapter = new listAdapter(this, mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void getdata() {
        Intent intent = getIntent();
        if (intent.hasExtra("id")){
            String id = intent.getStringExtra("id");
            String url = "http://192.168.43.122/Framework-Laravel/WebTruyenTranh/json/theloai/"+id;
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
                                txtTitle.setText(object.getString("TheLoai"));
                                mlist.add(new List(
                                        object.getString("id"),
                                        object.getString("TenTruyen"),
                                        object.getString("img"),
                                        object.getString("TheLoai"),
                                        "Chapter mới nhất: "+object.getString("NewChapter")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void BackCategory(View view) {
        finish();
    }
}
