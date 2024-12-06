package com.example.smartbilling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    CardView user_card;

    TextView _name, _role, _id;

    String name, role, id, phone;
    RelativeLayout bookList, reding_posting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        _name = findViewById(R.id.username);
        _role = findViewById(R.id.post);
        _id = findViewById(R.id.userid);

        name = getIntent().getStringExtra("name");
        role = getIntent().getStringExtra("role");
        id = getIntent().getStringExtra("id");
        phone = getIntent().getStringExtra("phone");


        _name.setText(name);
        _id.setText(id+""+phone);
        _name.setText(name);
        _name.setText(name);



        reding_posting = findViewById(R.id.readingpost);
        bookList = findViewById(R.id.book);
        bookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consumer.class);
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
                Intent intent = new Intent(MainActivity.this, FindBookActivity.class);
                startActivity(intent);
            }
        });

    }
}