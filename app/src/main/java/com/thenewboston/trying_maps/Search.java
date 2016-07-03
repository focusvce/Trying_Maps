package com.thenewboston.trying_maps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search extends AppCompatActivity {

    EditText editText;
    double lati, longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = (EditText) findViewById(R.id.Search_Box);
    }

    public void s_clicked(View v) {
       /* ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        String place = editText.getText().toString();
        Log.e("ADARSH", place);
        String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + place + "&sensor=true_or_false";

        Toast.makeText(getBaseContext(), "Search Clicked", Toast.LENGTH_LONG).show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.e(TAG,"inside on response");


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("ADARSH", "First object");
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    Log.e("ADARSH", "First array");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    Log.e("ADARSH", "Second Ojbect");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("geometry");
                    Log.e("ADARSH", "Third Object");
                    JSONObject jsonObject3 = jsonObject2.getJSONObject("location");
                    String lat = jsonObject3.getString("lat");
                    Log.e("ADARSH", lat);
                    String lng = jsonObject3.getString("lng");
                    Log.e("ADARSH", lng);
                    lati = Double.parseDouble(lat);
                    longi = Double.parseDouble(lng);
                    SharedPreferences sharedPreferences = getSharedPreferences("lati", MODE_PRIVATE);
                    SharedPreferences sharedPreferences1 = getSharedPreferences("longi", MODE_PRIVATE);
                    sharedPreferences.edit().putString("lat_val", lat).apply();
                    sharedPreferences1.edit().putString("lng_val", lng).apply();
                    Log.e("ADARSH_SHARED", sharedPreferences.getString("lat_val", "Nothing"));
                    Log.e("ADARSH", Double.toString(lati));
                    Log.e("ADARSH", Double.toString(longi));

                    // Log.e("ADARSH", response);
                } catch (JSONException e) {
                    Log.e("ADARSH", "inside catch");
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();

            }
        });
        progressDialog.dismiss();

        requestQueue.add(request);

        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);*/
        String place = editText.getText().toString();
        Intent i = new Intent(this,MapsActivity.class);
        i.putExtra("place",place);
        startActivity(i);
    }
}
