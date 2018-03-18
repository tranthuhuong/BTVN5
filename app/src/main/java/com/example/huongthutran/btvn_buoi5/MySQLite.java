package com.example.huongthutran.btvn_buoi5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Huong Thu Tran on 3/17/2018.
 */

public class MySQLite extends SQLiteOpenHelper {
    private static String  DB_NAME = "QLSinhVien";
    private String CREATE_TABLE_USER="CREATE TABLE "+User.USER_TABLENAME+"("+User.USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " +
            User.USER_USERNAME + " TEXT , " +User.USER_PASSWORD + " TEXT )";
    private String CREATE_TABLE_STUDENT="CREATE TABLE "+Student.STUDENT_TABLENAME+"("+Student.STUDENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " +
            Student.STUDENT_USERNAME + " TEXT , " +Student.STUDENT_EMAIL + " TEXT )";
    Context mContext;
    public MySQLite(Context context) {

        super(context, DB_NAME, null, 1);
        mContext=context;
        getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create Table
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
       sqLiteDatabase.execSQL(CREATE_TABLE_USER);

        //add value default account
        ContentValues values=new ContentValues();
        values.put(User.USER_USERNAME,"nguyenthihanh");
        values.put(User.USER_PASSWORD,"nguyenthihanh");
        sqLiteDatabase.insert(User.USER_TABLENAME, null, values);
        values=new ContentValues();
        values.put(User.USER_USERNAME,"tranthithuhuong");
        values.put(User.USER_PASSWORD,"tranthithuhuong");
        sqLiteDatabase.insert(User.USER_TABLENAME, null, values);
        values=new ContentValues();
        values.put(User.USER_USERNAME,"admin");
        values.put(User.USER_PASSWORD,"admin");
        sqLiteDatabase.insert(User.USER_TABLENAME, null, values);
        //add value default Student
        values=new ContentValues();
        values.put(Student.STUDENT_USERNAME,"thanhnga");
        values.put(Student.STUDENT_EMAIL,"nguyenthanhnga@gmail.com");
        sqLiteDatabase.insert(Student.STUDENT_TABLENAME, null, values);
        values=new ContentValues();
        values.put(Student.STUDENT_USERNAME,"Bảo Anh");
        values.put(Student.STUDENT_EMAIL,"baoanh@gmail.com");
        sqLiteDatabase.insert(Student.STUDENT_TABLENAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXITS "+CREATE_TABLE_STUDENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXITS "+CREATE_TABLE_USER);

        onCreate(sqLiteDatabase);
    }

    public void InsertUser(String userName,String userPassword){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(User.USER_USERNAME,userName);
        contentValues.put(User.USER_PASSWORD,userPassword);
        db.insert(User.USER_TABLENAME,null,contentValues);
        db.close();
    }
    //Get all Users
    public ArrayList<User> GetUsers (){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<User> users= new ArrayList<User>();
        String sql="SELECT * FROM " + User.USER_TABLENAME;
        Cursor cursor=db.rawQuery(sql,null);
        User user;

        if(cursor.moveToFirst()){
            do{
                user=new User();
                user.setId_user(cursor.getInt(cursor.getColumnIndex(User.USER_ID)));
                user.setUserName(cursor.getString(cursor.getColumnIndex(User.USER_USERNAME)));
                user.setUserPassword(cursor.getString(cursor.getColumnIndex(User.USER_PASSWORD)));
                users.add(user);

            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        Toast.makeText(mContext, "ok", Toast.LENGTH_SHORT).show();

        return users;
    }
    //Get User By UserName and PassWord
    public User getUserByNameAndPass(String userName,String pass){
        User user;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+User.USER_TABLENAME+" WHERE "+User.USER_USERNAME +" = ?"+" AND "+User.USER_PASSWORD+" = ?", new String[] {userName, pass});
        if(cursor != null)
            cursor.moveToFirst();
        user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

        return user;
    }
    //true nếu có tồn tại,false nếu ko
    public Boolean checkUserName(String userName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+User.USER_TABLENAME+ " WHERE " +User.USER_USERNAME+ " = ? " , new String[] {userName});
        if (cursor != null && cursor.getCount() > 0) {
            return true;
        }

        return false;
    }

    //Get all Student in table Students
    public ArrayList<Student> getAllStudents (){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Student> arrayList= new ArrayList<Student>();
        String sql="SELECT * FROM " + Student.STUDENT_TABLENAME;
        Cursor  cursor = db.rawQuery("select * from "+ Student.STUDENT_TABLENAME,null);
        Student student;

        if(cursor.moveToFirst()){
            do{
                student=new Student();
                student.setId_student(cursor.getInt(cursor.getColumnIndex(Student.STUDENT_ID)));
                student.setfName(cursor.getString(cursor.getColumnIndex(Student.STUDENT_USERNAME)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(Student.STUDENT_EMAIL)));
                arrayList.add(student);

            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        Toast.makeText(mContext, "ok", Toast.LENGTH_SHORT).show();

        return arrayList;
    }
    //insert new student to Table Students
    public void insertStudent(String fName,String email){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Student.STUDENT_USERNAME,fName);
        contentValues.put(Student.STUDENT_EMAIL,email);
        db.insert(Student.STUDENT_TABLENAME,null,contentValues);
        db.close();
    }
    public void updateStudent (int id, String fName, String email){

        SQLiteDatabase db= getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put(Student.STUDENT_USERNAME, fName);

        values.put(Student.STUDENT_EMAIL,email);

        db.update( Student.STUDENT_TABLENAME,values,

                Student.STUDENT_ID + " = " + id, null);
        Toast.makeText(mContext, "Update Student ID: "+id+" successful", Toast.LENGTH_SHORT).show();
        db.close();
    }
    public void deleteStudentById(int id){
        SQLiteDatabase db= getWritableDatabase();
        db.delete(Student.STUDENT_TABLENAME,Student.STUDENT_ID+" = "+id,null);
        db.close();
    }
    public Student getStudentByNameAndPass(String userName){
        Student user;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Student.STUDENT_TABLENAME+" WHERE "+Student.STUDENT_USERNAME +" = ?", new String[] {userName});
        if(cursor != null)
            cursor.moveToFirst();
        user = new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

        return user;
    }

}
