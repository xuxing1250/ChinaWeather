package com.example.administrator.chinaweather;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class CityWeather extends Activity {
    private static final String ERROR ="com.example.administrator.chinaweather.ERROR" ;

    private int index =0;
    private int itemClick;
    private TextView  mWeatherText;

    private HomeBroadcase myHomeBroadcase;


    /**
     * 注册动态广播
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        myHomeBroadcase = new HomeBroadcase();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(ERROR);
        registerReceiver(myHomeBroadcase,intentfilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myHomeBroadcase);

    }

    /**
     * 取消广播注册
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city_weather);
        Spinner spinner = (Spinner) findViewById(R.id.Spinner01);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CityName.CITY);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        //城市spinner选择
        Button submit = (Button) findViewById(R.id.Button01);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner) findViewById(R.id.Spinner01);
                Long l = spinner.getSelectedItemId();
                index = l.intValue();
                String s = CityName.CITY[index];
                int i = 0;
                Intent intent = new Intent();
                intent.setClass(CityWeather.this,WeatherView.class);
                intent.putExtra("cityname", s);
                intent.putExtra("itemClick",i);
                startActivity(intent);
            }

        });


        //城市手动输入
        Button submit_input = (Button) findViewById(R.id.Button001);
        submit_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEdit = (EditText)findViewById(R.id.EditText001);
                String s = mEdit.getText().toString();
                if (s.length()!=0){
                    int i=1;
                    Intent intent = new Intent();
                    intent.setClass(CityWeather.this,WeatherView.class);
                    intent.putExtra("cityname",s);
                    startActivity(intent);
                    intent.putExtra("itemClick",i);
                } else {
                    Toast.makeText(CityWeather.this,"请输入城市名称",Toast.LENGTH_SHORT).show();
                }


            }
        });






    }




        }

//        //显示当前天气情况
//        private void updateWeatherInfoView(int aResourceID, WeatherCurrentCondition aWFC) throws MalformedURLException {
//
//            URL imgURL = new URL("http://www.google.com/" + aWFC.getIcon());
//            ((SingleWeatherInfoView) findViewById(aResourceID)).setWeatherIcon(imgURL);
//            ((SingleWeatherInfoView) findViewById(aResourceID)).setWeatherString(aWFC.toString());
//        }
//
//        // 更新显示天气预报
//        private void updateWeatherInfoView(int aResourceID, WeatherForecastCondition aWFC) throws MalformedURLException {
//
//            URL imgURL = new URL("http://www.google.com/" + aWFC.getIcon());
//            ((SingleWeatherInfoView) findViewById(aResourceID)).setWeathe rIcon(imgURL);
//            ((SingleWeatherInfoView) findViewById(aResourceID)).setWeatherString(aWFC.toString());
//        }








class  HomeBroadcase extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(MyApplication.getmContext(), "请重新输入", Toast.LENGTH_SHORT).show();
    }
}
