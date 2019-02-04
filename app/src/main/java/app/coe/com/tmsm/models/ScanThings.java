package app.coe.com.tmsm.models;

public class ScanThings {

    private String nameThings;
    private String macAddress;


    public ScanThings(String nameThings, String macAddress) {
        this.nameThings = nameThings;
        this.macAddress = macAddress;
    }

    public String getNameThings() {
        return nameThings;
    }

    public void setNameThings(String nameThings) {
        this.nameThings = nameThings;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
