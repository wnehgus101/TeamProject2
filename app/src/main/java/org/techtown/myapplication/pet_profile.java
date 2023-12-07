package org.techtown.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// pet_settings.xml layout 에서 로그아웃, 탈퇴, 프로필 수정 버튼 클릭 시 수행되는 클래스

public class pet_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_settings); // pet_settings.xml 레이아웃 파일

        // 로그아웃 버튼 찾기
        Button logoutButton = findViewById(R.id.button9);
        // 회원탈퇴 버튼 찾기
        Button withdrawButton = findViewById(R.id.button10);
        // 프로필 수정 버튼 찾기
        Button profileButton = findViewById(R.id.newButton);


        // 로그아웃 버튼에 클릭 이벤트 리스너 추가
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그아웃 버튼을 눌렀을 때 실행될 코드

                // Intent를 사용하여 다음 액티비티로 전환
                Intent intent = new Intent(pet_profile.this, ActivityLogIn.class);
                startActivity(intent);

                // 현재 액티비티를 종료하려면 아래 코드를 추가
                // finish();
            }
        });

        // 회원탈퇴 버튼에 클릭 이벤트 리스너 추가
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 탈퇴하기 버튼 클릭 시 호출되는 메서드
                // deleteUser(userId); // 사용자 아이디에 실제 사용자 아이디를 넣어주세요

                // Intent를 사용하여 다음 액티비티로 전환
                Intent intent = new Intent(pet_profile.this, MainActivity.class);
                startActivity(intent);

                // 현재 액티비티를 종료하려면 아래 코드를 추가
                // finish();
            }
        });

        // 프로필 수정 버튼에 클릭 이벤트 리스너 추가
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 프로필 수정 버튼을 눌렀을 때 실행될 코드

                // Intent를 사용하여 다음 액티비티로 전환
                Intent intent = new Intent(pet_profile.this, Pet_profile_settings.class);
                startActivity(intent);

                // 현재 액티비티를 종료하려면 아래 코드를 추가
                // finish();
            }
        });
    }


}