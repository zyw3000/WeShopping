package zyw.weshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Static.Appstatic;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class FrdFragment extends Fragment implements View.OnClickListener {

    Toast toast;

    private Button mlogin;//登录按钮
    private Button mckshdz;//查看收货地址按钮
    private Button mxiugaimima;//修改密码按钮
    private Button mchakandingdan;//查看订单按钮
    private Button mtuichu;//退出按钮


    private TextView mhuanying;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //将布局文件转化为View
        View view = inflater.inflate(R.layout.f4, container, false);

        mfindviewbyid(view);//控件实例化
        monclicklistener();//设置控件监听事件

        chushihua();//初始化

        return view;
    }

    private void chushihua() {
        if (Appstatic.user!=null){
            mhuanying.setText("欢迎你，"+Appstatic.user.you_name);
            mlogin.setVisibility(View.INVISIBLE);
        }else {
            mhuanying.setText("亲，你还没有登录！");
            mlogin.setVisibility(View.VISIBLE);
        }
    }

    public void mfindviewbyid(View view){
        mckshdz = (Button) view.findViewById(R.id.ckshdz);
        mxiugaimima = (Button) view.findViewById(R.id.xiugaimima);
        mchakandingdan = (Button) view.findViewById(R.id.chakandingdan);
        mtuichu = (Button) view.findViewById(R.id.tuichu);
        mlogin = (Button) view.findViewById(R.id.login);

        mhuanying = (TextView) view.findViewById(R.id.huanying);
    }

    public void monclicklistener(){
        mlogin.setOnClickListener(this);
        mckshdz.setOnClickListener(this);
        mxiugaimima.setOnClickListener(this);
        mchakandingdan.setOnClickListener(this);
        mtuichu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                login();//调用登录方法
                break;
            case R.id.tuichu:
                tuichu();//调用退出方法
                break;
            case R.id.ckshdz:
                Intent intent =new Intent(getActivity(),AddressActivity.class);
                getActivity().startActivity(intent);
            case R.id.xiugaimima:
                xiugaimima();
            default:
                break;

        }
    }

    private void xiugaimima() {
        if (Appstatic.user==null){
            toast.makeText(getActivity(),"您还没有登录，请先登录！",Toast.LENGTH_LONG ).show();
        }else {
            Intent intent = new Intent(getActivity(),NewpasswordActivity.class);
            startActivity(intent);
        }
    }

    private void tuichu() {
        //如果登录按钮不显示则说明当前处于登录状态，反之则为未登录状态
        if (mlogin.getVisibility()==View.INVISIBLE){
            //退出时想说有和用户有关的数据全部清空，所有的空间恢复至登录前状态
            Appstatic.user = null;
            Appstatic.addresselist = null;
            mhuanying.setText("亲，你还没有登录！");
            mlogin.setVisibility(View.VISIBLE);
        }else {
            //判断当前是否有toast显示，如果没有则之间显示toast，否则只是显示重新显示一遍
            //防止因为用户失误连续多次按退出键使toast显示时间叠加
            if (toast==null){
                toast = Toast.makeText(getActivity(),"您已经退出登录，不可重复登录",
                        Toast.LENGTH_LONG);
            }else {
                toast.setText("您已经退出登录，不可重复退出");
            }
            toast.show();
        }
    }

    private void login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
    /*
     *通过startActivityForResult方法获得回传的数据
     * 第二个参数为请求标志，判断是哪个请求调用这个方法
     */
        startActivityForResult(intent, 1);
    }

    /*
     *第一个参数是请求标志
     * 第二个参数是回传标志
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2) {
            String huangying = data.getStringExtra("name");
            mhuanying.setText("欢迎你，"+huangying);
            mlogin.setVisibility(View.INVISIBLE);
        }
    }


}
