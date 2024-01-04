package com.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    private EditText edName,edAddress,edContact, edPincode;
    private Button buttonBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        edName = findViewById(R.id.editTextLabtTestBookFullName);
        edAddress = findViewById(R.id.editTextLabTestBookAddres);
        edContact = findViewById(R.id.editTextLabTestBookContactNumber);
        edPincode = findViewById(R.id.editTextLabTestBookPincode);
        buttonBook = findViewById(R.id.buttonLabTestBook);
        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        edName.setText("chinu");
        edAddress.setText("Bagudi");
        edPincode.setText("756045");
        edContact.setText("6370342926");
        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonBook.setElevation(20.0f);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");


                // Check if the price array is not null and has the expected length
                if (price != null && price.length == 2) {
                    Database db = new Database(getApplicationContext(), "orderplace", null, 1);
                    db.addOrder(username, edName.getText().toString(), edAddress.getText().toString(), edContact.getText().toString(), Integer.parseInt(edPincode.getText().toString()), date, time, Float.parseFloat(price[1]), "lab");
                    db.removeItemCart(username, "lab");
                    Toast.makeText(LabTestBookActivity.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    // Handle the case where the price array is null or has unexpected length
                    Toast.makeText(LabTestBookActivity.this, "Error processing price", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}