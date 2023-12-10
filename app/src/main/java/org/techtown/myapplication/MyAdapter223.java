package org.techtown.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter223 extends RecyclerView.Adapter<MyAdapter223.MyViewHolder>{
    private List<ReviewInfo> reviewList;

    public MyAdapter223(List<ReviewInfo> reviewList) {
        this.reviewList = reviewList != null ? reviewList : new ArrayList<>();
    }

    @NonNull
    @Override
    public MyAdapter223.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_223, parent, false);
        return new MyAdapter223.MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter223.MyViewHolder holder, int position) {
        ReviewInfo review = reviewList.get(position);

        holder.reviewTextView.setText("리뷰" + review.getReview());
        holder.reviewerTextView.setText("이름" + review.getReviewer());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView reviewTextView;
        TextView reviewerTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewerTextView = itemView.findViewById(R.id.textView31);
            reviewTextView = itemView.findViewById(R.id.textView12);
        }
    }

    public void addAll(List<ReviewInfo> newList) {
        if (newList != null) {
            reviewList.addAll(newList);
            notifyDataSetChanged();
        }
    }
}
