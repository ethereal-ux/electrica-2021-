package com.example.electrica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;


public class date_booking extends dashboard {
    int i=0;
    int f=1;
    //public static int g=1;
    String glob;
    String[] date_array= {"-1","-1"};
    HashMap <String,String> date_book_array = new HashMap<>();
    public static String date_selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_booking);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Button d1 = findViewById(R.id.date1);
        Button d2 = findViewById(R.id.date2);
        int g=3;

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("date");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for( DataSnapshot val : snapshot.getChildren()){


                            date_array[i]=val.getKey().toString();
                            date_book_array.put(date_array[i],val.getValue().toString());
                            //Log.i(getApplicationContext().toString(),date_array[i]);
                            i++;


                        }
                d1.setText((date_array[0]));
                d2.setText((date_array[1]));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void date_book(View view) {
            i=0;
            Button button_date_selected;
            button_date_selected = (Button) findViewById(view.getId());
             date_selected=button_date_selected.getText().toString();
            // Log.i(getApplicationContext().toString(),date_selectedabc);
            //  g=5;
             Intent intent = new Intent(getApplicationContext(), time_booking.class);
            startActivity(intent);

    }



}