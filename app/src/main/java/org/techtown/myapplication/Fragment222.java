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

public class Fragment222 extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter222 adapter;
    private DataBaseHandler dataBaseHandler;
    private List<ExpertInfo> expertList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_222, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyAdapter222(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        dataBaseHandler = new DataBaseHandler(getContext());
        SQLiteDatabase db_handler = dataBaseHandler.getReadableDatabase();

        String userName = getUserNameFromArguments();

        expertList = dataBaseHandler.getDataForFragment222(userName);
        adapter.addAll(expertList);

        return view;
    }

    private String getUserNameFromArguments() {
        if (getArguments() != null) {
            return getArguments().getString("expertName");
        }
        return "";
    }
}
