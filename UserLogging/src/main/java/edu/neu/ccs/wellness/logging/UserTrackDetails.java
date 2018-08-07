package edu.neu.ccs.wellness.logging;

import android.os.Bundle;

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
    private Map<String, Object> eventParameters;

    public UserTrackDetails(String eventName, Bundle eventParameters){
            this.eventName = eventName;
            this.eventParameters = getEventParamsMapFromBundle(eventParameters);
            this.date = new Date().toString();
            this.timestamp = String.valueOf(new Date().getTime());
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public Bundle getEventParameters() {
        return getBundleFromParamsMap(eventParameters);
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getEventParametersMap() { return eventParameters;}

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
