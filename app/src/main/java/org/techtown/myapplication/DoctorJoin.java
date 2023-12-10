package org.techtown.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;

public class DoctorJoin extends AppCompatActivity {
    private DataBaseHandler db_handler;
    private String image_path;

    private final ActivityResultLauncher<String> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        // 선택된 이미지를 파일로 저장.
                        image_path = saveImageToFile(result);
                    }
                }
            }
    );

    private String saveImageToFile(Uri imageUri) {
        // 이미지 파일을 저장할 디렉토리 생성 (예: 앱 내부 파일 디렉토리)
        File directory = getApplicationContext().getFilesDir();

        // 이미지 파일 생성
        String imageName = "image_" + System.currentTimeMillis() + ".jpg";
        File imageFile = new File(directory, imageName);

        // 이미지 파일 저장
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            OutputStream outputStream = Files.newOutputStream(imageFile.toPath());
            byte[] buffer = new byte[1024];
            int bytesRead;
            while (true) {
                assert inputStream != null;
                if ((bytesRead = inputStream.read(buffer)) == -1) break;
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 저장된 이미지 파일의 경로 반환
        return imageFile.getAbsolutePath();
    }

    private void openGallery() {
        // 갤러리에서 이미지를 선택하기 위한 코드
        pickImageLauncher.launch("image/*");
    }

    //비밀번호 일치 확인
    private int passwordCheck(String og, String ck){
        if(Objects.equals(og, ck)){
            return 1;
        } else {
            return 0;
        }
    }

    //사용자 번호 가져옴.
    private int getLatestUserNumber() {
        SQLiteDatabase db = db_handler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT number FROM user_table ORDER BY number DESC LIMIT 1", null);

        int latestUserNumber = -1;  // 기본값 또는 오류 시 반환할 값

        int columnIndex = cursor.getColumnIndex("number");

        if (columnIndex >= 0) {
            if (cursor.moveToFirst()) {
                latestUserNumber = cursor.getInt(columnIndex);
            }

            // 커서를 닫음
            cursor.close();
        }
        return latestUserNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_join);

        //사용 객체
        EditText user_id = findViewById(R.id.join_id);
        EditText user_password = findViewById(R.id.join_password);
        EditText user_pw_ck = findViewById(R.id.join_pw_ck);
        EditText user_name = findViewById(R.id.join_name);
        EditText user_contact = findViewById(R.id.join_Phone);
        EditText user_gender = findViewById(R.id.join_sex);
        EditText career = findViewById(R.id.career);
        EditText intro = findViewById(R.id.one_line);
        EditText price = findViewById(R.id.amount);
        EditText careable_pet = findViewById(R.id.available_pets);

        Button dup_button = findViewById(R.id.check_button);
        Button choose_pic_button = findViewById(R.id.check_button2);
        Button confirm = findViewById(R.id.button5);

        //전 엑티비티에서 넘겨준 값
        int user_type = getIntent().getIntExtra("user_type", -1);

        //만약에 0or 1이 아니라면 앱 종료됨
        if (user_type != 0 && user_type != 1) {
            Toast.makeText(this, "잘못된 사용자 유형입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        //데이터베이스 객체 생성 및 연결
        db_handler = new DataBaseHandler(getApplicationContext());

        final boolean[] dup = new boolean[1];
        final int[] pw_check = new int[1];

        //아이디 중복 확인
        dup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = user_id.getText().toString().trim();
                dup[0] = db_handler.isIDExists(userId);

                if (dup[0]){
                    Toast.makeText(DoctorJoin.this, "아이디 중복", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DoctorJoin.this, "존재하지 않는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //사진 선택 버튼
        choose_pic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr_id = user_id.getText().toString().trim();
                String usr_pw = user_password.getText().toString().trim();
                String usr_pw_ck = user_pw_ck.getText().toString().trim();
                String usr_name = user_name.getText().toString().trim();
                String usr_contact = user_contact.getText().toString().trim();
                String usr_gender = user_gender.getText().toString().trim();
                String usr_career = career.getText().toString().trim();
                String introduce = intro.getText().toString().trim();
                String pri = price.getText().toString().trim();
                String care_pets = careable_pet.getText().toString().trim();

                String review = "예시";
                String reviewer = "홍길동";

                pw_check[0] = passwordCheck(usr_pw,usr_pw_ck);

                if (pw_check[0] == 1 && !dup[0]){
                    long result_1 = db_handler.addUserData(usr_id, usr_pw, usr_name, usr_contact, usr_gender, user_type);

                    int user_number = getLatestUserNumber();
                    
                    long result_3 = db_handler.addReviewData(review, reviewer, user_number);


                    if (result_1 != -1){
                        long result_2 = db_handler.addExpertData(image_path, usr_career, introduce, pri, care_pets, user_number);

                        if (result_2 != -1){
                            Toast.makeText(DoctorJoin.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DoctorJoin.this, ActivityLogIn.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(DoctorJoin.this, "추가정보 입력 실패", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DoctorJoin.this, "기본정보 입력 실패", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DoctorJoin.this, "비밀번호가 일치 하지 않거나, 아이디 중복입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
