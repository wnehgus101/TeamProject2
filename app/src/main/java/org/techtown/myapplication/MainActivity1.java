package org.techtown.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity1 extends AppCompatActivity {

    CounselorFragment counselorFragment;
    QuestionnaireFragment questionnaireFragment;
    ScheduleFragment scheduleFragment;
    RecordFragment recordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        counselorFragment = new CounselorFragment();
        questionnaireFragment = new QuestionnaireFragment();
        scheduleFragment = new ScheduleFragment();
        recordFragment = new RecordFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, counselorFragment).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.menu_bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                }
        );
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
