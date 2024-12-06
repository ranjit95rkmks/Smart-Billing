package com.example.smartbilling;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartbilling.accessoris.ViewDialog;
import com.example.smartbilling.model.BookData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class ReadingPosting extends AppCompatActivity {

    private final String WEB_API = "https://script.google.com/macros/s/AKfycbxq2EVE2P1aYvJMbrxCgFjNNUIo8b-jU2279zEEVgV3YDTslVZALxWocQLefyD8QXq5xw/exec?action=posting";


    TextView name, fname, phone, bookAccno, preReading, address;

    Button readingPosting, printing;

    EditText currentReading, billMonth, billYear;

    boolean printstatus = false;

    Switch aswitch;

    String _bookno, _accno, _name, _fname, _phone, _address,current_Reading, bill_Month, bill_Year;
    LinearLayout items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("রিডিং পোস্টিং");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_reading_posting);

       // receiver_msg = findViewById(R.id.book_acc);
        //BookData getdata = getIntent().getParcelableExtra("data_id");
       // BookData bdResponse = (BookData) getdata.getSerializableExtra("data_1");
       // receiver_msg.setText(str);

        //Intent data = getIntent().getExtras();
       // BookData bookdata = (BookData) (data != null ? data.getParcelable("data_id") : null);



        readingPosting = findViewById(R.id.btnposting);
        printing = findViewById(R.id.print);

        aswitch = findViewById(R.id.switch1);
        items = findViewById(R.id.itemsse);

        name = findViewById(R.id.name);
        fname = findViewById(R.id.father);
        phone = findViewById(R.id.phone);
        bookAccno = findViewById(R.id.book_acc);
        preReading = findViewById(R.id.pre_reading); // ok
        address = findViewById(R.id.village);

        currentReading = findViewById(R.id.current_reading);
        billMonth = findViewById(R.id.bill_month);
        billYear = findViewById(R.id.bill_year);

        _name = getIntent().getStringExtra("data_name");
        _fname = getIntent().getStringExtra("data_fname");
        _phone = getIntent().getStringExtra("data_phone");
        _address = getIntent().getStringExtra("data_address");
        _bookno = getIntent().getStringExtra("data_book");
        _accno = getIntent().getStringExtra("data_account");

        bookAccno.setText(_bookno+"/"+_accno);
        name.setText(_name);
        fname.setText(_fname);
        phone.setText(_phone);
        address.setText(_address);






        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
       // int currentYear = calendar.get(Calendar.YEAR);

       // billYear.setText(currentYear);
//        billMonth.setText(currentMonth);



        printing.setVisibility(View.GONE);

        if (aswitch.isChecked()) {
            items.setVisibility(View.VISIBLE);
        } else {
            items.setVisibility(View.GONE);
        }

        // on below line we are adding check change listener for our switch.
        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.v("Switch State=", ""+isChecked);
                if(isChecked)
                    items.setVisibility(View.VISIBLE);
                else
                    items.setVisibility(View.GONE);
            }

        });


        readingPosting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                current_Reading = currentReading.getText().toString();
                bill_Year = billYear.getText().toString();
                bill_Month = billMonth.getText().toString();

                if(isValidCredentials(bill_Month, bill_Year, current_Reading)){
                    String url = WEB_API+"&account="+_accno+"&book="+_bookno+"&bill_month="+bill_Month+"&bill_year="+bill_Year+"&c_reading_unit="+current_Reading+"&other_amount=0";

                    Log.d("myUrl", url);
                    postingData(url);

                    ViewDialog.dataLoadingWait(ReadingPosting.this, "ডাটা সংরক্ষন হয়েছে" ,"পরবর্তী ধাপে এগিয়ে যান");
                }
                else{
                    Log.d("kkkkk","&account="+_accno+"&book="+_bookno+"&bill_month="+bill_Month+"&bill_year="+bill_Year+"&c_reading_unit="+current_Reading+"&other_amount=0");
                }



                // printstatus = true;
                if(!printstatus){
                    printing.setVisibility(View.VISIBLE);
                    //printstatus = true;
                }
            }
        });

        printing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1p2kfBweDgOMM2VC8BazDrQ7Shz-t6zeT/view?usp=sharing"));
                startActivity(i);
                ViewDialog.dataLoadingWait(ReadingPosting.this, "প্রিন্ট করুন" ,"" +
                        "এগিয়ে যান");
            }
        });

    }


    private boolean isValidCredentials(String billM, String billY, String cReading) {
        // Add your authentication logic here
        // For simplicity, let's perform basic validation
        return billM != null && cReading != null && !billM.isEmpty() && billY != null && !billY.isEmpty() && !cReading.isEmpty();
    }

    private void postingData(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws RuntimeException {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");

                } catch (
                        JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    Log.e("name2", "I am hrere");
                    //Toast.makeText(.this, e.getMessage(),Toast.LENGTH_SHORT).show();
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