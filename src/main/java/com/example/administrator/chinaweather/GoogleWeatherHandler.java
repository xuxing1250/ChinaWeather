package com.example.administrator.chinaweather;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/4.
 */

public class GoogleWeatherHandler extends DefaultHandler {
    //天气信息
    private WeatherSet myWeatherSet = null;
    //    实时天气信息
    private boolean is_Current_Conditions = false;
    //    预报天气信息
    private boolean is_Forecast_Conditions = false;
    private int curent;
    private String sTable;

    private final String CURRENT_CONDITIONS = "resp";
    private final String FORECAST_CONDITIONS = "forecast_conditions";
    public static final String TAG = "GoogleWeatherHandle";

    private WeatherCurrentCondition mWcurrentCondition;
    private WeatherForecastCondition mWforecastCondition;
    private List<WeatherForecastCondition> mForecastConditions;

    //    构造方法
    public GoogleWeatherHandler() {

    }

    //返回天气信息对象
    public WeatherSet getMyWeatherSet() {
        return myWeatherSet;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        myWeatherSet.setMyCurrentCondition(mWcurrentCondition);
        myWeatherSet.setMyForecastConditons((ArrayList<WeatherForecastCondition>) mForecastConditions);
        Log.d(TAG,""+mWcurrentCondition.toString());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals(CURRENT_CONDITIONS)) {
            this.is_Current_Conditions = false;
        } else if (localName.equals(FORECAST_CONDITIONS)) {
            this.is_Forecast_Conditions = false;
        }

    }

    @Override
    public void startDocument() throws SAXException {
//        初始化天气信息
        this.myWeatherSet = new WeatherSet();
        mWcurrentCondition = new WeatherCurrentCondition();
        mForecastConditions = new ArrayList<WeatherForecastCondition>();


    }

    @Override
//    开始解析Xml
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        sTable = qName;
        if (qName.equals("weather")){
            mWforecastCondition = new WeatherForecastCondition();
            mForecastConditions.add(mWforecastCondition);
            myWeatherSet.setMyForecastConditon(mWforecastCondition);
        }else if (qName.equals("error")){

        }


