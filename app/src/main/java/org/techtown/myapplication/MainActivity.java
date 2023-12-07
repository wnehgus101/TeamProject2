package org.techtown.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button proSignUpTestButton = findViewById(R.id.expert);
        proSignUpTestButton.setOnClickListener(new View.OnClickListener() { //전문가 회원가입 전환 버튼
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, DoctorJoin.class);
                int user_types = 0;
                intent.putExtra("user_type", user_types);
                startActivity(intent);
            }
        });

        Button userSignUpTestButton = findViewById(R.id.user);
        userSignUpTestButton.setOnClickListener(new View.OnClickListener() { //이용자 회원가입 전환 버튼
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserJoin.class);
                int user_types = 1;
                intent.putExtra("user_type", user_types);
                startActivity(intent);
            }
        });

        Button logInButton = findViewById(R.id.login);
        logInButton.setOnClickListener(new View.OnClickListener() { //로그인 화면 전환 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityLogIn.class);
                startActivity(intent);
            }
        });
    }
}