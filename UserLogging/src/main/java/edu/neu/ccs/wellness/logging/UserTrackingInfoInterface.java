package edu.neu.ccs.wellness.logging;

import android.os.Bundle;

/**
 * Created by hermansaksono on 7/26/18.
 */

public interface UserTrackingInfoInterface {

    String getEventName();

    String getDate();

    Bundle getEventParameters();

    String getTimestamp();
}