//        if (localName.equals("updatetime")) {
//            sT
//        } else if (localName.equals("wendu")) {
//            String mTemt = attributes.getValue("wendu");
//            myWeatherSet.getMyCurrentCondition().setTemp_celcius(mTemt);
//            Log.d(TAG, "---" + mTemt);
//
//        } else if (localName.equals("fengli")) {
//            String mWind = attributes.getValue("fengli");
//            myWeatherSet.getMyCurrentCondition().setWind(mWind);
//            Log.d(TAG, "---" + mWind);
//
//        } else if (localName.equals("shidu")) {
//            String mWet = attributes.getValue("shidu");
//            myWeatherSet.getMyCurrentCondition().setHumidity(mWet);
//            Log.d(TAG, "---" + mWet);
//
//        } else if (localName.equals("fengxiang")) {
//            String mWind_where = attributes.getValue("wendu");
//            myWeatherSet.getMyCurrentCondition().setWind_condition(mWind_where);
//            Log.d(TAG, "---" + mWind_where);
//
//        } else if (localName.equals("forecast")) {
//            if (localName.equals("weather")) {
//                WeatherForecastCondition mForeCast = new WeatherForecastCondition();
//                myWeatherSet.setMyForecastConditon(mForeCast);
//                myWeatherSet.getMyForecastConditions().add(mForeCast);
//                if (localName.equals("data")) {
//                    String mdata = attributes.getValue("date");
//                    myWeatherSet.getMyForecastConditon().setData(mdata);
//                    Log.d(TAG, "---" + mdata);
//                } else if (localName.equals("high")) {
//                    String mHigt = attributes.getValue("high");
//                    myWeatherSet.getMyForecastConditon().setHigh(mHigt);
//                    Log.d(TAG, "---" + mHigt);
//                } else if (localName.equals("low")) {
//                    String mLow = attributes.getValue("low");
//                    myWeatherSet.getMyForecastConditon().setLow(mLow);
//                    Log.d(TAG, "---" + mLow);
//                } else if (localName.equals("day")) {
//                    if (localName.equals("type")) {
//                        String mType = attributes.getValue("type");
//                        myWeatherSet.getMyForecastConditon().setType(mType);
//                        Log.d(TAG, "---" + mType);
//                    } else if (localName.equals("fengxiang")) {
//                        String mWind_where = attributes.getValue("fengxiang");
//                        myWeatherSet.getMyForecastConditon().setWind_condition(mWind_where);
//                        Log.d(TAG, "---" + mWind_where);
//                    } else if (localName.equals("fengli")) {
//                        String mWind = attributes.getValue("date");
//                        myWeatherSet.getMyForecastConditon().setWind(mWind);
//                        Log.d(TAG, "---" + mWind);
//                    }
//                }
//            String mType = myWeatherSet.getMyForecastConditions().get(0).getType();
//            mCurrent.setType(mType);
//            String mHigh = myWeatherSet.getMyForecastConditions().get(0).getHigh();
//            mCurrent.setHigh(mHigh);
//            String mLow = myWeatherSet.getMyForecastConditions().get(0).getLow();
//            mCurrent.setLow(mLow);
         }


        /**
         if(localName.equals(CURRENT_CONDITIONS)){
         //实时天气
         this.myWeatherSet.setMyCurrentCondition(new WeatherCurrentCondition());
         this.is_Current_Conditions=true;
         }else if (localName.equals(FORECAST_CONDITIONS)){
         this.myWeatherSet.getMyForecastConditions().add(new WeatherForecastCondition());
         this.is_Forecast_Conditions = true;
         }else{
         //分别将得到的信息设置到指定的对象中
         if (localName.equals(CURRENT_CONDITIONS))
         {
         Log.i("localName+CURRENT", localName);
         }
         String dataAttribute = attributes.getValue("data");

         if (localName.equals("icon"))
         {
         if (this.is_Current_Conditions)
         {
         this.myWeatherSet.getMyCurrentCondition().setIcon(dataAttribute);
         }
         else if (this.is_Forecast_Conditions)
         {
         this.myWeatherSet.getLastForecastCondition().setIcon(dataAttribute);
         }
         }
         else if (localName.equals("condition"))
         {
         if (this.is_Current_Conditions)
         {
         this.myWeatherSet.getMyCurrentCondition().setCondition(dataAttribute);
         }
         else if (this.is_Forecast_Conditions)
         {
         this.myWeatherSet.getLastForecastCondition().setCondition(dataAttribute);
         }
         }
         else if (localName.equals("temp_c"))
         {
         this.myWeatherSet.getMyCurrentCondition().setTemp_celcius(dataAttribute);
         }
         else if (localName.equals("temp_f"))
         {
         this.myWeatherSet.getMyCurrentCondition().setTemp_fahrenheit(dataAttribute);
         }
         else if (localName.equals("humidity"))
         {
         this.myWeatherSet.getMyCurrentCondition().setHumidity(dataAttribute);
         }
         else if (localName.equals("wind_condition"))
         {
         this.myWeatherSet.getMyCurrentCondition().setWind_condition(dataAttribute);
         }// Tags is forecast_conditions
         else if (localName.equals("day_of_week"))
         {
         this.myWeatherSet.getLastForecastCondition().setDay_of_week(dataAttribute);
         }
         else if (localName.equals("low"))
         {
         this.myWeatherSet.getLastForecastCondition().setLow(dataAttribute);
         }
         else if (localName.equals("high"))
         {
         this.myWeatherSet.getLastForecastCondition().setHigh(dataAttribute);
         }
         }
         */

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch,start,length);
        switch (sTable){
            case "city":
                Log.d(TAG, "s--------" + s);
                sTable = "";
                break;
            case "updatetime":
                mWcurrentCondition.setUpdatatime(s);
                Log.d(TAG, "s--------" + s);
                sTable = "";
                break;
            case "wendu":
                mWcurrentCondition.setTemp_celcius(s);
                Log.d(TAG, "s--------" + s);
                sTable = "";
                break;
            case "shidu":
                mWcurrentCondition.setHumidity(s);
                Log.d(TAG, "s--------" + s);
                sTable = "";
                break;
            case "fengli":
                 if (mWcurrentCondition.getWind()==null){
                    mWcurrentCondition.setWind(s);
                    Log.d(TAG, "s--------" + s);

                }
                sTable = "";
                break;
            case "fengxiang":
                if (mWcurrentCondition.getWind_condition()==null) {
                    mWcurrentCondition.setWind_condition(s);
                    Log.d(TAG, "s--------" + s);
                    break;
                }
                if (mWforecastCondition.getWind_condition()==null){
                    mWforecastCondition.setWind_condition(s);
                    Log.d(TAG, "s--------" + s);
                }
                sTable = "";


                break;
            case "date":
                mWforecastCondition.setData(s);
                if (mWcurrentCondition.getData()==null){
                    mWcurrentCondition.setData(s);
                }
                Log.d(TAG, "s--------" + s);
                sTable = "";
                break;
            case "high":
                mWforecastCondition.setHigh(s);
                Log.d(TAG, "s--------" + s);
                if (mWcurrentCondition.getHigh()==null){
                    mWcurrentCondition.setHigh(s);
                }
                sTable = "";
                break;
            case "low":
                mWforecastCondition.setLow(s);
                Log.d(TAG, "s--------" + s);
                if (mWcurrentCondition.getLow()==null){
                    mWcurrentCondition.setLow(s);
                }
                sTable = "";
                break;
            case "type":
                if (mWforecastCondition.getType()==null){
                    mWforecastCondition.setType(s);
                    if (mWcurrentCondition.getType()==null){
                        mWcurrentCondition.setType(s);
                    }
                    Log.d(TAG, "s--------" + s);
                }
                sTable = "";
                break;
        }

    }
}
