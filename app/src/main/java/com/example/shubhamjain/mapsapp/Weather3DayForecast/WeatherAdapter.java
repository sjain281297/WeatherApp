package com.example.shubhamjain.mapsapp.Weather3DayForecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shubhamjain.mapsapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by SHUBHAM JAIN on 29-08-2016.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyHolder> {
    ArrayList<WeatherListResponse.Forecast.SimpleForcast.ForecastDay> mData;
    Context mContext;
    OnItemClickListener listener;
    private int lastPosition=-1;

    public interface OnItemClickListener {
        void onItemClick(WeatherListResponse.Forecast.SimpleForcast.ForecastDay item, int pos);
    }


    public WeatherAdapter(ArrayList<WeatherListResponse.Forecast.SimpleForcast.ForecastDay> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView weathTemp;
        ImageView weathIcon;
        TextView weathDate;
        TextView minTemp;
        TextView maxTemp;
        LinearLayout layout;



        public MyHolder(View itemView) {
            super(itemView);
            weathTemp=(TextView)itemView.findViewById(R.id.weathText);
            weathIcon=(ImageView)itemView.findViewById(R.id.weathIcon);
            weathDate=(TextView)itemView.findViewById(R.id.weathDate);
            minTemp=(TextView)itemView.findViewById(R.id.minTemp);
            maxTemp=(TextView)itemView.findViewById(R.id.maxTemp);
            layout=(LinearLayout)itemView.findViewById(R.id.layout);



        }


        public void cancelAnimation() {
            layout.clearAnimation();
        }
    }

    @Override
    public WeatherAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View v= LayoutInflater.from(mContext).inflate(R.layout.weather_view,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {



        WeatherListResponse.Forecast.SimpleForcast.ForecastDay m=mData.get(position);
        holder.weathTemp.setText(m.getConditions());
        holder.weathDate.setText(m.getDate().getWeekday());
        holder.minTemp.setText(Html.fromHtml(m.getLow().getCelsius()+"<small><small><sup>o</sup></small></small>" +"C"));
        holder.maxTemp.setText(Html.fromHtml(m.getHigh().getCelsius()+"<small><small><sup>o</sup></small></small>" +"C"));
        Picasso.with(mContext).load(m.getIcon_url()).into(holder.weathIcon);
        startAnimation(holder.layout,position);
    }

    private void startAnimation(View layout, int position) {
        if(position>lastPosition){
            Animation a=AnimationUtils.loadAnimation(mContext,android.R.anim.slide_in_left);
            layout.startAnimation(a);
            lastPosition=position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyHolder holder) {
        holder.cancelAnimation();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
