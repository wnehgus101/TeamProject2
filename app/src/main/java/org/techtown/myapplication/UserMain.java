package org.techtown.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserMain extends AppCompatActivity {

    CounselorFragment counselorFragment;
    QuestionnaireFragment questionnaireFragment;
    ScheduleFragment scheduleFragment;
    RecordFragment recordFragment;

    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);

        int userNumber = getIntent().getIntExtra("user_number", -1);

        counselorFragment = new CounselorFragment();
        questionnaireFragment = new QuestionnaireFragment();
        scheduleFragment = new ScheduleFragment();
        recordFragment = new RecordFragment();
        dataBaseHandler = new DataBaseHandler(this);

        String userName = dataBaseHandler.getNameByNumber(userNumber);

        Bundle bundle = new Bundle();
        bundle.putInt("user_number", userNumber);
        bundle.putString("userName", userName);
        counselorFragment.setArguments(bundle);
        questionnaireFragment.setArguments(bundle);
        scheduleFragment.setArguments(bundle);
        recordFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, counselorFragment).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.menu_bottom_navigation);
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.tab1) {
                    Toast.makeText(getApplicationContext(), "첫번째 탭 선택됨", Toast.LENGTH_LONG).show();
                    replaceFragment(counselorFragment);
                    return true;
                } else if (itemId == R.id.tab2) {
                    Toast.makeText(getApplicationContext(), "두번째 탭 선택됨", Toast.LENGTH_LONG).show();
                    replaceFragment(questionnaireFragment);
                    return true;
                } else if (itemId == R.id.tab3) {
                    Toast.makeText(getApplicationContext(), "세번째 탭 선택됨", Toast.LENGTH_LONG).show();
                    replaceFragment(scheduleFragment);
                    return true;
                } else if (itemId == R.id.tab4) {
                    Toast.makeText(getApplicationContext(), "네번째 탭 선택됨", Toast.LENGTH_LONG).show();
                    replaceFragment(recordFragment);
                    return true;
                }

                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
