package hurricaneParser;


import com.google.common.collect.Iterables;
import hurricaneParser.data.Hurricane;
import hurricaneParser.data.TrackRecord;
import hurricaneParser.util.Filter;
import hurricaneParser.util.HurricaneDataParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class HurricaneSeason {

    public static void main(String[] args) throws IOException, IllegalDataException {

        File dataFile = new File(args[0]);
        Properties properties = new Properties();
        properties.load(new FileReader(args[1]));
        int year = Integer.valueOf(args[2]);
        List<Hurricane> hurricanes = HurricaneDataParser.fromFile(dataFile, properties).parseToList();
        Iterable<Hurricane> hurricaneIterable = Filter.onYear(hurricanes, year, false);

        for (Hurricane hurricane : hurricaneIterable) {
            Collections.sort(hurricane.getTracks(), new Comparator<TrackRecord>() {
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
            });
            System.out.println(String.format("%-90s  Max wind in kmh: %.2f recorded at %s",
                    hurricane,
                    Iterables.getLast(hurricane.getTracks()).getMaximumSustainedWindInKmh(),
                    Iterables.getLast(hurricane.getTracks()).getDate()));
        }
    }
}