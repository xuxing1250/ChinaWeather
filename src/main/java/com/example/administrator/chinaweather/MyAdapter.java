package com.example.administrator.chinaweather;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/10.
 */
public class MyAdapter extends BaseAdapter {
    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;
    private Context mContext;




    public final class  ViewHolder{
        public TextView mDate;
        public TextView mWeather;
        public TextView mTempture;
        public ImageView mImage;
    }



    public MyAdapter(List<Map<String, Object>> data, Context mContext) {
        this.data = data;
        this.layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
       if (convertView==null){
           convertView = layoutInflater.inflate(R.layout.list_view,null);
           viewHolder.mDate = (TextView)convertView.findViewById(R.id.list_date);
           viewHolder.mWeather = (TextView)convertView.findViewById(R.id.list_weather);
           viewHolder.mTempture = (TextView)convertView.findViewById(R.id.list_temp);
           viewHolder.mImage = (ImageView)convertView.findViewById(R.id.list_image);
           convertView.setTag(viewHolder);
       }else {
           viewHolder=(ViewHolder)convertView.getTag();
       }
        viewHolder.mDate.setText((String) data.get(position).get("date"));
        viewHolder.mWeather.setText((String) data.get(position).get("weather"));
        viewHolder.mTempture.setText((String) data.get(position).get("tempture"));
        viewHolder.mImage.setBackgroundResource((Integer) data.get(position).get("image"));
        return convertView;
    }
}
