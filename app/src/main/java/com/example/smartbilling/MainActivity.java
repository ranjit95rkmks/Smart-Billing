package com.example.smartbilling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {
    CardView user_card;
    RelativeLayout bookList, reding_posting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        reding_posting = findViewById(R.id.readingpost);
        bookList = findViewById(R.id.book);
        bookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookList.class);
                startActivity(intent);
            }
        });

        user_card = findViewById(R.id.usercard);
        user_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class);
                startActivity(intent);
            }
        });


        View.OnClickListener onClickListener = null;
        reding_posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AccountList.class);
                startActivity(intent);
            }
        });

    }
}