package hurricane.data;


public enum SystemStatus {
    TROPICAL_DEPRESSION("TD"),
    TROPICAL_STORM("TS"),
    HURRICANE("HU"),
    EXTRA_TROPICAL("EX"),
    SUBTROPICAL_DEPRESSION("SD"),
    SUBTROPICAL_STORM("SS"),
    LOW("LO"),
    DISTURBANCE("DB"),
    UNDEFINED("UNDEFINED");


    private String shortStatus;

    SystemStatus(String shortStatus) {

        this.shortStatus = shortStatus;
    }

    public static SystemStatus fromValue(String shortStatus) {
        for (SystemStatus systemStatus : values()) {
            if (systemStatus.shortStatus.equals(shortStatus)) {
                return systemStatus;
            }
        }
        return UNDEFINED;
    }
}
