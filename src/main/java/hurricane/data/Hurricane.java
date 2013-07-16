package hurricane.data;


import java.util.List;

public class Hurricane {

    private Basin basin;
    private String atcfNumber;
    private int year;
    private String name;
    private List<TrackRecord> tracks;

    public Basin getBasin() {
        return basin;
    }

    public void setBasin(Basin basin) {
        this.basin = basin;
    }

    public String getAtcfNumber() {
        return atcfNumber;
    }

    public void setAtcfNumber(String atcfNumber) {
        this.atcfNumber = atcfNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrackRecord> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackRecord> tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Hurricane) {
            return true;
        }

        Hurricane hurricane = (Hurricane) o;

        if (getYear() != hurricane.getYear()) {
            return false;
        }
        if (getAtcfNumber() != null ? !getAtcfNumber().equals(hurricane.getAtcfNumber()) : hurricane.getAtcfNumber() != null) {
            return false;
        }
        if (getBasin() != hurricane.getBasin()) {
            return false;
        }
        if (getName() != null ? !getName().equals(hurricane.getName()) : hurricane.getName() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = basin.hashCode();
        result = 31 * result + (atcfNumber != null ? atcfNumber.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hurricane{" +
                "name='" + name + '\'' +
                ", basin=" + basin +
                ", atcfNumber='" + atcfNumber + '\'' +
                ", year=" + year +
                '}';
    }
}

