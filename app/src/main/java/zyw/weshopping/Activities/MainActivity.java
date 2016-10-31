package zyw.weshopping.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import zyw.weshopping.Beans.News;
import zyw.weshopping.Fragments.FrdFragment;
import zyw.weshopping.Fragments.MarkFragment;
import zyw.weshopping.Fragments.MenuFragment;
import zyw.weshopping.Fragments.NewsFragment;
import zyw.weshopping.R;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private List<News> newsList;

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
                getNewsInfo();
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


    Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            ListView listView = (ListView) findViewById(R.id.lv);
            listView.setAdapter(new MyAdapter());
        }
    };

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v;
            ViewHolder mHolder;
            if(convertView == null)
            {
                v = View.inflate(MainActivity.this, R.layout.item_listview, null);
                mHolder = new ViewHolder();
                mHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
                mHolder.tv_detail = (TextView) v.findViewById(R.id.tv_detail);
                mHolder.tv_date = (TextView) v.findViewById(R.id.tv_date);
                mHolder.siv = (SmartImageView) v.findViewById(R.id.iv);
                v.setTag(mHolder);
            }
            else
            {
                v = convertView;
                mHolder = (ViewHolder) v.getTag();
            }
            mHolder.tv_title.setText(newsList.get(position).getZx_Mame());
            mHolder.tv_detail.setText(newsList.get(position).getZx_Content());
            mHolder.tv_date.setText(newsList.get(position).getZx_Date());
            mHolder.siv.setImageUrl(newsList.get(position).getZx_Tp());
            return v;
        }

        class ViewHolder{
            TextView tv_title;
            TextView tv_detail;
            TextView tv_date;
            SmartImageView siv;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    public void getNewsInfo() {
        Thread t = new Thread() {

            //执行子线程
            @Override
            public void run() {
                String path = "http://192.168.1.101:8080/app/news.xml";
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    if(conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        parseNewsXml(is);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    public void parseNewsXml(InputStream is) {

        XmlPullParser xp = Xml.newPullParser();

        try {
            xp.setInput(is, "utf-8");
            int type = xp.getEventType();
            News news = null;
            while (type != XmlPullParser.END_DOCUMENT) {

                switch (type) {
                    case XmlPullParser.START_TAG:
                        if("newslist".equals(xp.getName()))
                        {
                            newsList = new ArrayList<News>();
                        }
                        else if("news".equals(xp.getName()))
                        {
                            news = new News();
                        }
                        else if("title".equals(xp.getName()))
                        {
                            String title = xp.nextText();
                            news.setZx_Mame(title);
                        }
                        else if("detail".equals(xp.getName()))
                        {
                            String detail = xp.nextText();
                            news.setZx_Content(detail);
                        }
                        else if("date".equals(xp.getName()))
                        {
                            String date = xp.nextText();
                            news.setZx_Date(date);
                        }
                        else if("image".equals(xp.getName()))
                        {
                            String image = xp.nextText();
                            news.setZx_Tp(image);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("news".equals(xp.getName())){
                            newsList.add(news);
                        }
                        break;

                }
                type = xp.next();
            }
            handler.sendEmptyMessage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
