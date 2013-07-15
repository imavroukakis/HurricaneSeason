package hurricaneParser.data;

public enum RecordIdentifier {
    LANDFALL("L"),
    CENTRAL_PRESSURE_MINIMUM("P"),
    PEAK_INTENSITY("I"),
    STATUS_CHANGE("S"),
    ADDITIONAL_TRACK("T"),
    UNDEFINED("UNDEFINED");

    private String shortValue;

    RecordIdentifier(String shortValue) {
        this.shortValue = shortValue;
    }

    public static RecordIdentifier fromValue(String shortValue) {
        for (RecordIdentifier recordIdentifier : values()) {
            if (recordIdentifier.shortValue.equals(shortValue)) {
                return recordIdentifier;
            }
        }
        return UNDEFINED;
    }
}
