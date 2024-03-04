package com.example.smartbilling;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Splash_Activity extends AppCompatActivity {
    ImageView myImage;
    TextView logo, description;
    Animation topAnim, bottomAnim;
    boolean backbtnPress = false;
    ProgressBar progressBar;
    ProgressBar progressBarS;

    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


       // myImage = findViewById(R.id.imageView);
      //  logo = findViewById(R.id.textView);
      //  description = findViewById(R.id.textView2);
        progressBar = findViewById(R.id.progressBar);


        setProgressValue(progress);


      //  topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
       // bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        //Set animation to elements
       // myImage.setAnimation(topAnim);
        // logo.setAnimation(bottomAnim);
       // description.setAnimation(bottomAnim);
        gotoMain();
    }

    public void gotoMain(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
               // if(isConnected()){
                 //   if(!backbtnPress)
//                    {
                        Intent intent = new Intent(Splash_Activity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
  //                  }
//                }
//               // else{
//                    AlertDialog.Builder builder = new AlertDialog.Builder(Splash_Activity.this);
//                    builder.setTitle("নেট কানেকশন সেবা বন্ধ আছে");
//                    builder.setMessage("দয়া করে আপনার ইন্টারনেট কানেকশন চালু করে আবার চেষ্টা করুন?");
//                    builder.setPositiveButton("ওকে", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            isConnected();
//                            gotoMain();
//                            dialog.dismiss();
//                        }
//                    });
//                    AlertDialog alert = builder.create();
//                    alert.show();
//                }
            }
        },3000);
    }


    @Override
    public void onBackPressed() {
        backbtnPress = true;
        super.onBackPressed();
    }


    public boolean isConnected() {
        boolean connected = false;
        try {
           // ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
           // NetworkInfo nInfo = cm.getActiveNetworkInfo();
           // connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            //Toast.makeText(this, "com", Toast.LENGTH_SHORT).show();
           // return connected;
        } catch (Exception e) {
            // Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    private void setProgressValue(final int progress) {

        // set the progress
        progressBar.setProgress(progress);
        //progressBar.secondaryProgress(60);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setProgressValue(progress + 10);
                }
            });
            thread.start();
        }
}