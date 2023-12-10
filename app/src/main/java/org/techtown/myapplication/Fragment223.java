package org.techtown.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment223 extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter223 adapter;
    private DataBaseHandler dataBaseHandler;
    private List<ReviewInfo> reviewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_223, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyAdapter223(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        dataBaseHandler = new DataBaseHandler(getContext());
        SQLiteDatabase db_handler = dataBaseHandler.getReadableDatabase();

        String userName = getUserNameFromArguments();

        reviewList = dataBaseHandler.getDataForFragment223(userName);
        adapter.addAll(reviewList);

        return view;
    }

    private String getUserNameFromArguments() {
        if (getArguments() != null) {
            return getArguments().getString("expertName");
        }
        return "";
    }
}
