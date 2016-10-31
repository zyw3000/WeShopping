package zyw.weshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Static.Appstatic;
import mysql.User;
import mysql.Userrepo;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mname;//账号控件
    private EditText mpassword;//密码控件

    private TextView lijizhuce;//立即注册

    private Button mlong;//登录按钮


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        lijizhuce = (TextView) findViewById(R.id.lijizhuce);

        mlong= (Button) findViewById(R.id.login_layout);
        mname= (EditText) findViewById(R.id.name);
        mpassword = (EditText) findViewById(R.id.password);

        mlong.setOnClickListener(this);
        lijizhuce.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_layout:
                login();
                break;
            case R.id.lijizhuce:
                zhuce();
                break;
            default:
                break;
        }


    }

    private void zhuce() {
        Intent intent = new Intent(LoginActivity.this,ZhuceActivity.class);
        LoginActivity.this.startActivity(intent);
        finish();
    }

    private void login() {
        Userrepo repo = new Userrepo(this);
        Appstatic.user = repo.getUserbylogin(mname.getText().toString(),
                                             mpassword.getText().toString());
        if(Appstatic.user!=null){
            Intent intent =getIntent();
            intent.putExtra("name",Appstatic.user.you_name);
            setResult(2,intent);
            finish();

        }else{
            Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show();
        }
    }
}
