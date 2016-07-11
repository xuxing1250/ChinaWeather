package com.example.administrator.chinaweather;

/**
 * Created by Administrator on 2016/6/4.
 */
public class WeatherForecastCondition {

    private String data;                //  日期
    private String temp_celcius;		//  摄氏温度
    private String humidity;			//  湿度:58%
    private String wind;                //  风力

    private String wind_condition;		//  风向...

    private String high;                //  最高温
    private String low;                 //  最低温
    private String type;                //  天气情况
    public WeatherForecastCondition()
    {

    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getTemp_celcius() {
        return temp_celcius;
    }

    public void setTemp_celcius(String temp_celcius) {
        this.temp_celcius = temp_celcius;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getWind_condition() {
        return wind_condition;
    }

    public void setWind_condition(String wind_condition) {
        this.wind_condition = wind_condition;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("实时天气: ").append(temp_celcius).append(" °C");
        sb.append(" ").append(humidity);
        sb.append(" ").append(wind_condition);
        return sb.toString();
    }
}
