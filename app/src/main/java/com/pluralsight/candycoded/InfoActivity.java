package com.pluralsight.candycoded;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvAddress, tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvAddress = findViewById(R.id.text_view_address);
        tvPhone = findViewById(R.id.text_view_phone);

        tvAddress.setOnClickListener(this);
        tvPhone.setOnClickListener(this);

        Uri uri = Uri.parse("android.resource://com.pluralsight.candycoded/" + R.drawable.store_front);
        ImageView candyStoreImageView = (ImageView) findViewById(R.id.image_view_candy_store);
        Picasso.with(this).
                load(uri).
                into(candyStoreImageView);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.text_view_address) {

        } else if (v.getId() == R.id.text_view_phone) {
            String phoneNumber = tvPhone.getText().toString().trim();
            Uri phoneUri = Uri.parse(String.format("tel:%s", phoneNumber));
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
            if (phoneIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(phoneIntent);
            } else {
                //No suitable application to handle action
                Toast.makeText(getApplicationContext(), R.string.no_suitable_activity, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void createMapIntent(View view){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=618 E South St Orlando, FL 32801"));
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            //No suitable application to handle action
            Toast.makeText(getApplicationContext(), R.string.no_suitable_activity, Toast.LENGTH_SHORT).show();
        }
    }
}
