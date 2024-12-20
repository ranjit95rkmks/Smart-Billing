package com.example.smartbilling;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static com.example.smartbilling.accessoris.MyApiLink.WEBAPIFinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Objects;


public class ReadingPosting extends AppCompatActivity {

   // private final String WEB_API = "https://script.google.com/macros/s/AKfycbxq2EVE2P1aYvJMbrxCgFjNNUIo8b-jU2279zEEVgV3YDTslVZALxWocQLefyD8QXq5xw/exec?action=posting";
    private final String WEB_API = WEBAPIFinal+"action=posting";


    TextView name, fname, phone, bookAccno, preReading, address;


    Button readingPosting, printing;

    EditText currentReading, billMonth, billYear;

    boolean printstatus = false;

    Switch aswitch;

    String _bookno, _accno, _name, _fname, _phone, _address,current_Reading, bill_Month, bill_Year,
            lpc_date, print_tgtaka, pirint_nb, vat, lpc, total_amount, other_amount, gtotal, print_use,
            issueDate, dueDate, print_tarrif, pre_reading, discon_date, print_billnumber,
            print_account, print_book, print_month, print_year,
            demand, demand_charg;
    LinearLayout items;
    private static final int CREATE_FILE = 1;


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
        bill_Month = getIntent().getStringExtra("data_billmonth");
        bill_Year = getIntent().getStringExtra("data_billyear");

        bookAccno.setText(_bookno + "/" + _accno);
        name.setText(_name);
        fname.setText(_fname);
        phone.setText(_phone);
        address.setText(_address);


        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        // int currentYear = calendar.get(Calendar.YEAR);

          billYear.setText(bill_Year);
          billMonth.setText(bill_Month);


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

                Log.v("Switch State=", "" + isChecked);
                if (isChecked)
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

                if (isValidCredentials(bill_Month, bill_Year, current_Reading)) {
                    String url = WEB_API + "&account=" + _accno + "&book=" + _bookno + "&bill_month=" + bill_Month + "&bill_year=" + bill_Year + "&c_reading_unit=" + current_Reading + "&other_amount=0";

                    print_account = _accno;
                    print_book = _bookno;
                    print_month = bill_Month;
                    print_year = bill_Year;

                    _accno = null;
                    _bookno = null;
                    bill_Year = null;
                    bill_Month = null;

                    Log.d("myUrl", url);
                    postingData(url);

                    if (!printstatus) {
                        printing.setVisibility(View.VISIBLE);
                        //printstatus = true;
                    }

                    String url2 = WEBAPIFinal+"action=billcon&book="+print_book+"&accno="+print_account+"&month="+ print_month + "&year=" + print_year;
                    Log.d("printing", url2);
                    billShowCorrent(url2);

                    readingPosting.setVisibility(view.GONE);

                    ViewDialog.dataLoadingWait(ReadingPosting.this, "ডাটা সংরক্ষন হয়েছে", "পরবর্তী ধাপে এগিয়ে যান");
                } else {

                    printing.setVisibility(View.GONE);
                    Log.d("kkkkk", "&account=" + _accno + "&book=" + _bookno + "&bill_month=" + bill_Month + "&bill_year=" + bill_Year + "&c_reading_unit=" + current_Reading + "&other_amount=0");
                }

            }
        });

        printing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                doWebViewPrint();


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

                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    Log.e("name2", "I am hrere");
                    //Toast.makeText(.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ewwor", Objects.requireNonNull(error.getMessage()));
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        //ViewDialog.dataLoadingWait(ReadingPosting.this, "Bill processing ", "Printing prossing");
    }

    private WebView mWebView;

    private void doWebViewPrint() {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(ReadingPosting.this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "page finished loading " + url);
                createWebPrintJob(view);
                mWebView = null;
            }
        });

        // Generate an HTML document on the fly:
        String htmlDocument = "<html><body><h1>Pally bidhut samimit</h1><p>Ellectric Bill Copy</p>" +
                "<p>Bill month: "+print_month+"</p>" +
                "<p>Bill year: "+print_year+"</p>" +
                "<p>Consumer name: "+_name+"</p>" +
                "<p>Consumer Phone: "+_phone+"</p>" +
                "<p>Tariff: "+print_tarrif+"</p>" +
                "<p>Bill Number: "+print_billnumber+"</p>" +
                "<p>Current Reading: "+current_Reading+"</p>" +
                "<p>Previous Reading: "+pre_reading+"</p>" +
                "<p>Use's Reading: "+print_use+"</p>" +
                "<p>Issue Date: "+issueDate+"</p>" +
                "<p>LPC date: "+lpc_date+"</p>" +
                "<p>Last Date: "+discon_date+"</p>" +
                "<p>Need Balance: "+pirint_nb+"</p>" +
                "<p>Vat: "+vat+"</p>" +
                "<p>LPC charge: "+lpc+"</p>" +
                "<p>Demand charge: "+demand_charg+"</p>" +
                "<p>Total Amount: "+total_amount+"</p>" +
                "<p>Other Amount: "+other_amount+"</p>" +
                "<p> Total Amount:"+gtotal+"</p>" +
                "</body></html>";
        webView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);

        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        // to the PrintManager
        mWebView = webView;
    }

    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) ReadingPosting.this
                .getSystemService(Context.PRINT_SERVICE);

        String jobName = getString(R.string.app_name) + " Document";

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);

        // Create a print job with name and adapter instance
         printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

        // Save the job object for later status checking
       // printJobs.add(printJob);
    }


    private void billShowCorrent(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws RuntimeException {
                try {
                    JSONArray jsonArray= response.getJSONArray("items");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        print_use = jsonObject.getString("dif_unit");
                        print_tarrif = jsonObject.getString("tarrif");
                        print_tgtaka = jsonObject.getString("gtotal_taka");
                        issueDate = jsonObject.getString("issue_date");
                        dueDate = jsonObject.getString("lpc_date");
                        print_billnumber =jsonObject.getString("bill_number");
                        demand =jsonObject.getString("demand");
                        pirint_nb =jsonObject.getString("nb");
                        vat =jsonObject.getString("vat");
                        lpc =jsonObject.getString("lpc");
                        total_amount =jsonObject.getString("total_amount");
                        other_amount =jsonObject.getString("other_amount");
                        gtotal =jsonObject.getString("gtotal_taka");
                        demand_charg =jsonObject.getString("demand_charge");
                        pre_reading =jsonObject.getString("pre_reading_unit");
                        discon_date =jsonObject.getString("discon_date");
                        lpc_date =jsonObject.getString("lpc_date");

                    }

                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    Log.e("name2", "I am hrere");
                    Toast.makeText(ReadingPosting.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), ""+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }


}