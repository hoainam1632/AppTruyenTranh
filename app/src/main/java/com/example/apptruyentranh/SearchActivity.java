package com.example.apptruyentranh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

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

public class SearchActivity extends AppCompatActivity {
    private ArrayList<List> mlist;
    private listAdapter adapter;
    private RecyclerView recyclerView;
    private String url ="http://192.168.43.122/Framework-Laravel/WebTruyenTranh/json/home";

    EditText search ;
    Button btnCategory, btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.RCV_search);
        btnCategory = findViewById(R.id.btn_category_search);
        btnHome = findViewById(R.id.btn_home_search);

        mlist = new ArrayList<>();
        getJSON(url);
        adapter = new listAdapter(this, mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        search = (EditText) findViewById(R.id.edit_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }
 private void  filter(String text){
        ArrayList<List> filterList = new ArrayList<>();
        for (List list: mlist){
            if (list.getTitle().toLowerCase().contains(text.toLowerCase())){
                filterList.add(list);
            }
        }
        adapter.searchList(filterList);
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
                        Toast.makeText(SearchActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }


    public void HomeActivity(View view) {
        Intent intent = new Intent(SearchActivity.this , HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void Category(View view) {
        Intent intent = new Intent(SearchActivity.this , CategoryActivity.class);
        startActivity(intent);
        finish();
    }
}
