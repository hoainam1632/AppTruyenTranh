package com.example.apptruyentranh;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptruyentranh.Adapter.homeAdapter;
import com.example.apptruyentranh.Model.Home;
import com.example.apptruyentranh.slider.SliderAdapterExample;
import com.example.apptruyentranh.slider.SliderItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    SliderView sliderView;
    Button btnSearch, btnCategory;
    private SliderAdapterExample sliderAdapter;
    private ArrayList<Home> mHome;
    private homeAdapter hAdapter;
    private RecyclerView rcvHome;
    protected  String url = "http://192.168.43.122/Framework-Laravel/WebTruyenTranh/json/home";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnSearch = findViewById(R.id.btn_search);
        btnCategory = findViewById(R.id.btn_category);
        //slider image
        sliderView = findViewById(R.id.imageSlider);
        sliderAdapter = new SliderAdapterExample(this);
        // data
        rcvHome= (RecyclerView) findViewById(R.id.rcvHome);
        mHome = new ArrayList<>();
        hAdapter = new homeAdapter(mHome, this);
        rcvHome.setAdapter(hAdapter);
        rcvHome.setLayoutManager(new GridLayoutManager(this,2));
        getJSON(url);
    }
    protected void SliderImage(){

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
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
                                if (i < 3)
                                {
                                    sliderAdapter.addItem(new SliderItem(object.getString("TenTruyen"),
                                            object.getString("img"),
                                            object.getString("id")));
                                }
                                mHome.add(new Home(
                                        object.getString("id"),
                                        object.getString("TenTruyen"),
                                        object.getString("img")

                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        hAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
        SliderImage();
    }

    public void searchActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    public void Category(View view) {
        Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
}
