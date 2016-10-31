package mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class DBUser extends SQLiteOpenHelper {
    //数据库版本号
    private static final int DATABASE_VERSION=4;
    //数据库名称
    private static final String DATABASE_NAME="weshopping.db";
    public DBUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据表
        String CREATE_TABLE_STUDENT="CREATE TABLE "+User.TABLE +"( "
                +User.KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +User.KEY_NAME+" TEXT, "
                +User.KEY_PASSWORD+" text, "
                +User.KEY_YOU_NAME+" TEXT)";
        db.execSQL(CREATE_TABLE_STUDENT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS "+User.TABLE );
        //再次创建表
        onCreate(db);
    }
}
