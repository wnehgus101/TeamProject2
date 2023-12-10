package org.techtown.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseHandler extends DataBaseHelper {
    private final SQLiteDatabase db;

    //생성자.
    public DataBaseHandler(Context context) {
        super(context);
        db = getWritableDatabase();
    }

    //유저 정보 저장 함수
    public long addUserData(String id, String pw, String name, String contact, String gender, int role) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("pw", pw);
        values.put("username", name);
        values.put("contact", contact);
        values.put("gender", gender);
        values.put("role", role);

        return db.insert("user_table", null, values);
    }

    //전문가 정보 추가 저장 함수
    public long addExpertData(String path, String career, String introduce, String price, String careable_pet, int number) {
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
    public long addPetData(String image_path, String file_path, String pet_name, String pet_age, String pet_gender, String pet_species, int number) {
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
    public boolean isIDExists(String id) {
        Cursor cursor = db.query("user_table", new String[]{"id"},
                "id=?", new String[]{id}, null, null, null);

        boolean exists = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }
        return exists;
    }

    public boolean isPWExists(String pw) {
        Cursor cursor = db.query("user_table", new String[]{"pw"},
                "pw=?", new String[]{pw}, null, null, null);

        boolean exists = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
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

    public int getNumberByName(String name) {
        int number = -1; // 기본값

        Cursor cursor = db.query("user_table", new String[]{"number"},
                "username=?", new String[]{name}, null, null, null);

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

    public String getNameByNumber(int number) {
        String name = null; // 기본값

        Cursor cursor = db.query("user_table", new String[]{"username"},
                "number=?", new String[]{String.valueOf(number)}, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("username");

                    // 커서에서 열을 찾을 수 있는지 확인
                    if (columnIndex != -1) {
                        name = cursor.getString(columnIndex);
                    }
                }
            } finally {
                cursor.close();
            }
        }

        return name;
    }

    public List<ExpertDataModel> getExpertsWithRoleZero() {
        List<ExpertDataModel> expertList = new ArrayList<>();

        String query = "SELECT user_table.username, expert_table.image_path, expert_table.introduce, expert_table.career " +
                "FROM user_table " +
                "JOIN expert_table ON user_table.number = expert_table.user_number " +
                "WHERE user_table.role = 0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            try {
                int usernameIndex = cursor.getColumnIndex("username");
                int imagePathIndex = cursor.getColumnIndex("image_path");
                int introduceIndex = cursor.getColumnIndex("introduce");
                int careerIndex = cursor.getColumnIndex("career");

                while (cursor.moveToNext()) {
                    ExpertDataModel expert = new ExpertDataModel();

                    // 각 컬럼의 인덱스가 유효한지 확인
                    if (usernameIndex != -1) {
                        expert.setName(cursor.getString(usernameIndex));
                    }

                    if (imagePathIndex != -1) {
                        expert.setImagePath(cursor.getString(imagePathIndex));
                    }

                    if (introduceIndex != -1) {
                        expert.setIntroduce(cursor.getString(introduceIndex));
                    }

                    if (careerIndex != -1) {
                        expert.setCareer(cursor.getString(careerIndex));
                    }

                    expertList.add(expert);
                }
            } finally {
                cursor.close();
                db.close();
            }
        }

        return expertList;
    }


    public List<ExpertInfo> getDataForFragment221(String userName) {
        List<ExpertInfo> expertList = new ArrayList<>();

        // 데이터베이스에서 데이터를 가져오는 쿼리 작성
        int userNumber = getNumberByName(userName);
        if (userNumber == -1) {
            // 사용자 이름에 해당하는 번호를 찾을 수 없는 경우
            // 오류 처리를 수행하거나 함수 종료
            // 예를 들면, return Collections.emptyList(); 를 사용하여 빈 리스트 반환
            return Collections.emptyList();
        }

        String selectQuery = "SELECT introduce, career, price, careable_pet FROM expert_table WHERE user_number = " + userNumber + ";";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            try {
                int priceIndex = cursor.getColumnIndex("price");
                int carePetIndex = cursor.getColumnIndex("careable_pet");
                int introduceIndex = cursor.getColumnIndex("introduce");
                int careerIndex = cursor.getColumnIndex("career");

                // Move to the first row of the cursor
                if (cursor.moveToFirst()) {
                    do {
                        ExpertInfo expert = new ExpertInfo();

                        // Check if the column indices are valid before setting data
                        if (priceIndex != -1) {
                            expert.setPrice(cursor.getString(priceIndex));
                        }

                        if (carePetIndex != -1) {
                            expert.setCareablePet(cursor.getString(carePetIndex));
                        }

                        if (introduceIndex != -1) {
                            expert.setIntroduce(cursor.getString(introduceIndex));
                        }

                        if (careerIndex != -1) {
                            expert.setCareer(cursor.getString(careerIndex));
                        }

                        expertList.add(expert);

                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
                db.close();
            }
        }

        return expertList;
    }
}
