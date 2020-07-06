package com.example.parva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.parva.Adapter.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //=============Variables=================

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    String url1, url2, url3;
    ArrayList<Details> detailsArrayList;
    Details obj;
    Boolean loadMore;
    View header;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        loadMore = false;

        //===================HOOKS========================

        recyclerView = findViewById(R.id.recyclerView);
        // recyclerView.setHasFixedSize(true);
        final GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        header = LayoutInflater.from(this).inflate(
                R.layout.footer_rv, recyclerView, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return myAdapter.isFooter(position) ? manager.getSpanCount() : 1;
            }
        });
        detailsArrayList = new ArrayList<>();
        myAdapter = new MyAdapter(this, detailsArrayList);
        recyclerView.setAdapter(myAdapter);

        //====================URLs========================

        url1 = "http://demo0405353.mockable.io/posts/1";
        url2 = "http://demo0405353.mockable.io/posts/2";
        url3 = "http://demo0405353.mockable.io/posts/3";

        //===================Functions==================
        process1();

    }

    private void process1() {
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.i("extract", "success");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        //  Log.i("post_author_name", object.getString("post_author_name"));
                        // Log.i("post_type_title", object.getString("post_type_title"));
                        //Log.i("community_title", object.getString("community_title"));
                        obj = new Details();
                        obj.setPost_author_name(object.getString("post_author_name"));
                        obj.setPost_description(object.getString("post_description"));
                        obj.setPost_title(object.getString("post_type_title"));
                        obj.setPost_thumbnail(object.getString("post_thumbnail"));
                        obj.setCommunity_title(object.getString("community_title"));
                        detailsArrayList.add(obj);
                    }
                    myAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
               // Log.i("Result", "Error");
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void loadData() {
        if (count > 2) {
            return;
        }
        String tempURL="";
        if (count == 1) {
            tempURL = url2;
            count++;
        } else if (count == 2) {
            tempURL = url3;
            count++;
        }
        RequestQueue queue = Volley.newRequestQueue(this);
       // Log.i("extract", "success");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, tempURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        //  Log.i("post_author_name", object.getString("post_author_name"));
                        // Log.i("post_type_title", object.getString("post_type_title"));
                        //Log.i("community_title", object.getString("community_title"));
                        obj = new Details();
                        obj.setPost_author_name(object.getString("post_author_name"));
                        obj.setPost_description(object.getString("post_description"));
                        obj.setPost_title(object.getString("post_type_title"));
                        obj.setCommunity_title(object.getString("community_title"));
                        obj.setPost_thumbnail(object.getString("post_thumbnail"));
                        detailsArrayList.add(obj);
                    }
                    myAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(this,"Network Error",Toast.LENGTH_SHORT).show();
                Log.i("Result", "Error");
            }
        });
        queue.add(jsonObjectRequest);
    }


}



