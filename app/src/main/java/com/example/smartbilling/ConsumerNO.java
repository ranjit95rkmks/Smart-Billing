package com.example.smartbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class ConsumerNO extends AppCompatActivity {

    private Button conSearch, conLedegr, conAdd;


    EditText get_bookno, get_acco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Consumer");

        get_bookno = findViewById(R.id.booknoc);
        get_acco = findViewById(R.id.accnoc);
        conSearch = findViewById(R.id.con_search);
        conLedegr = findViewById(R.id.con_ledger);
        conAdd = findViewById(R.id.con_add);

        String bookNo = get_bookno.getText().toString();
        String accNo = get_acco.getText().toString();


        conSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (false) {

                    Intent intent = new Intent(getApplicationContext(), ConsumerDetails.class);
                    intent.putExtra("book_no", 101);
                    intent.putExtra("acc_no", 1001);
                    startActivity(intent);

                   // showBookData(WEB_API+"&acco="+accno+"&book="+bookno);
                    Log.d("consumer", accNo +" book "+ bookNo);

                   // Toast.makeText(Consumer.this, "ok", Toast.LENGTH_SHORT).show();


                } else {
                    Log.d("consumer", accNo +" book "+ bookNo);
                    // Show error message if credentials are invalid
                    Toast.makeText(getApplicationContext(), bookNo+"Invalid email or password "+accNo, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}