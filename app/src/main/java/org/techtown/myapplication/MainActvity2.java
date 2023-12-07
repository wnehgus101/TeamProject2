package org.techtown.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActvity2 extends AppCompatActivity {


    CounselorListFragment counselorListFragment;
    MylistFragment mylistFragment;
    ScheduledoctorFragment scheduledoctorFragment;
    MedicalrecordsFragment medicalrecordsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        counselorListFragment = new CounselorListFragment();
        mylistFragment = new MylistFragment();
        scheduledoctorFragment = new ScheduledoctorFragment();
        medicalrecordsFragment = new MedicalrecordsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container2, counselorListFragment).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.menu_bottom);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.tab1) {
                            Toast.makeText(getApplicationContext(), "첫번째 탭 선택됨", Toast.LENGTH_LONG).show();
                            replaceFragment(counselorListFragment);
                            return true;
                        } else if (itemId == R.id.tab2) {
                            Toast.makeText(getApplicationContext(), "두번째 탭 선택됨", Toast.LENGTH_LONG).show();
                            replaceFragment(mylistFragment);
                            return true;
                        } else if (itemId == R.id.tab3) {
                            Toast.makeText(getApplicationContext(), "세번째 탭 선택됨", Toast.LENGTH_LONG).show();
                            replaceFragment(scheduledoctorFragment);
                            return true;
                        } else if (itemId == R.id.tab4) {
                            Toast.makeText(getApplicationContext(), "네번째 탭 선택됨", Toast.LENGTH_LONG).show();
                            replaceFragment(medicalrecordsFragment);
                            return true;
                        }

                        return false;
                    }
                }
        );
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container2, fragment).commit();
    }
}
