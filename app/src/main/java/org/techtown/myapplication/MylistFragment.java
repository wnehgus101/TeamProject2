package org.techtown.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;


public class MylistFragment extends Fragment {

    private TabLayout tabLayout;
    private FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mylist, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        frameLayout = view.findViewById(R.id.frameLayout4);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        loadFragment(new Fragment221());
                        break;
                    case 1:
                        loadFragment(new Fragment222());
                        break;
                    case 2:
                        loadFragment(new Fragment223());
                        break;
                    // Add more cases if you have additional tabs
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // Set the default fragment to be displayed when the activity is created
        loadFragment(new Fragment221());

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout4, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}