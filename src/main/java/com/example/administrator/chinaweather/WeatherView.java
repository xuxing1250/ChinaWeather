package com.example.administrator.chinaweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WeatherView extends Activity {
    private TextView mTemture;                      //温度
    private TextView mWeather;                      //天气
    private TextView mWetPecent;                    //湿度
    private TextView mWind;                         //风向
    private TextView mWindPecent;                   //风力
    private TextView mKongqizhishu;                 //空气指数
    private TextView mPushTime;                     //发布时间
    private TextView mDate;                         //日期
    private TextView mDateInCHN;                    //农历

    private WeatherSet ws;
    private String cityName;
    private int itemClick;
    private MyAdapter mAdapter;
    private ListView mListView;
    private List<WeatherForecastCondition> weatherForecastConditions;
    private WeatherCurrentCondition weatherCurrentCondition;
    private  List<Map<String,Object>> list;
    private String date;
    private String type;
    private String low;
    private String high;


    private int test =0;


    //定义Handler对象
    private Handler handler =new Handler(){
        @Override
//当有消息发送出来的时候就执行Handler的这个方法

        public void handleMessage(Message msg){
            super.handleMessage(msg);
            //处理UI


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_weather_view);
        getView();
        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityname");
        itemClick = intent.getIntExtra("itemClick", 0);
        Log.d("cityname", "+++++++++++++++" + cityName);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                init();
                handler.sendEmptyMessage(0);
                weatherCurrentCondition = ws.getMyCurrentCondition();
                weatherForecastConditions = ws.getMyForecastConditions();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateView();
        list = getData();
        mListView.setAdapter(new MyAdapter(list, this));

    }

    private  void getString(WeatherForecastCondition weatherForecastCondition) {
        date = weatherForecastCondition.getData();
        type = weatherForecastCondition.getType();
        low = weatherForecastCondition.getLow();
        high = weatherForecastCondition.getHigh();
    }
    private  void getString(WeatherCurrentCondition weatherCurrentCondition) {
        date = weatherCurrentCondition.getData();
        type = weatherCurrentCondition.getType();
        low = weatherCurrentCondition.getLow();
        high = weatherCurrentCondition.getHigh();
    }


    private void getView(){
        mTemture = (TextView)findViewById(R.id.tempture);
        mWeather = (TextView)findViewById(R.id.weather);
        mWind = (TextView)findViewById(R.id.wind);
        mWetPecent = (TextView)findViewById(R.id.wet_pecent);
        mWindPecent = (TextView)findViewById(R.id.wind_pecent);
        mKongqizhishu = (TextView)findViewById(R.id.kongqizhishu);
        mPushTime = (TextView)findViewById(R.id.pushtime);
        mDate = (TextView)findViewById(R.id.textView7);
        mDateInCHN = (TextView)findViewById(R.id.textView8);
        mListView = (ListView)findViewById(R.id.list_view);
    }


    //    将城市插入spinner中
    private void init() {
        switch (itemClick){
            case 0:
                try {
                    URL url = new URL(CityName.QUERYSTRING + cityName);
                    getCityWeather(url);
                } catch (MalformedURLException e) {
                    Log.e("CityWeather", e.toString());
                }
                break;
            case 1:
                try {
                    URL url = new URL(CityName.QUERYSTRING_INPUT + cityName);
                    getCityWeather(url);
                } catch (MalformedURLException e) {
                    Log.e("CityWeather", e.toString());
                }
        }
    }

    /**
     * 获取天气信息
     * 通过网络获取数据
     * 传递给XMLReader
     * 解析获取城市天气信息
     */
    private  void getCityWeather(URL url) {
    //获取ParaseFactory实例——————获取SAXParser实例-------获取XMLReader实例 通过XMLReader实例解析XML
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            GoogleWeatherHandler gwg = new GoogleWeatherHandler();
            xr.setContentHandler(gwg);
            InputStreamReader isr = new InputStreamReader(url.openStream(), "utf-8");
            InputSource is = new InputSource(isr);
            xr.parse(is);
            ws = gwg.getMyWeatherSet();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  List<Map<String, Object>> getData() {
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 1;i<4;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            getString(weatherForecastConditions.get(i));
//            getString(weatherCurrentCondition);
            map.put("date", date);
            map.put("weather",type);
            map.put("tempture",low+"/"+high);

            switch (type){
                case   "晴" :
                    map.put("image",R.drawable.weather8);
                    break;
                case   "阴" :
                    map.put("image",R.drawable.weather3);
                    break;
                case    "多云":
                    map.put("image",R.drawable.weather10);
                    break;
                case    "雷阵雨":
                    map.put("image",R.drawable.weather5);
                    break;
                default:
                    map.put("image",R.drawable.weather4);
                    break;
            }


            list.add(map);
        }
        return list;
    }

    private void updateView(){
        mTemture.setText(weatherCurrentCondition.getTemp_celcius()+"°");
        mWeather.setText(weatherCurrentCondition.getType());
        mWetPecent.setText(weatherCurrentCondition.getHumidity());
        mWind.setText(weatherCurrentCondition.getWind());
        mWindPecent.setText(weatherCurrentCondition.getWind_condition());
        mPushTime.setText("中央气象台"+weatherCurrentCondition.getUpdatatime()+"发布");
        mDate.setText(weatherCurrentCondition.getData());
    }
}
