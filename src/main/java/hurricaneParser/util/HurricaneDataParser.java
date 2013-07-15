package hurricaneParser.util;

import hurricaneParser.IllegalDataException;
import hurricaneParser.data.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HurricaneDataParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(HurricaneDataParser.class);
    private final Pattern headerPattern;
    private final Pattern dataPattern;
    private final DateTimeFormatter dateTimeFormatter;

    private File dataFile;

    private HurricaneDataParser(File dataFile, Properties properties) {
        this.dataFile = dataFile;
        headerPattern = Pattern.compile(properties.getProperty("header"));
        dataPattern = Pattern.compile(properties.getProperty("data"));
        dateTimeFormatter = DateTimeFormat.forPattern(properties.getProperty("dateTimeFormat")).withZone(DateTimeZone.UTC);
    }

    public static HurricaneDataParser fromFile(File dataFile, Properties regexProperties) {
        checkFileValidity(dataFile);
        return new HurricaneDataParser(dataFile, regexProperties);
    }

    private static void checkFileValidity(File dataFile) {
        if (dataFile == null) {
            throw new NullPointerException("Null data file");
        }
        if (!dataFile.exists() || dataFile.isDirectory()) {
            throw new IllegalStateException("Invalid data file");
        }
    }

    public List<Hurricane> parse() throws IllegalDataException {
        List<Hurricane> hurricanes = new ArrayList<Hurricane>();
        Scanner scanner = newScannerInstance();
        try {
            Hurricane hurricane = null;
            while (scanner.hasNextLine()) {
                String dataLine = scanner.nextLine();
                if (isHeader(dataLine)) {
                    hurricane = parseHeader(dataLine);
                    hurricanes.add(hurricane);
                } else {
                    TrackRecord trackRecord = parseData(dataLine);
                    hurricane.getTracks().add(trackRecord);
                }
            }
        } finally {
            scanner.close();
        }

        return hurricanes;
    }

    private Scanner newScannerInstance() {
        try {
            return new Scanner(new BufferedReader(new FileReader(dataFile)));
        } catch (FileNotFoundException fnfe) {
            throw new IllegalStateException("Data file has dissapeared");
        }
    }

    protected Hurricane parseHeader(String dataLine) throws IllegalDataException {
        Hurricane hurricane = new Hurricane();
        Matcher matcher = headerPattern.matcher(dataLine);
        if (matcher.matches()) {
            hurricane.setBasin(Basin.fromValue(matcher.group(1)));
            hurricane.setAtcfNumber(matcher.group(2));
            hurricane.setYear(Integer.valueOf(matcher.group(3)));
            hurricane.setName(matcher.group(4).trim());
            hurricane.setTracks(new ArrayList<TrackRecord>(Integer.valueOf(matcher.group(5).trim())));
            return hurricane;
        }
        throw new IllegalDataException(String.format("%s is not a valid header line", dataLine));

    }

    private boolean isHeader(String dataLine) {
        return headerPattern.matcher(dataLine).matches();
    }

    protected TrackRecord parseData(String dataLine) throws IllegalDataException {
        TrackRecord trackRecord = new TrackRecord();
        Matcher matcher = dataPattern.matcher(dataLine);
        if (matcher.matches()) {
            DateTime dateTime = dateTimeFormatter.parseDateTime(matcher.group(1));
            trackRecord.setDate(dateTime);
            trackRecord.setRecordIdentifier(RecordIdentifier.fromValue(matcher.group(2)));
            trackRecord.setSystemStatus(SystemStatus.fromValue(matcher.group(3)));
            trackRecord.setLatitude(matcher.group(4));
            trackRecord.setLongtitude(matcher.group(5));
            trackRecord.setMaximumSustainedWind(Short.valueOf(matcher.group(6).trim()));
            trackRecord.setMinimumPressure(Short.valueOf(matcher.group(7).trim()));
            return trackRecord;
        }
        throw new IllegalDataException(String.format("%s is not a valid data line", dataLine));
    }
}
