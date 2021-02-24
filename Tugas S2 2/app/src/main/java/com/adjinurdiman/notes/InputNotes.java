package com.adjinurdiman.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputNotes extends AppCompatActivity {
    private String KEY_KONDISI = "kondisi";
    private String KEY_ID = "id";
    private String KEY_TITLE = "title";
    private String KEY_DESC = "desc";
    EditText edtJudul;
    EditText edtDeskripsi;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_notes);
        final NoteModel noteModel = new NoteModel();
        final DatabaseHelper db = new DatabaseHelper(this);

        edtJudul = findViewById(R.id.edtjudul);
        edtDeskripsi = findViewById(R.id.edtdeskripsi);
        btnSubmit = findViewById(R.id.btnsubmit);

        String kondisi = getIntent().getStringExtra(KEY_KONDISI);
        if (kondisi.equals("edit")){
            final int id = getIntent().getIntExtra(KEY_ID, 0);
            edtJudul.setText(getIntent().getStringExtra(KEY_TITLE));
            edtDeskripsi.setText(getIntent().getStringExtra(KEY_DESC));
            btnSubmit.setText("Edit");
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noteModel.setJudul(edtJudul.getText().toString());
                    noteModel.setDeskripsi(edtDeskripsi.getText().toString());
                    db.update(noteModel, id);
                    finish();
                }
            });
        }else{
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noteModel.setJudul(edtJudul.getText().toString());
                    noteModel.setDeskripsi(edtDeskripsi.getText().toString());
                    db.insert(noteModel);
                    finish();
                }
            });
        }
    }
}