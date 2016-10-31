package zyw.weshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Static.Appstatic;
import mysql.Address;

public class Add_addressActivity extends AppCompatActivity {

    private EditText mShNameEditText;
    private EditText mTeleEditText;
    private EditText mShAddressEditText;
    private Button mAddAddressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);


        mShNameEditText = (EditText) findViewById(R.id.add_sh_name);
        mTeleEditText = (EditText) findViewById(R.id.add_tele);
        mShAddressEditText = (EditText) findViewById(R.id.add_sh_address);
        mAddAddressButton = (Button) findViewById(R.id.add_address);

        mAddAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address address= new Address();
                if (mShNameEditText.getText().toString().trim().equals("")||
                        mTeleEditText.getText().toString().trim().equals("")||
                        mShAddressEditText.getText().toString().trim().equals("")){

                    String s = "";

                    if (mShNameEditText.getText().toString().trim().equals(""))
                        s = "收货人";

                    if (mTeleEditText.getText().toString().trim().equals("")){
                        if (!s.equals(""))s+="、";
                        s+="联系电话";
                    }


                    if (mShAddressEditText.getText().toString().trim().equals("")){
                        if (!s.equals(""))s+="、";
                        s +="收货地址";
                    }

                        Toast.makeText(Add_addressActivity.this,s+"不允许为空",Toast.LENGTH_LONG).show();
                }else {
                    if (Appstatic.user !=null){
                        address.user_id = Appstatic.user.user_id;
                        address.name = mShNameEditText.getText().toString().trim();
                        address.tele = mTeleEditText.getText().toString().trim();
                        address.address=mShAddressEditText.getText().toString().trim();
                        address.id = Appstatic.addresselist.size();
                        Appstatic.addresselist.add(address);
                        Intent intent = new Intent(Add_addressActivity.this,AddressActivity.class);
                        Add_addressActivity.this.startActivity(intent);
                    }else {
                        Toast.makeText(Add_addressActivity.this,"您还未登录，请先登录",Toast.LENGTH_LONG).show();
                    }
                    finish();
                }

            }
        });
    }
}
