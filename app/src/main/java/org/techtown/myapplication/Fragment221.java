package org.techtown.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class Fragment221 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_221, container, false);

        // 예시: TextView 초기화 및 텍스트 설정
        TextView introTextView = view.findViewById(R.id.textView19);
        introTextView.setText("한줄 소개: 이곳에 프로필 소개를 표시합니다.");

        // 추가적인 초기화 및 동작 수행

        return view;
    }
}
