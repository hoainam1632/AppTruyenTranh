package com.example.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptruyentranh.Adapter.categoryAdapter;
import com.example.apptruyentranh.Adapter.listAdapter;
import com.example.apptruyentranh.Model.Category;
import com.example.apptruyentranh.Model.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ArrayList<Category> categories;
    private categoryAdapter adapter;
    private RecyclerView recyclerView;
    private String url ="http://192.168.43.122/Framework-Laravel/WebTruyenTranh/json/theloai";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.rcv_category);
        categories = new ArrayList<>();
        getJSON(url);
        adapter = new categoryAdapter(this, categories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                                categories.add(new Category(
                                        object.getString("id"),
                                        object.getString("TenTheLoai")
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
                        Toast.makeText(CategoryActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void searchActivity(View view) {
        Intent intent = new Intent(CategoryActivity.this ,SearchActivity.class);
        startActivity(intent);
        finish();
    }

    public void HomeActivity(View view) {
        Intent intent = new Intent(CategoryActivity.this ,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
