package zyw.weshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import Myui.AddressListview;
import Static.Appstatic;
import mysql.Address;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener{

    public AddressListview lv_address;
    public Button addaddress;
    public ImageButton address_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉顶部标题
        setContentView(R.layout.activity_address);

        lv_address= (AddressListview) findViewById(R.id.lv_address);
        addaddress = (Button) findViewById(R.id.addaddress);
        address_back= (ImageButton) findViewById(R.id.address_back);

        addaddress.setOnClickListener(this);
        address_back.setOnClickListener(this);

        if(Appstatic.addresselist==null){
            Appstatic.addresselist = new ArrayList<Address>();
            shushihuashuju();//初始化数据，模拟数据
        }


        lv_address.updateaddress();
    }

    private void shushihuashuju() {
        for(int i=0 ; i<9;i++ ){
            Address address = new Address();
            address.id = i;
            address.user_id = i;
            address.name = "巧克力"+i;
            address.tele = "123456" +i;
            address.address = "之江学院"+i;
            Appstatic.addresselist.add(address);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_back:
                finish();
                break;
            case R.id.addaddress:
                Intent intent = new Intent(this,Add_addressActivity.class);
                this.startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
