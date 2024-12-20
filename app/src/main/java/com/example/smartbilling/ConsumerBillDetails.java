package com.example.smartbilling;

import static com.example.smartbilling.accessoris.MyApiLink.WEBAPIFinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.smartbilling.adapter.BillShowAdapter;
import com.example.smartbilling.adapter.BookAdapter;
import com.example.smartbilling.model.BillShowClass;
import com.example.smartbilling.model.BookData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsumerBillDetails extends AppCompatActivity {

    ArrayList <BillShowClass> billShowArray;

    BillShowClass eachBill;

    BillShowAdapter billShowAdapter;

    String __name, __fname, __book, __account, __phone;

    String id_book, id_acco;

    TextView _name, _faname, _account, _phone;

    private final String WEB_API = WEBAPIFinal+"action=conleaser";

   ArrayList<String> id, bookno, account, billmonth, billyear, c_reading, nb, demand_charge, gTotal_taka, bill_type, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_bill_details);

        _name = findViewById(R.id.name);
        _faname = findViewById(R.id.father);
        _account = findViewById(R.id.account);
        _phone = findViewById(R.id.phone);

        id = new ArrayList<>();
        bookno = new ArrayList<>();
        account = new ArrayList<>();
        billmonth = new ArrayList<>();
        billyear = new ArrayList<>();
        c_reading = new ArrayList<>();
        nb = new ArrayList<>();
        demand_charge = new ArrayList<>();
        gTotal_taka = new ArrayList<>();
        bill_type = new ArrayList<>();
        status = new ArrayList<>();
        billShowArray = new ArrayList<>();

        id_book = getIntent().getStringExtra("bookno");
        id_acco = getIntent().getStringExtra("accno");

        cunsumerDataShow();
        consumerBillDetails(id_book, id_acco);

    }

    private void consumerBillDetails(String bookid, String accono) {
        String url = WEB_API+"&book="+bookid+"&accno="+accono;

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
                        account.add(jsonObject.getString("account"));
                        bookno.add(jsonObject.getString("book"));

                        billmonth.add(jsonObject.getString("bill_month"));
                        billyear.add(jsonObject.getString("bill_year"));
                        c_reading.add(jsonObject.getString("c_reading_unit"));
                        nb.add(jsonObject.getString("nb"));
                        demand_charge.add(jsonObject.getString("demand_charge"));
                        gTotal_taka.add(jsonObject.getString("gtotal_taka"));
                        bill_type.add(jsonObject.getString("bill_type"));
                        status.add(jsonObject.getString("status"));

                       // eachBill = new BillShowClass(id, bookno, account, billmonth, billyear, c_reading, nb, demand_charge, gTotal_taka, bill_type, status);

                        eachBill = new BillShowClass(jsonObject.getString("id"),jsonObject.getString("book"),jsonObject.getString("account"),
                                jsonObject.getString("bill_month"), jsonObject.getString("bill_year"), jsonObject.getString("c_reading_unit"), jsonObject.getString("nb"),
                                jsonObject.getString("demand_charge"), jsonObject.getString("gtotal_taka"), jsonObject.getString("bill_type"), jsonObject.getString("status"));

                        billShowArray.add(eachBill);

                    }
                    setUpRecyclerView();

                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    Log.e("name2", "I am hrere");
                    Toast.makeText(ConsumerBillDetails.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ConsumerBillDetails.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        ViewDialog.dataLoadingWait(ConsumerBillDetails.this, "Data Processing", "Please Wait");
    }


    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.laser_bill);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        billShowAdapter = new BillShowAdapter(billShowArray);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(billShowAdapter);

        //myAdapter.setOnItemClickListener(new ShowCountryAdapter.OnItemClickListener() {
//        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(int position) {
//
//                BookData my_response = bookData.get(position);
//
//                Intent iintent = new Intent(getApplicationContext(), ReadingPosting.class);
//                iintent.putExtra("data_name", my_response.getName());
//                iintent.putExtra("data_fname", my_response.getFname());
//                iintent.putExtra("data_book", my_response.getBook());
//                iintent.putExtra("data_account", my_response.getAccount());
//                iintent.putExtra("data_phone", my_response.getPhone());
//                iintent.putExtra("data_address", my_response.getAddress());
//                iintent.putExtra("data_billyear", billyear);
//                iintent.putExtra("data_billmonth", billmonth);
//                startActivity(iintent);
//            }
//        });
    }


    private void cunsumerDataShow() {

        String url = WEBAPIFinal+"action=eachconsumer&acco="+id_acco+"&book="+id_book;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws RuntimeException {
                try {
                    JSONArray jsonArray= response.getJSONArray("items");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       // id = jsonObject.getString("id");
                        __name= jsonObject.getString("name");
                        __fname= jsonObject.getString("fname");
                       // mname= "myMothor";
                        __book= jsonObject.getString("book");
                        __account = jsonObject.getString("account");
                        __phone = jsonObject.getString("phone");
                       // village= jsonObject.getString("village");
                       // thana= jsonObject.getString("thana");
                       // distric= jsonObject.getString("distric");
                       // tarrif= jsonObject.getString("tarrif");
                       // demand= jsonObject.getString("demand");
                       // entry_data= jsonObject.getString("entry_data");
                       // date_of_brith= jsonObject.getString("date_of_brith");
                       // status= jsonObject.getString("status");

                       // Log.d("joybangla", "I am hrere"+name);

                        _name.setText(__name);
                        _faname.setText(__fname);
                       // _book.setText("Book No:    "+book+"  Account No:"+ account);
                        _account.setText(__book+"/"+__account);
                        _phone.setText(__phone);


                    }

                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    Log.e("name2", "I am hrere");
                  //  Toast.makeText(ConsumerBillDetails.this, e.getMessage(),Toast.LENGTH_SHORT).show();
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
        //ViewDialog.dataLoadingWait(ConsumerBillDetails.this,"Consumer Data Loading", "Data Loading");
    }

}

//    record['id'] = row[0];
//    record['book'] =row[1];
//    record['account'] = row[2];
//    record['bill_month'] = row[3];
//    record['bill_year'] = row[4];
//    record['c_reading_unit'] = row[5];
//    record['pre_reading_unit'] = row[6];
//    record['dif_unit'] = row[7];
//    record['tarrif'] = row[8];
//    record['demand'] = row[10];
//    record['posting_date'] = row[11];
//    record['issue_date'] = row[12];
//    record['lpc_date'] = row[13];
//    record['discon_date'] = row[14];
//    record['nb'] = row[15];
//    record['vat'] = row[16];
//    record['lpc'] = row[17];
//    record['demand_charge'] = row[18];
//    record['total_amount'] = row[19];
//    record['other_amount'] = row[20];
//    record['gtotal_taka'] = row[21];
//    record['user'] = row[22];
//    record['bill_type'] =row[23];
//    record['bill_number'] = row[24];
//    record['sms_number'] = row[25];