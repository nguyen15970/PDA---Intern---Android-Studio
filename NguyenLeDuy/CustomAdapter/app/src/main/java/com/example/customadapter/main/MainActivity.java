package com.example.customadapter.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customadapter.Order.CreateOrderActivity;
import com.example.customadapter.Product.ListProductActivity;
import com.example.customadapter.R;

public class MainActivity extends AppCompatActivity {
    Button btnDssp , btnTdh, btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDssp = (Button) findViewById(R.id.btnDssp);
        btnTdh=(Button) findViewById(R.id.btnTDH);
        btnExit=(Button) findViewById(R.id.btnExit);


        btnDssp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , ListProductActivity.class);
                startActivity(intent);
            }
        });
        btnTdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, CreateOrderActivity.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                ////
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();

            }
        });
    }
}
