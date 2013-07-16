package hurricane;


import com.google.common.collect.Iterables;
import hurricane.data.Hurricane;
import hurricane.data.TrackRecord;
import hurricane.util.Filter;
import hurricane.util.HurricaneDataParser;
import hurricane.util.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class HurricaneSeason {

    private static final Logger LOG = LoggerFactory.getLogger(HurricaneSeason.class);

    public static void main(String[] args) throws IOException, IllegalDataException {

        if (missingOptions()) {
            LOG.error("Required properties -DdataFile,-DregexFile,-Dyear missing");
            System.exit(-1);
        }

        File dataFile = new File(System.getProperty("dataFile"));
        Properties properties = new Properties();
        properties.load(new FileReader(System.getProperty("regexFile")));
        int year = Integer.valueOf(System.getProperty("year"));
        List<Hurricane> hurricanes = HurricaneDataParser.fromFile(dataFile, properties).parseToList();
        Iterable<Hurricane> hurricaneIterable = Filter.onYear(hurricanes, year, false);

        for (Hurricane hurricane : hurricaneIterable) {
            Collections.sort(hurricane.getTracks(), Sort.onMaxWindSpeed());
            TrackRecord trackRecord = Iterables.getLast(hurricane.getTracks());
            LOG.info(String.format("%-90s  Max wind: %.2f km/h (%d kt) recorded at %s",
                    hurricane,
                    trackRecord.getMaximumSustainedWindInKmh(),
                    trackRecord.getMaximumSustainedWind(),
                    trackRecord.getDate()));
        }
    }

    private static boolean missingOptions() {
        return !System.getProperties().containsKey("dataFile") || !System.getProperties().containsKey("regexFile")
                || !System.getProperties().containsKey("year");
    }
}