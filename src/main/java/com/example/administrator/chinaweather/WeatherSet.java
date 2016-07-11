package com.example.administrator.chinaweather;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/4.
 */
public class WeatherSet {
    private WeatherCurrentCondition myCurrentCondition              = null;
    private ArrayList<WeatherForecastCondition> myForecastConditions ;
    private WeatherForecastCondition myForecastConditon             = null;
    public WeatherSet()
    {

    }
    //得到实时天气信息的对象
    public WeatherCurrentCondition getMyCurrentCondition() {
        return myCurrentCondition;
    }
    //设置实时天气信息的对象
    public void setMyCurrentCondition(WeatherCurrentCondition myCurrentCondition) {
        this.myCurrentCondition = myCurrentCondition;
    }
    //得到预报天气
    public ArrayList<WeatherForecastCondition> getMyForecastConditions()
    {
        return myForecastConditions;
    }


    public WeatherForecastCondition getMyForecastConditon() {
        return myForecastConditon;
    }

    public void setMyForecastConditon(WeatherForecastCondition myForecastConditon) {
        this.myForecastConditon = myForecastConditon;
    }

    public void setMyForecastConditons(ArrayList<WeatherForecastCondition> myForecastConditons) {
        this.myForecastConditions = myForecastConditons;
    }
}
