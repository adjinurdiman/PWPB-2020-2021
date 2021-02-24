package com.adjinurdiman.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerviewAdapter extends
        RecyclerView.Adapter<RecyclerviewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<NoteModel> listNote;
    public RecyclerviewAdapter(Context context, List<NoteModel>

            listNote,OnUserClickListener listener) {
        this.context=context;
        this.listNote=listNote;
        this.listener=listener;
    }
    public interface OnUserClickListener{
        void onUserClick(NoteModel currentNote, String action);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view=
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(view);
        return userViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        final NoteModel currentNote=listNote.get(position);
        holder.ctxtName.setText(currentNote.getJudul());
        holder.ctxtAge.setText(currentNote.getDeskripsi()+"");
        holder.txtdate.setText(currentNote.getDate());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentNote,"Edit");
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentNote,"Delete");
            }
        });
    }
    @Override
    public int getItemCount() {
        return listNote.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView ctxtAge,ctxtName, txtdate;
        ImageView imgDelete,imgEdit;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtAge=itemView.findViewById(R.id.ctxtAge);
            ctxtName=itemView.findViewById(R.id.ctxtName);
            imgDelete=itemView.findViewById(R.id.imgdelete);
            imgEdit=itemView.findViewById(R.id.imgedit);
            txtdate=itemView.findViewById(R.id.txtDate);
        }
    }

}
