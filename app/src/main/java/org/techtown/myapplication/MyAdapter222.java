package org.techtown.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter222 extends RecyclerView.Adapter<MyAdapter222.MyViewHolder>{
    private List<ExpertInfo> expertList;

    public MyAdapter222(List<ExpertInfo> expertList) {
        this.expertList = expertList != null ? expertList : new ArrayList<>();
    }

    @NonNull
    @Override
    public MyAdapter222.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_222, parent, false);
        return new MyAdapter222.MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return expertList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter222.MyViewHolder holder, int position) {
        ExpertInfo expert = expertList.get(position);

        holder.careerTextView.setText("경력" + expert.getCareer());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView careerTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            careerTextView = itemView.findViewById(R.id.textView32);
        }
    }

    public void addAll(List<ExpertInfo> newList) {
        if (newList != null) {
            expertList.addAll(newList);
            notifyDataSetChanged();
        }
    }
}
