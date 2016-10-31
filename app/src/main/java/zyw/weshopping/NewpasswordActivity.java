package zyw.weshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import Static.Appstatic;

public class NewpasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mNewpasswordEditText;
    private EditText mTooEditText;
    private Button mUpdatepasswordbtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);

        mNewpasswordEditText = (EditText) findViewById(R.id.newpassword);
        mTooEditText = (EditText) findViewById(R.id.newpassword_too);
        mUpdatepasswordbtButton = (Button) findViewById(R.id.updatepasswordbt);

        mUpdatepasswordbtButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(mNewpasswordEditText.getText().toString().trim().equals(mTooEditText.getText().toString().trim())){
            Appstatic.user.password = mNewpasswordEditText.getText().toString().trim();
            Toast.makeText(this,Appstatic.user.password,Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
        }
    }
}
