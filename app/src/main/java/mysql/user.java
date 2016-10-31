package mysql;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class User {
    //表名
    public static final String TABLE = "user";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_YOU_NAME = "you_name";

    public int user_id;
    public String name;
    public String password;
    public String you_name;
}
