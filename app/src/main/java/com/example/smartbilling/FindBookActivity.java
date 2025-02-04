package com.example.smartbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindBookActivity extends AppCompatActivity {
    private EditText etBookTitle, etBillMonth, etBillYear;
    private Button btnFindBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_book);


        etBookTitle = findViewById(R.id.etBookTitle);
        btnFindBook = findViewById(R.id.btnFindBook);
        etBillMonth = findViewById(R.id.etbillmonth);
        etBillYear = findViewById(R.id.etbillyear);

        btnFindBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookTitle = etBookTitle.getText().toString();
                String billMonth = etBillMonth.getText().toString();
                String billYear = etBillYear.getText().toString();

                Intent intent = new Intent(getApplicationContext(), BookList.class);
                intent.putExtra("bookID",bookTitle);
                intent.putExtra("billmonth",billMonth);
                intent.putExtra("billyear",billYear);
                startActivity(intent);

                Toast.makeText(FindBookActivity.this, "Searching for " + bookTitle, Toast.LENGTH_SHORT).show();
            }
        });
    }
}