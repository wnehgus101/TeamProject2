package org.techtown.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DataBaseHandler extends DataBaseHelper{
    private final SQLiteDatabase db;

    //생성자.
    public DataBaseHandler(Context context){
        super(context);
        db = getWritableDatabase();
    }

    //유저 정보 저장 함수
    public long addUserData(String id, String pw, String name, String contact, String gender, int role){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("pw", pw);
        values.put("username", name);
        values.put("contact", contact);
        values.put("gender", gender);
        values.put("role", role);

        return db.insert("user_table", null, values);
    }

    // 사용자 정보 삭제 함수
    public void deleteUser(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("user_table", "id=?", new String[]{id});
    }

    //전문가 정보 추가 저장 함수
    public long addExpertData(String path, String career, String introduce, String price, String careable_pet,int number){
        ContentValues values = new ContentValues();
        values.put("image_path", path);
        values.put("career", career);
        values.put("introduce", introduce);
        values.put("price", price);
        values.put("careable_pet", careable_pet);
        values.put("confirm", 0);
        values.put("user_number", number);

        return db.insert("expert_table", null, values);
    }

    //반려동물 데이터 추가
    public long addPetData(String image_path, String file_path, String pet_name, String pet_age, String pet_gender, String pet_species, int number){
        ContentValues values = new ContentValues();
        values.put("image_path", image_path);
        values.put("file_path", file_path);
        values.put("pet_name", pet_name);
        values.put("pet_age", pet_age);
        values.put("pet_gender", pet_gender);
        values.put("pet_species", pet_species);
        values.put("user_number", number);

        return db.insert("pet_table", null, values);
    }

    //아이디 중복 확인 함수.
    public boolean isIDExists(String id){
        Cursor cursor = db.query("user_table", new String[]{"id"},
                "id=?", new String[]{id}, null, null, null);

        boolean exists = cursor != null && cursor.getCount() > 0;

        if (cursor != null){
            cursor.close();
        }
        return exists;
    }

    public boolean isPWExists(String pw){
        Cursor cursor = db.query("user_table", new String[]{"pw"},
                "id=?", new String[]{pw}, null, null, null);

        boolean exists = cursor != null && cursor.getCount() > 0;

        if (cursor != null){
            cursor.close();
        }
        return exists;
    }

    public int getRoleById(String id) {
        int role = -1; // 기본값

        Cursor cursor = db.query("user_table", new String[]{"role"},
                "id=?", new String[]{id}, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("role");

                    // 커서에서 열을 찾을 수 있는지 확인
                    if (columnIndex != -1) {
                        role = cursor.getInt(columnIndex);
                    }
                }
            } finally {
                cursor.close();
            }
        }

        return role;
    }

    public int getNumberById(String id) {
        int number = -1; // 기본값

        Cursor cursor = db.query("user_table", new String[]{"number"},
                "id=?", new String[]{id}, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("number");

                    // 커서에서 열을 찾을 수 있는지 확인
                    if (columnIndex != -1) {
                        number = cursor.getInt(columnIndex);
                    }
                }
            } finally {
                cursor.close();
            }
        }

        return number;
    }


}
