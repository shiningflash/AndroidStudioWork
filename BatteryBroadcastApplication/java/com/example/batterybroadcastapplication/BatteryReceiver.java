package com.example.batterybroadcastapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent) {
        TextView status = ((MainActivity)context).findViewById(R.id.status);
        TextView percentage = ((MainActivity)context).findViewById(R.id.percentage);
        ImageView battery = ((MainActivity)context).findViewById(R.id.battery);

        String action = intent.getAction();

        if (action != null && action.equals(Intent.ACTION_BATTERY_CHANGED)) {

            // Battery Status
            int bat_status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            Resources res = context.getResources();
            String message = "";

            switch(bat_status) {
                case BatteryManager.BATTERY_STATUS_FULL:
                    message = "Your Battery is FULL";
                    battery.setImageDrawable(res.getDrawable(R.drawable.battery));
                    break;
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    message = "Charging";
                    battery.setImageDrawable(res.getDrawable(R.drawable.charge_bat));
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    message = "Discharging";
                    battery.setImageDrawable(res.getDrawable(R.drawable.battery));
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    message = "Not Charging";
                    battery.setImageDrawable(res.getDrawable(R.drawable.battery));
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    message = "Unknown Battery Status";
                    battery.setImageDrawable(res.getDrawable(R.drawable.battery));
                    break;
            }
            status.setText(message);

            // Battery Percentage
            int bat_level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int bat_scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int bat_percentage = bat_level * 100 / bat_scale;
            percentage.setText(bat_percentage + "%");
        }
    }
}
