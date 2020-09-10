package com.adjinurdiman.inputuser;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText bil1;
    private EditText bil2;
    private EditText jumlah;
    private Button btnhasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initEvent();
    }

    private void initUI(){
        bil1 = (EditText) findViewById(R.id.bil1);
        bil2 = (EditText) findViewById(R.id.bil2);
        jumlah = (EditText) findViewById(R.id.jumlah);
        btnhasil = (Button) findViewById(R.id.btnhasil);
    }

    private void initEvent(){

        btnhasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungJumlah();
            }
        });
    }

    private void hitungJumlah(){
        int angka1 = Integer.parseInt(bil1.getText().toString());
        int angka2 = Integer.parseInt(bil2.getText().toString());
        int total = angka1 + angka2;
        jumlah.setText(total+"");
    }
}