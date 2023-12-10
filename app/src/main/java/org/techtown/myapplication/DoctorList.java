package org.techtown.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DoctorList extends AppCompatActivity {
    Fragment221 fragment221;
    Fragment222 fragment222;
    Fragment223 fragment223;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);

        int userNumber = getIntent().getIntExtra("user_number", -1);
        String doctorName = getIntent().getStringExtra("expertName");

        fragment221 = new Fragment221();
        fragment222 = new Fragment222();
        fragment223 = new Fragment223();

        Bundle bundle1 = new Bundle();
        bundle1.putString("expertName", doctorName);
        fragment221.setArguments(bundle1);

        Bundle bundle2 = new Bundle();
        bundle2.putString("expertName", doctorName);
        fragment222.setArguments(bundle2);

        Bundle bundle3 = new Bundle();
        bundle3.putString("expertName", doctorName);
        fragment223.setArguments(bundle3);

        ViewPager2 viewPager = findViewById(R.id.viewPager2);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(this, fragment221, fragment222, fragment223);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(pagerAdapter.getTabTitle(position))
        ).attach();
    }
}
