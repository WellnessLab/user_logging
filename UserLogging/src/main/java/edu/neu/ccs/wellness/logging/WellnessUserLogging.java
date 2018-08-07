package edu.neu.ccs.wellness.logging;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hermansaksono on 7/11/18.
 */

public class WellnessUserLogging extends AbstractUserLogging {

    private static final String FIREBASE_USER_TRACKING_ROOT = "user_logging";
    private DatabaseReference databaseReference;
    private DatabaseReference userTrackingRoot;

    /**
     * Constructor.
     *
     * @param uid Anonymized user id. Must not contain any personally identifiable information.
     */
    public WellnessUserLogging(String uid) {
        super(uid);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        userTrackingRoot = databaseReference.child(FIREBASE_USER_TRACKING_ROOT);
    }

    /**
     * Logs an app event in Firebase under the user's uid and for the current timestamp in UTC.
     * Example: Suppose that uid = 717, and timestamp = 1531281600000 (2018-07-11 4:00:00 AM).
     * Calling logEvent("USER_REFLECTION") will add the following entry to Firebase:
     * + user_logging
     * +---717
     * +-----2018-07-11
     * +-------1531281600000
     * +---------"eventName": "USER_REFLECTION"
     * +---------"date": "2018-07-11 4:00:00 AM"
     * +---------"timestamp": "1531281600000"
     * +---------"eventParameters": {}
     *
     *
     * @param eventName The name of the event. Should contain 1 to 40 alphanumeric characters or
     *                  underscores. The name must start with an alphabetic character.
     * @param eventParameters The map of event parameters. Passing null indicates that the event
     *                        has no parameters. Parameter names can be up to 40 characters long
     *                        and must start with an alphabetic character and contain only
     *                        alphanumeric characters and underscores.
     */
    @Override
    public void logEvent(String eventName, @Nullable Bundle eventParameters) {
        UserTrackDetails userTrackDetails = new UserTrackDetails(eventName, eventParameters);

        userTrackingRoot
                .child(this.getUid())
                .child(userTrackDetails.getDateString())
                .child(userTrackDetails.getTimestamp())
                .setValue(userTrackDetails);
    }
}
