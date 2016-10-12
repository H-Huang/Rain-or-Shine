package com.aych.stormy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aych.stormy.R;
import com.aych.stormy.weather.Hour;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HowardHuang on 6/28/2015.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder>{

    private Hour[] mHours;
    private Context mContext;

    public HourAdapter(Context context, Hour[] hours){
        mContext = context;
        mHours = hours;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false);
        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHours[position]);
    }

    @Override
    public int getItemCount() {
        return mHours.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @InjectView(R.id.timeLabel) TextView mTimeLabel;
        @InjectView(R.id.summaryLabel) TextView mSummaryLabel;
        @InjectView(R.id.temperatureLabel) TextView mTemperatureLabel;
        @InjectView(R.id.iconImageView) ImageView mIconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(this);
            /* Without ButterKnife!
            mTimeLabel = (TextView)itemView.findViewById(R.id.timeLabel);
            mSummaryLabel = (TextView)itemView.findViewById(R.id.summaryLabel);
            mTemperatureLabel = (TextView)itemView.findViewById(R.id.temperatureLabel);
            mIconImageView = (ImageView)itemView.findViewById(R.id.iconImageView);*/
        }

        public void bindHour(Hour hour){
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() + "");
            mIconImageView.setImageResource(hour.getIconId());

        }

        @Override
        public void onClick(View v) {
                String time = mTimeLabel.getText().toString();
                String temperature = mTemperatureLabel.getText().toString();
                String summary = mSummaryLabel.getText().toString();
                String message = String.format("At %s it will be %s and %s", time, temperature,
                        summary);
                Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
            }
        }
    }
