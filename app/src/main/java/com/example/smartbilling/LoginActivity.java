package com.example.smartbilling;

import static com.example.smartbilling.accessoris.MyApiLink.WEBAPIFinal;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    String [] userData;
   // private final String WEB_API = "https://script.google.com/macros/s/AKfycbxq2EVE2P1aYvJMbrxCgFjNNUIo8b-jU2279zEEVgV3YDTslVZALxWocQLefyD8QXq5xw/exec?action=login";
    private final String WEB_API = WEBAPIFinal+"action=login";


    String id, name, fname, role, village, phone, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = editTextEmail.getText().toString();
                String _password = editTextPassword.getText().toString();


                if (isValidCredentials(_email, _password)) {
                    String  url =WEB_API+"&email="+_email+"&password="+_password;
                    showBookData(url);
                    // After successful login, navigate to another activity
                } else {
                    // Show error message if credentials are invalid
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String email, String password) {
        // Add your authentication logic here
        // For simplicity, let's perform basic validation
        return email != null && !email.isEmpty() && password != null && !password.isEmpty();
    }

    private void showBookData(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws RuntimeException {

              //  ViewDialog.dataLoadingWait(LoginActivity.this, "Login processing..." ,"Please wait");
                try {
                    JSONArray jsonArray= response.getJSONArray("items");
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        id = jsonObject.getString("id");
                        name= jsonObject.getString("name");
                        fname= jsonObject.getString("fname");
                        role= jsonObject.getString("book");
                        email = jsonObject.getString("email");
                        phone = jsonObject.getString("phone");
                        village= jsonObject.getString("village");
                        password= jsonObject.getString("password");


                        Intent intent =new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("role", role);
                        intent.putExtra("id", id);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                        finish();

                    }

                } catch (JSONException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    Log.e("name2", "I am hrere");
                    Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
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
        ViewDialog.dataLoadingWait(LoginActivity.this, "Login processing..." ,"Please wait");
    }
}
