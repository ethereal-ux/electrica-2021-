package com.example.electrica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class time_booking extends date_booking {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_booking);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        //Log.i(getApplicationContext().toString(),String.valueOf(g));
        Log.i(getApplicationContext().toString(),date_selected);

    }

    public void time_book(View view) {


        Button t1 = findViewById(R.id.time1);
        Button t2 = findViewById(R.id.time2);


        Button btn = (Button) findViewById(view.getId());
        String text = btn.getText().toString();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("date").child(date_selected).child(text);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString().equals("0")){

                    ref.setValue("ananth");
                    t1.setVisibility(View.GONE);
                    t2.setVisibility(View.GONE);
                    ImageView circle=findViewById(R.id.circle);
                    circle.setVisibility(View.VISIBLE);
                    ImageView tick=findViewById(R.id.tick);
                    tick.setVisibility(View.VISIBLE);
                    Drawable drawable = tick.getDrawable();
                    if(drawable instanceof AnimatedVectorDrawableCompat)
                    {
                        AnimatedVectorDrawableCompat avd=(AnimatedVectorDrawableCompat) drawable;
                        avd.start();
                    }
                    else if (drawable instanceof AnimatedVectorDrawable)
                    {
                        AnimatedVectorDrawable avd2=(AnimatedVectorDrawable) drawable;
                        avd2.start();
                    }



                    HandlerThread handlerThread = new HandlerThread("background-thread");
                    handlerThread.start();
                    final Handler handler = new Handler(handlerThread.getLooper());
                    handler.postDelayed(new Runnable() {
                        @Override public void run() {
                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                            startActivity(intent);
                            handlerThread.quitSafely();
                        }
                    }, 3000);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}