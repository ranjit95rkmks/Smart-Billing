package com.example.smartbilling;

import static com.example.smartbilling.R.id.current_c;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Consumer extends AppCompatActivity {


    EditText c_book, c_acc;

    Button con_search, con_bill_laser;

    String book, acc;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_laser);

        c_acc = findViewById(current_c);
        c_book =findViewById(R.id.current_b);

        con_search = findViewById(R.id.con_details);
        con_bill_laser = findViewById(R.id.con_bill_laser);

        con_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book = c_book.getText().toString();
                acc = c_acc.getText().toString();

                if(book != null && !book.equals("") && acc != null && !acc.equals("")){
                    Intent intent = new Intent(getApplicationContext(), ConsumerDetails.class);
                    intent.putExtra("bookno",book);
                    intent.putExtra("accno", acc);
                    startActivity(intent);

                }else{Toast.makeText(getApplicationContext(), "book and account number are required", Toast.LENGTH_SHORT).show();}

            }
        });


        con_bill_laser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book = c_book.getText().toString();
                acc = c_acc.getText().toString();

                if(book != null && !book.equals("") && acc != null && !acc.equals("")){
                    Intent intent = new Intent(getApplicationContext(), ConsumerBillDetails.class);
                    intent.putExtra("bookno",book);
                    intent.putExtra("accno", acc);
                    startActivity(intent);

                }else{Toast.makeText(getApplicationContext(), "book and account number are required", Toast.LENGTH_SHORT).show();}

            }
        });




    }
}