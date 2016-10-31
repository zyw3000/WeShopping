package zyw.weshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Static.Appstatic;
import mysql.User;
import mysql.Userrepo;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class ZhuceActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mzc_name;
    private EditText mzc_you_name;
    private EditText mzc_password;

    private Button mzhuce;
    private Button mquxiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuceuser);

        mzc_name = (EditText) findViewById(R.id.zc_name);
        mzc_password = (EditText) findViewById(R.id.zc_password);
        mzc_you_name = (EditText) findViewById(R.id.zc_you_name);

        mzhuce = (Button) findViewById(R.id.zhuce);
        mquxiao = (Button) findViewById(R.id.quxiao);

        mquxiao.setOnClickListener(this);
        mzhuce.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.zhuce:
                zhuce();//调用注册方法
                break;
            case R.id.quxiao:
                Intent intent =new Intent(ZhuceActivity.this,LoginActivity.class);
                ZhuceActivity.this.startActivity(intent);
                finish();
                break;
            default:
                break;

        }

    }

    private void zhuce() {
        User user = new User();
        user.name = mzc_name.getText().toString().trim();
        user.password = mzc_password.getText().toString().trim();
        user.you_name = mzc_you_name.getText().toString().trim();
        Userrepo userrepo = new Userrepo(this);

        int id = userrepo.insert(user);
        if (id !=0){
            Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
            user.user_id=id;
            Appstatic.user = user;
            Intent intent =new Intent(ZhuceActivity.this,MainActivity.class);
            ZhuceActivity.this.startActivity(intent);
        }else {
            Toast.makeText(this,"该手机号码已经注册",Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
