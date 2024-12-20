package com.example.smartbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartbilling.accessoris.ViewDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsumerDetails extends AppCompatActivity {

    private final String WEB_API = "https://script.google.com/macros/s/AKfycbxq2EVE2P1aYvJMbrxCgFjNNUIo8b-jU2279zEEVgV3YDTslVZALxWocQLefyD8QXq5xw/exec?action=eachconsumer";
    private final String WEB_API1 = "https://script.google.com/macros/s/AKfycbxq2EVE2P1aYvJMbrxCgFjNNUIo8b-jU2279zEEVgV3YDTslVZALxWocQLefyD8QXq5xw/exec?action=eachconsumer&acco=1001&book=101";
    String id, name, fname, mname, book, account,phone, village, thana, distric, tarrif, demand, entry_data, date_of_brith, status;
    TextView _name, _faname, _book, _account, _phone, _village, _thana, _tarrif;

    String id_book, id_acco;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_details);

        _name = findViewById(R.id.name);
        _faname = findViewById(R.id.fname);
        _book = findViewById(R.id.bookcom);
        _phone = findViewById(R.id.phone);
        _tarrif = findViewById(R.id.tarrif);
        _account = findViewById(R.id.acc);

        id_book = getIntent().getStringExtra("bookno");
        id_acco = getIntent().getStringExtra("accno");

        ViewDialog.dataLoadingWait(this, "Data Loding..." ,"Please wait");


        String  url =WEB_API+"&acco="+id_acco+"&book="+id_book;

        Log.d("url",url);

        showBookData(url);
    }

    private void showBookData(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws RuntimeException {
                try {
                    JSONArray jsonArray= response.getJSONArray("items");
                   for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id = jsonObject.getString("id");
                        name= jsonObject.getString("name");
                        fname= jsonObject.getString("fname");
                        mname= "myMothor";
                        book= jsonObject.getString("book");
                        account = jsonObject.getString("account");
                        phone = jsonObject.getString("phone");
                        village= jsonObject.getString("village");
                        thana= jsonObject.getString("thana");
                        distric= jsonObject.getString("distric");
                        tarrif= jsonObject.getString("tarrif");
                        demand= jsonObject.getString("demand");
                        entry_data= jsonObject.getString("entry_data");
                        date_of_brith= jsonObject.getString("date_of_brith");
                        status= jsonObject.getString("status");

                        Log.d("joybangla", "I am hrere"+name);

                       _name.setText("Name:    "+name);
                       _faname.setText("Father Name:    "+fname);
                       _book.setText("Book No:    "+book+"  Account No:"+ account);
                       _account.setText("Address: "+village+", Thana: "+ thana);
                       _phone.setText("Phone :"+phone);
                       _tarrif.setText("Tarrif: "+tarrif+", Status: "+ status);

                    }

                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    Log.e("name2", "I am hrere");
                    Toast.makeText(ConsumerDetails.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }
}