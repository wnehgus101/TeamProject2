package org.techtown.myapplication;

import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;

public class UserJoin extends AppCompatActivity {
    private DataBaseHandler db_handler;
    private String image_path;

    private String filePath;

    private static final int PERMISSION_REQUEST_CODE = 456;

    private ActivityResultLauncher<Intent> documentPickerLauncher;

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

    private void openDocumentPicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        // documentPickerLauncher를 사용하여 액티비티를 시작
        documentPickerLauncher.launch(intent);
    }
    private String getPathFromUri(Uri uri) {
        filePath = null;
        if (DocumentsContract.isDocumentUri(this, uri)) {
            filePath = getDataColumn(uri, null, null);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            filePath = getDataColumn(uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            filePath = getDataColumn(uri, null, null);
        }

        Toast.makeText(this, "파일 경로: " + filePath, Toast.LENGTH_SHORT).show();
        return filePath;
    }

    private String getDataColumn(Uri uri, String selection, String[] selectionArgs) {
        String column = "_data";
        String[] projection = {column};
        try (Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        }
        return uri.getPath(); // 파일 경로를 찾을 수 없을 때 기본적으로 URI의 경로를 반환
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_join);

        documentPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri uri = data.getData();
                            if (uri != null) {
                                filePath = getPathFromUri(uri);
                                if (filePath != null) {
                                    Toast.makeText(this, "파일 경로: " + filePath, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(this, "파일 경로를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });

        //사용 객체
        EditText user_id = findViewById(R.id.join_id);
        EditText user_password = findViewById(R.id.join_password);
        EditText user_pw_ck = findViewById(R.id.join_pwck);
        EditText user_name = findViewById(R.id.join_name);
        EditText user_contact = findViewById(R.id.join_Phone);
        EditText user_gender = findViewById(R.id.join_sex);
        EditText pet_name = findViewById(R.id.career);
        EditText pet_age = findViewById(R.id.one_line);
        EditText pet_spices = findViewById(R.id.amount);
        EditText pet_gender = findViewById(R.id.available_pets);

        Button dup_button = findViewById(R.id.check_button);
        Button choose_pic_button = findViewById(R.id.check_button2);
        Button choose_file_button = findViewById(R.id.check_Insert);
        Button confirm = findViewById(R.id.complete);

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
                    Toast.makeText(UserJoin.this, "아이디 중복", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserJoin.this, "존재하지 않는 아이디 입니다.", Toast.LENGTH_SHORT).show();
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

        choose_file_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDocumentPicker();
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
                String p_name = pet_name.getText().toString().trim();
                String p_age = pet_age.getText().toString().trim();
                String p_gender = pet_gender.getText().toString().trim();
                String p_spices = pet_spices.getText().toString().trim();

                pw_check[0] = passwordCheck(usr_pw,usr_pw_ck);

                if (pw_check[0] == 1 && !dup[0]){
                    long result_1 = db_handler.addUserData(usr_id, usr_pw, usr_name, usr_contact, usr_gender, user_type);

                    int user_number = getLatestUserNumber();

                    if (result_1 != -1){
                        long result_2 = db_handler.addPetData(image_path, filePath, p_name, p_age, p_gender, p_spices, user_number);

                        if (result_2 != -1){
                            Toast.makeText(UserJoin.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UserJoin.this, ActivityLogIn.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(UserJoin.this, "추가정보 입력 실패", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UserJoin.this, "기본정보 입력 실패", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserJoin.this, "비밀번호가 일치 하지 않거나, 아이디 중복입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
