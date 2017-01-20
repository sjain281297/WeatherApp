package com.example.shubhamjain.mapsapp.HourlyForecast;

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
 * Created by SHUBHAM JAIN on 31-08-2016.
 */
public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.OurHolder> {
    ArrayList<HourlyForecastResponse.HourlyForecast> mData;
    Context mContext;
    OnItemClickListener listener;
    private int lastPosition = -1;

    public interface OnItemClickListener {
        void onItemClick(HourlyForecastResponse.HourlyForecast item, int pos);
    }


    public HourlyAdapter(ArrayList<HourlyForecastResponse.HourlyForecast> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    class OurHolder extends RecyclerView.ViewHolder {

        TextView weathCondition;
        ImageView weathImage;
        TextView time;
        TextView temp;
        TextView humid;
        LinearLayout container;



        public OurHolder(View itemView) {
            super(itemView);
            weathCondition=(TextView)itemView.findViewById(R.id.weathCondition);
            weathImage=(ImageView)itemView.findViewById(R.id.weathImage);
            time=(TextView)itemView.findViewById(R.id.time);
            temp=(TextView)itemView.findViewById(R.id.temp);

            container=(LinearLayout)itemView.findViewById(R.id.container);

            }


        public void clearAnimation() {
            container.clearAnimation();
        }
    }

    @Override
    public OurHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View v= LayoutInflater.from(mContext).inflate(R.layout.hourly_view,parent,false);
        return new OurHolder(v);
    }

    @Override
    public void onBindViewHolder(OurHolder holder, int position) {
        holder.weathCondition.setText(mData.get(position).getCondition());
        holder.temp.setText(Html.fromHtml(mData.get(position).getTemp().getMetric()+"<small><sup>o</sup></small>"+"C"));
        holder.time.setText(mData.get(position).getFCTTIME().getPretty());
        Picasso.with(mContext).load(mData.get(position).getIcon_url()).into(holder.weathImage);
        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            animation.setInterpolator(mContext,android.R.anim.anticipate_interpolator);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public void onViewDetachedFromWindow(OurHolder holder) {
        holder.clearAnimation();
    }
}
