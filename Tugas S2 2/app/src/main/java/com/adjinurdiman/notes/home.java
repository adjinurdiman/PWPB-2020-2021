package com.adjinurdiman.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import java.util.List;

public class home extends AppCompatActivity implements RecyclerviewAdapter.OnUserClickListener {
    private RecyclerView rv_note;
    RecyclerView.LayoutManager layoutManager;
    List<NoteModel> listNotes;
    DatabaseHelper helper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv_note = findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        rv_note.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent toInput = new Intent(home.this, InputNotes.class);
                toInput.putExtra("kondisi", "submit");
                startActivity(toInput);
            }
        });
    }

    private void setRecycle(){
        DatabaseHelper db=new DatabaseHelper(this);
        listNotes=db.selectUserData();
        RecyclerviewAdapter adapter=new RecyclerviewAdapter(context,listNotes,this);
        rv_note.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecycle();
    }

    @Override
    public void onUserClick(NoteModel currentNote, String action) {
        if (action.equals("Edit")){
            Intent toInput = new Intent(home.this, InputNotes.class);
            toInput.putExtra("kondisi", "edit");
            toInput.putExtra("title", currentNote.getJudul());
            toInput.putExtra("desc", currentNote.getDeskripsi());
            toInput.putExtra("id", currentNote.getId());
            startActivity(toInput);
        }if(action.equals("Delete")){
            DatabaseHelper db=new DatabaseHelper(this);
            db.delete(currentNote.getId());
            setRecycle();
        }
    }
}