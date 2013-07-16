package hurricane.util;


import hurricane.data.TrackRecord;

import java.util.Comparator;

public class Sort {


    private Sort() {
        throw new IllegalAccessError("Utility classes cannot be instantiated");
    }

    public static Comparator<TrackRecord> onMaxWindSpeed() {
        return new Comparator<TrackRecord>() {
            @Override
            public int compare(TrackRecord trackRecordOne, TrackRecord trackRecordTwo) {
                if (trackRecordOne.getMaximumSustainedWind() > trackRecordTwo.getMaximumSustainedWind()) {
                    return 1;
                } else if (trackRecordOne.getMaximumSustainedWind() == trackRecordTwo.getMaximumSustainedWind()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        };
    }
}
