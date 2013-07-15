package hurricaneParser.data;


public enum Basin {
    NORTH_EAST_PACIFIC("EP"), NORTH_CENTRAL_PACIFIC("CP"), UNDEFINED("UNDEFINED");

    private String shortName;

    Basin(String shortName) {
        this.shortName = shortName;
    }

    public static Basin fromValue(String shortName) {
        for (Basin basin : values()) {
            if (basin.shortName.equals(shortName)) {
                return basin;
            }
        }
        return UNDEFINED;
    }
}
