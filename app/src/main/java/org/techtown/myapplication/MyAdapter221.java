package org.techtown.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter221 extends RecyclerView.Adapter<MyAdapter221.MyViewHolder> {

    private List<ExpertInfo> expertList;

    public MyAdapter221(List<ExpertInfo> expertList) {
        this.expertList = expertList != null ? expertList : new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_221, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ExpertInfo expert = expertList.get(position);

        holder.introduceTextView.setText("소개" + expert.getIntroduce());
        holder.careerTextView.setText("경력" + expert.getCareer());
        holder.priceTextView.setText("가격" + expert.getPrice());
        holder.careablePetTextView.setText("치료 가능 동물" + expert.getCareablePet());
    }

    @Override
    public int getItemCount() {
        return expertList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView introduceTextView;
        TextView careerTextView;
        TextView priceTextView;
        TextView careablePetTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            introduceTextView = itemView.findViewById(R.id.textView19);
            careerTextView = itemView.findViewById(R.id.textView27);
            priceTextView = itemView.findViewById(R.id.textView30);
            careablePetTextView = itemView.findViewById(R.id.textView28);
        }
    }

    public void addAll(List<ExpertInfo> newList) {
        if (newList != null) {
            expertList.addAll(newList);
            notifyDataSetChanged();
        }
    }
}