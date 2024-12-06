package com.example.smartbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class AccountList extends AppCompatActivity {

    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        ListView listView;

        String[] listTopics = {"1500","2510","2520","2530","3540","2245","2244","2234","2423","2434","2333","5200","2511","2521","2531","2541","2242","3445","3336","1424","4128","1333"};

            getSupportActionBar().setTitle("একাউন্ট নাম্বার");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            listView = findViewById(R.id.listView);
            adapter = new ArrayAdapter<String>(AccountList.this, android.R.layout.simple_dropdown_item_1line, listTopics);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                    Intent intent = new Intent(AccountList.this, ReadingPosting.class);
                    intent.putExtra("message_key", "864/" + listTopics[index]);
                    Toast.makeText(AccountList.this, "You have clicked on " + listTopics[index], Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            });
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
                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }
}