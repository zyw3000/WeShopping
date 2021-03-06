package zyw.weshopping.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import zyw.weshopping.Fragment.FrdFragment;
import zyw.weshopping.Fragment.MarkFragment;
import zyw.weshopping.Fragment.MenuFragment;
import zyw.weshopping.Fragment.NewsFragment;
import zyw.weshopping.R;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout mTabMenu;
    private LinearLayout mTabNews;
    private LinearLayout mTabFrd;
    private LinearLayout mTabMark;
    private TextView mtitle;

    private ImageButton mImgMenu;
    private ImageButton mImgNews;
    private ImageButton mImgFrd;
    private ImageButton mImgMark;

    private Fragment mf1;
    private Fragment mf2;
    private Fragment mf3;
    private Fragment mf4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉顶部标题
        setContentView(R.layout.main);

        mfindViewById();

        initEvent();
        setSelect(0);

    }

    private void initEvent() {
        mTabMenu.setOnClickListener(this);
        mTabNews.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabMark.setOnClickListener(this);
    }

    private void mfindViewById() {
        mtitle= (TextView) findViewById(R.id.title_name);
        mTabMenu = (LinearLayout) findViewById(R.id.id_tab_menu);
        mTabNews = (LinearLayout) findViewById(R.id.id_tab_news);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabMark = (LinearLayout) findViewById(R.id.id_tab_mark);

        mImgMenu = (ImageButton) findViewById(R.id.id_menu_img);
        mImgNews = (ImageButton) findViewById(R.id.id_news_img);
        mImgFrd = (ImageButton) findViewById(R.id.id_frd_img);
        mImgMark = (ImageButton) findViewById(R.id.id_mark_img);
    }

    @Override
    public void onClick(View v) {
        resetImgs(); //重置图片,都变为初始颜色
        switch (v.getId()) {
            case R.id.id_tab_menu:
                setSelect(0);
                break;
            case R.id.id_tab_news:
                setSelect(1);
                break;
            case R.id.id_tab_frd:
                setSelect(2);
                break;
            case R.id.id_tab_mark:
                setSelect(3);
                break;
            default:
                break;
        }
    }

    private void resetImgs() {
        mImgMenu.setImageResource(R.drawable.menu_normal);
        mImgNews.setImageResource(R.drawable.news_normal);
        mImgFrd.setImageResource(R.drawable.lt_normal);
        mImgMark.setImageResource(R.drawable.mark_normal);

    }

    public void setSelect(int i) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction= fm.beginTransaction();
        // 开启Fragment事务
        hideFragment(transaction);  //隐藏所有的Fragment
        //设置选中区域是亮的
        switch(i) {
            case 0:
                if(mf1 == null) {
                    mf1 = new MenuFragment();
                    mtitle.setText("掌上轻纺城");
                    transaction.add(R.id.id_content, mf1);
                    // 往Activity中添加一个Fragment,这里添加的是MenuFragment			
                }
                else{
                    transaction.show(mf1);
                    mtitle.setText("掌上轻纺城");
                    //显示之前隐藏的Fragment
                }
                mImgMenu.setImageResource(R.drawable.menu_press);
                //替换为有颜色的图片
                break;
            case 1:
                if(mf2 == null) {
                    mf2 = new NewsFragment();
                    mtitle.setText("资讯");
                    transaction.add(R.id.id_content, mf2);
                }
                else{
                    mtitle.setText("资讯");
                    transaction.show(mf2);
                }
                mImgNews.setImageResource(R.drawable.news_press);
                break;
            case 2:
                if(mf3 == null) {
                    mf3 = new FrdFragment();
                    mtitle.setText("个人中心");
                    transaction.add(R.id.id_content, mf3);
                }
                else {
                    transaction.show(mf3);
                    mtitle.setText("个人中心");
                }
                mImgFrd.setImageResource(R.drawable.lt_press);
                break;
            case 3:
                if(mf4 == null) {
                    mf4=new MarkFragment();
                    mtitle.setText("购物车");
                    transaction.add(R.id.id_content, mf4);
                }
                else {
                    transaction.show(mf4);
                    mtitle.setText("购物车");
                }
                mImgMark.setImageResource(R.drawable.mark_press);
                break;
            default:
                break;
        }
        transaction.commit(); //提交一个事务
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(mf1!=null) {
            transaction.hide(mf1);
        }
        if(mf2!=null) {
            transaction.hide(mf2);
        }
        if(mf3!=null) {
            transaction.hide(mf3);
        }
        if(mf4!=null) {
            transaction.hide(mf4);
        }
    }

}
