package com.codesample.wdjmemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    interface OnItemClick{
        public void onItemClick(int position, Memo memo);
    }
    private List<Memo> data;
    private OnItemClick listener;

    public MemoAdapter(OnItemClick listener){
        this.listener=listener;
    }

    public void setData(List<Memo> data){
        this.data =data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
        return new MemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoViewHolder holder, int position) {
        Memo g = data.get(position);

        holder.date.setText(g.time);
        holder.title.setText(g.title);

        holder.itemView.setOnClickListener(v->{
            listener.onItemClick(position,g);
        }
        );
    }

    @Override
    public int getItemCount() {
       return data==null? 0 :data.size();
    }



    class MemoViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        public MemoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            date = itemView.findViewById(R.id.textViewDate);
        }
    }
}
