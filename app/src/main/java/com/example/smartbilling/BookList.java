package com.example.smartbilling;

import static com.example.smartbilling.accessoris.MyApiLink.WEBAPIFinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartbilling.accessoris.ViewDialog;
import com.example.smartbilling.adapter.BookAdapter;
import com.example.smartbilling.model.BookData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookList extends AppCompatActivity {


    private final String WEB_API = WEBAPIFinal+"action=accno";

    RecyclerView recyclerView;
    //ActivityBookBinding binding;

    ArrayList<String> id, name, fname, mname, book, account,phone, village, thana, distric, tarrif, demand, entry_data, date_of_brith, status;

    BookAdapter bookAdapter;

    String billmonth, billyear;
    ArrayList<BookData> bookData;

    BookData bookHolder;
    //String[] listTopics = {"500","510","520","530","540","45","44","34","423","434","333","500","510","520","530","540","45","44","34","423","434","333"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Book List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String bookid = getIntent().getStringExtra("bookID");
        billyear = getIntent().getStringExtra("billyear");
        billmonth = getIntent().getStringExtra("billmonth");

        setContentView(R.layout.activity_book_list);
        recyclerView = findViewById(R.id.myview);
        id = new ArrayList<>();
        name = new ArrayList<>();
        fname = new ArrayList<>();
        mname = new ArrayList<>();
        book = new ArrayList<>();
        account = new ArrayList<>();
        phone = new ArrayList<>();
        village = new ArrayList<>();
        thana = new ArrayList<>();
        distric = new ArrayList<>();
        tarrif = new ArrayList<>();
        demand = new ArrayList<>();
        entry_data = new ArrayList<>();
        date_of_brith = new ArrayList<>();
        status = new ArrayList<>();
        bookData = new ArrayList<>();



        showBookData(bookid);

    }

    private void showBookData(String bookid) {
        String url = WEB_API+"&book="+bookid;

        Log.d("myUrl", url);

       // Log.e()
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws RuntimeException {
                try {
                    JSONArray jsonArray= response.getJSONArray("items");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id.add(jsonObject.getString("id"));
                        name.add(jsonObject.getString("name"));
                        fname.add(jsonObject.getString("fname"));
                        mname.add("myMothor");
                        book.add(jsonObject.getString("book"));
                        account.add(jsonObject.getString("account"));
                        phone.add(jsonObject.getString("phone"));
                        village.add(jsonObject.getString("village"));
                        thana.add(jsonObject.getString("thana"));
                        distric.add(jsonObject.getString("distric"));
                        tarrif.add(jsonObject.getString("tarrif"));
                        demand.add(jsonObject.getString("demand"));
                        entry_data.add(jsonObject.getString("entry_data"));
                        date_of_brith.add(jsonObject.getString("date_of_brith"));
                        status.add(jsonObject.getString("status"));

                        bookHolder = new BookData(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("fname"),
                                "joy", jsonObject.getString("book"), jsonObject.getString("account"), jsonObject.getString("phone"),
                                jsonObject.getString("tarrif"), jsonObject.getString("demand"), jsonObject.getString("status"), jsonObject.getString("village")+","+jsonObject.getString("thana"));

                        bookData.add(bookHolder);

                        //bookAdapter.addModel(bookData);
                        //recyclerView.setHasFixedSize(true);
                        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookList.this);
                        //bookAdapter =new BookAdapter(bookData);
                       // recyclerView.setLayoutManager(layoutManager);
                       // recyclerView.setAdapter(bookAdapter);
                    }
                    setUpRecyclerView();

                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    Log.e("name2", "I am hrere");
                    Toast.makeText(BookList.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BookList.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        ViewDialog.dataLoadingWait(BookList.this, "Data Processing", "Please Wait");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_for_country_list, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
             //   bookAdapter.getFilter().filter(newText);
                bookAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.myview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        bookAdapter = new BookAdapter(bookData);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAdapter);

        //myAdapter.setOnItemClickListener(new ShowCountryAdapter.OnItemClickListener() {
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(int position) {

                BookData my_response = bookData.get(position);

                Intent iintent = new Intent(getApplicationContext(), ReadingPosting.class);
                iintent.putExtra("data_name", my_response.getName());
                iintent.putExtra("data_fname", my_response.getFname());
                iintent.putExtra("data_book", my_response.getBook());
                iintent.putExtra("data_account", my_response.getAccount());
                iintent.putExtra("data_phone", my_response.getPhone());
                iintent.putExtra("data_address", my_response.getAddress());
                iintent.putExtra("data_billyear", billyear);
                iintent.putExtra("data_billmonth", billmonth);
                startActivity(iintent);
            }
        });
    }
}