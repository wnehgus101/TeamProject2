package org.techtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CounselorFragment extends Fragment {
    private static final String ARG_USER_NUMBER = "user_number";
    private int userNumber;

    private RecyclerView recyclerView;
    private YourRecyclerViewAdapter adapter;
    private List<ExpertDataModel> dataModelList;
    private DataBaseHandler dbHandler;

    public CounselorFragment() {
        // 필수 생성자
    }

    public static CounselorFragment newInstance(int userNumber) {
        CounselorFragment fragment = new CounselorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USER_NUMBER, userNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userNumber = getArguments().getInt(ARG_USER_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counselor, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataModelList = new ArrayList<>();
        dbHandler = new DataBaseHandler(getActivity()); // 데이터베이스 핸들러 초기화
        adapter = new YourRecyclerViewAdapter(dataModelList);  // 어댑터 생성 및 데이터 전달
        recyclerView.setAdapter(adapter);

        adapter.setItemClickListener(new YourRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ExpertDataModel clickedData = dataModelList.get(position);

                // 액티비티로 전환하기 위해 Intent 생성
                Intent intent = new Intent(getActivity(), DoctorList.class);
                intent.putExtra("expertName", clickedData.getName());
                intent.putExtra("user_number", userNumber);

                // 액티비티 시작
                startActivity(intent);
            }
        });

        fetchDataFromDatabase();  // 데이터베이스에서 데이터 가져오기

        return view;
    }

    private void fetchDataFromDatabase() {
        List<ExpertDataModel> expertsWithRoleZero = dbHandler.getExpertsWithRoleZero();

        dataModelList.addAll(expertsWithRoleZero);

        // adapter 객체 초기화 후에 데이터 설정
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}


