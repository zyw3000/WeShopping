package Myui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Static.Appstatic;
import mysql.Address;
import zyw.weshopping.LoginActivity;
import zyw.weshopping.R;

/**
 * Created by Administrator on 2016/10/29 0029.
 */

public class AddressListview extends ListView {

    public ArrayList<Address> mlist = new ArrayList<Address>();
    public Addressadapter maddressadpter;
    public Context mcontext;
    public LayoutInflater inflate;



    public AddressListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext=context;
        maddressadpter = new Addressadapter();
        inflate=LayoutInflater.from(context);
    }

    public void updateaddress(){
        mlist = Appstatic.addresselist;
        setAdapter(maddressadpter);
    }

    public void deleteAddress(final int i){
        Appstatic.addresselist.remove(i);
        mlist = Appstatic.addresselist;
        setAdapter(maddressadpter);
    }


    private void ItemOnClickListener(int position) {
        Log.d("TTT",Appstatic.addresselist.get(position).name);


    }


    public class Addressadapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            Address address = mlist.get(position);
            Viewholder holder = null;
            if (convertView == null){
                convertView = inflate.inflate(R.layout.address_item,null);
                holder = new Viewholder();
                holder.shuser = (TextView) convertView.findViewById(R.id.shuser);
                holder.usertele = (TextView) convertView.findViewById(R.id.usertele);
                holder.shaddress = (TextView) convertView.findViewById(R.id.shaddress);
                holder.deleteaddress = (Button) convertView.findViewById(R.id.deleteaddress);

                convertView.setTag(holder);
            }else {
                holder = (Viewholder) convertView.getTag();
            }
            holder.shuser.setText(address.name);
            holder.usertele.setText(address.tele);
            holder.shaddress.setText(address.address);
            holder.deleteaddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteAddress(position);
                }
            });
            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemOnClickListener(position);
                }
            });
            return convertView;
        }


        private final class Viewholder{
            public TextView shuser;
            public TextView usertele;
            public TextView shaddress;
            public Button deleteaddress;
        }
    }
}
