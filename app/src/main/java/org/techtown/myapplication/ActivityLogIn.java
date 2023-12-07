package org.techtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogIn extends AppCompatActivity {
    private DataBaseHandler db_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText id = findViewById(R.id.login_id);
        EditText pw = findViewById(R.id.login_password);

        Button login = findViewById(R.id.login_button);
        Button join = findViewById(R.id.join_button);

        db_handler = new DataBaseHandler(getApplicationContext());

        final boolean[] id_check = new boolean[1];
        final boolean[] pw_check = new boolean[1];

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type;
                int number;

                String ID = id.getText().toString().trim();
                String PW = pw.getText().toString().trim();

                id_check[0] = db_handler.isIDExists(ID);
                pw_check[0] = db_handler.isPWExists(PW);

                if(!id_check[0] && !pw_check[0]){
                    type = db_handler.getRoleById(ID);
                    number = db_handler.getNumberById(ID);

                    if(type == 0){
                        //Intent intent = new Intent(ActivityLogIn.this, );
                    } else {
                        //Intent intent = new Intent(ActivityLogIn,this, );
                    }
                } else {
                    Toast.makeText(ActivityLogIn.this, "회원정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
