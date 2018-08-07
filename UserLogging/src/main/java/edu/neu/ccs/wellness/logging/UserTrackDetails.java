package edu.neu.ccs.wellness.logging;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.database.Exclude;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by RAJ on 7/15/2018.
 */

public class UserTrackDetails implements UserTrackingInfoInterface {

    private String eventName;
    private String date;
    private String timestamp;
    private Map<String, Object> eventParams;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public UserTrackDetails(String eventName, @Nullable Bundle eventParameters){
        Date date = new Date();
        this.eventName = eventName;
        this.date = date.toString();
        this.timestamp = String.valueOf(date.getTime());

        if (eventParameters == null) {
            this.eventParams = null;
        } else {
            this.eventParams = getEventParamsMapFromBundle(eventParameters);
        }
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override @Exclude
    public Bundle getEventParameters() {
        if (eventParams == null) {
            return new Bundle();
        } else {
            return getBundleFromParamsMap(eventParams);
        }
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getEventParams() { return eventParams;}

    @Exclude
    public String getDateString() {
        Timestamp stamp = new Timestamp(Long.parseLong(this.timestamp));
        Date date = new Date(stamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /* HELPER METHODS */
    private static Map<String, Object> getEventParamsMapFromBundle(Bundle eventParametersBundle) {
        Map<String, Object> eventParametersMap = new HashMap<>();
        for (String key : eventParametersBundle.keySet()) {
            eventParametersMap.put(key, eventParametersBundle.get(key));
        }
        return eventParametersMap;
    }

    private static Bundle getBundleFromParamsMap(Map<String, Object> eventParametersMap) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Object> entry : eventParametersMap.entrySet()) {
            bundle.putString(entry.getKey(), (String) entry.getValue());
        }
        return bundle;
    }

}
