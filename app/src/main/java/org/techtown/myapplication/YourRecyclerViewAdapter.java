package org.techtown.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class YourRecyclerViewAdapter extends RecyclerView.Adapter<YourRecyclerViewAdapter.YourViewHolder> {

    private final List<ExpertDataModel> dataList;
    private static ItemClickListener itemClickListener;

    public YourRecyclerViewAdapter(List<ExpertDataModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public YourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // YourViewHolder 객체를 생성하고 반환하는 코드
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_conunselor, parent, false);
        return new YourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YourViewHolder holder, int position) {
        ExpertDataModel data = dataList.get(position);

        // Glide를 사용하여 이미지 로드
        Glide.with(holder.itemView.getContext())
                .load("file://" + data.getImagePath())
                .override(Target.SIZE_ORIGINAL)
                .fitCenter()
                .into(holder.imageView);

        holder.textView36.setText(data.getName());
        holder.textView37.setText("소개: " + data.getIntroduce());
        holder.textView38.setText("경력: " + data.getCareer());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (itemClickListener != null && clickedPosition != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(clickedPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        int verticalSpaceHeight = recyclerView.getResources().getDimensionPixelSize(R.dimen.vertical_space);
        RecyclerViewDecoration itemDecoration = new RecyclerViewDecoration(verticalSpaceHeight);
        recyclerView.addItemDecoration(itemDecoration);
    }

    // YourViewHolder 클래스 정의
    public static class YourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView36;
        TextView textView37;
        TextView textView38;

        public YourViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView6);
            textView36 = itemView.findViewById(R.id.textView36);
            textView37 = itemView.findViewById(R.id.textView37);
            textView38 = itemView.findViewById(R.id.textView38);

            // 추가된 코드: 항목 클릭 이벤트 설정
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
