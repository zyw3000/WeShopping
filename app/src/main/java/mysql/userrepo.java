package mysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class Userrepo {
    private DBUser dbUser ;

    public Userrepo(Context context){
        dbUser = new DBUser(context);
    }

    public int insert(User user){

        User u=getUserbyname(user.name);
        if (u==null){
            //打开连接，写入数据
            SQLiteDatabase db=dbUser.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(User.KEY_NAME,user.name);
            values.put(User.KEY_PASSWORD,user.password);
            values.put(User.KEY_YOU_NAME,user.you_name);
            //
            long student_Id=db.insert(User.TABLE,null,values);
            db.close();
            return (int)student_Id;
        }else {
            return 0;
        }

    }

    public void update(User user){
        SQLiteDatabase db=dbUser.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(User.KEY_NAME,user.name);
        values.put(User.KEY_PASSWORD,user.password);
        values.put(User.KEY_YOU_NAME,user.you_name);

        db.update(User.TABLE,null,User.KEY_ID+"=?",new String[] { String.valueOf(user.user_id) });
        db.close();
    }
    public User getUserbyname(String name){
        SQLiteDatabase db=dbUser.getReadableDatabase();
        String selectQuery="SELECT "+
                User.KEY_ID + "," +
                User.KEY_NAME + "," +
                User.KEY_PASSWORD + "," +
                User.KEY_YOU_NAME +
                " FROM " + User.TABLE
                + " WHERE " +
                User.KEY_NAME + "=?";
        int iCount=0;
        User user = new User();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{name});
        if(cursor.moveToFirst()){
            do{
                user.user_id =cursor.getInt(cursor.getColumnIndex(User.KEY_ID));
                user.name =cursor.getString(cursor.getColumnIndex(User.KEY_NAME));
                user.password  =cursor.getString(cursor.getColumnIndex(User.KEY_PASSWORD));
                user.you_name =cursor.getString(cursor.getColumnIndex(User.KEY_YOU_NAME));
            }while(cursor.moveToNext());

            cursor.close();
            db.close();
            return user;
        }
        cursor.close();
        db.close();
        return null;
    }

    public User getUserbylogin(String name,String password){
        SQLiteDatabase db=dbUser.getReadableDatabase();
        String selectQuery="SELECT "+
                User.KEY_ID + "," +
                User.KEY_NAME + "," +
                User.KEY_PASSWORD + "," +
                User.KEY_YOU_NAME +
                " FROM " + User.TABLE
                + " WHERE " +
                User.KEY_NAME + "=? AND "+
                User.KEY_PASSWORD+"=?";
        int iCount=0;
        User user = new User();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{name,password});
        if(cursor.moveToFirst()){
            do{
                user.user_id =cursor.getInt(cursor.getColumnIndex(User.KEY_ID));
                user.name =cursor.getString(cursor.getColumnIndex(User.KEY_NAME));
                user.password  =cursor.getString(cursor.getColumnIndex(User.KEY_PASSWORD));
                user.you_name =cursor.getString(cursor.getColumnIndex(User.KEY_YOU_NAME));
            }while(cursor.moveToNext());
            cursor.close();
            db.close();
            return user;

        }
        cursor.close();
        db.close();
        return null;
    }
}
