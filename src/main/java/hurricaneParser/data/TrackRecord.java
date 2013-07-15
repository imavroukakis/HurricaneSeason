package hurricaneParser.data;


import org.joda.time.DateTime;

public class TrackRecord {

    private DateTime date;
    private RecordIdentifier recordIdentifier;
    private SystemStatus systemStatus;
    private String latitude;
    private String longtitude;
    private short maximumSustainedWind;
    private short minimumPressure;

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public RecordIdentifier getRecordIdentifier() {
        return recordIdentifier;
    }

    public void setRecordIdentifier(RecordIdentifier recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
    }

    public SystemStatus getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(SystemStatus systemStatus) {
        this.systemStatus = systemStatus;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public short getMaximumSustainedWind() {
        return maximumSustainedWind;
    }

    public void setMaximumSustainedWind(short maximumSustainedWind) {
        this.maximumSustainedWind = maximumSustainedWind;
    }

    public short getMinimumPressure() {
        return minimumPressure;
    }

    public void setMinimumPressure(short minimumPressure) {
        this.minimumPressure = minimumPressure;
    }
}
