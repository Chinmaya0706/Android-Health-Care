package com.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailActivity extends AppCompatActivity {
    TextView tvPackageName,tvTotalCost;
    EditText multiLineEditText;
    Button addToCart,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_detail);
        tvPackageName = findViewById(R.id.textViewPackageName);
        tvTotalCost = findViewById(R.id.textViewTotalCost);
        multiLineEditText = findViewById(R.id.EditTextMultiline);
        back = findViewById(R.id.buttonBackLabTestDetail);
        addToCart = findViewById(R.id.buttonAddToCartLTD);
        multiLineEditText.setKeyListener(null);
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        multiLineEditText.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setElevation(20.0f);
                startActivity(new Intent(getApplicationContext(),LabTestActivity.class));
                finish();
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart.setElevation(20.0f);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkCartItem(username,product)==1) Toast.makeText(getApplicationContext(),"Already exists in the cart",Toast.LENGTH_SHORT).show();
                else{
                    db.addToCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Added to cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailActivity.this,LabTestActivity.class));
                    finish();
                }
            }
        });

    }
}