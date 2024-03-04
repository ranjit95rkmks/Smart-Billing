package com.example.smartbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BookList extends AppCompatActivity {

    ListView listView;
    String[] listTopics = {"500","510","520","530","540","45","44","34","423","434","333","500","510","520","530","540","45","44","34","423","434","333"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Book List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_book_list);
        listView = findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter<String>(BookList.this, android.R.layout.simple_dropdown_item_1line, listTopics);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Toast.makeText(BookList.this, "You have clicked on " + listTopics[index], Toast.LENGTH_SHORT).show();
            }
        });
    }
}