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
import com.example.apptruyentranh.Adapter.mangaAdapter;
import com.example.apptruyentranh.Model.Detail;
import com.example.apptruyentranh.Model.Manga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MangaActivity extends AppCompatActivity {
    private ArrayList<Manga> mangas;
    private mangaAdapter adapter;
    private RecyclerView recyclerView;
    TextView txtChapter;
    Button btnNextChapter, btnBackChapter;
    String NextChapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga);
        recyclerView = (RecyclerView) findViewById(R.id.rcvManga);
        txtChapter = findViewById(R.id.txt_chapter_manga);
        btnNextChapter = findViewById(R.id.btn_Nextchapter_manga);
        btnBackChapter = findViewById(R.id.btn_Backchapter_manga);
        mangas = new ArrayList<>();
        adapter = new mangaAdapter(mangas, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getdata();

    }
    private void getdata() {
        Intent intent = getIntent();
        if (intent.hasExtra("id")){
             String id = intent.getStringExtra("id");
            String url = "http://192.168.43.122/Framework-Laravel/WebTruyenTranh/json/read/"+id;
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
                                final JSONObject object = response.getJSONObject(i);
                                final String idNext = object.getString("idNext");
                                final String idBack = object.getString("idBack");
                                btnNextChapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(Integer.parseInt(idNext) > 0) {
                                            Intent intent = new Intent(MangaActivity.this, MangaActivity.class);
                                            intent.putExtra("id", "" + idNext);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(MangaActivity.this, "Bạn đang ở cuối chapter", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                btnBackChapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(Integer.parseInt(idBack) > 0) {
                                            Intent intent = new Intent(MangaActivity.this, MangaActivity.class);
                                            intent.putExtra("id", "" + idBack);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(MangaActivity.this, "Bạn đang ở đầu chapter" ,Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                txtChapter.setText("Chapter: "+object.getString("Chapter"));
                                mangas.add(new Manga(
                                        object.getString("img")
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
                        Toast.makeText(MangaActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void BackHome(View view) {
        finish();
    }

}
