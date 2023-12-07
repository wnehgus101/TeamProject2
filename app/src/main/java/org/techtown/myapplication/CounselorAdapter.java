package org.techtown.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CounselorAdapter extends RecyclerView.Adapter<CounselorAdapter.CounselorViewHolder> {

    private List<Counselor> counselorList;

    public CounselorAdapter(List<Counselor> counselorList) {
        this.counselorList = counselorList;
    }

    @NonNull
    @Override
    public CounselorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_counselor, parent, false);
        return new CounselorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounselorViewHolder holder, int position) {
        Counselor counselor = counselorList.get(position);

        // 상담사 정보 설정
        holder.imageView.setImageResource(R.drawable.sample_avatar); // 적절한 이미지 리소스로 대체
        holder.nameTextView.setText(counselor.getName());
        holder.locationTextView.setText("병원 위치: " + counselor.getLocation());
        holder.experienceTextView.setText("경력: " + counselor.getExperience());
    }

    @Override
    public int getItemCount() {
        return counselorList.size();
    }

    static class CounselorViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, locationTextView, experienceTextView;

        public CounselorViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView6);
            nameTextView = itemView.findViewById(R.id.textView36);
            locationTextView = itemView.findViewById(R.id.textView37);
            experienceTextView = itemView.findViewById(R.id.textView38);
        }
    }
}

