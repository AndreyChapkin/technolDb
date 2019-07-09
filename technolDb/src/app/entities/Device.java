package app.entities;

public class Device {
    private int devId;
    private int operId;
    private String devName;
    private String devCode;

    public int getDevId() {
        return devId;
    }

    public void setDevId(int newDevId) {
        this.devId = newDevId;
    }
    
    public int getOperId() {
        return operId;
    }

    public void setOperId(int newOperId) {
        this.operId = newOperId;
    }

    public String getDevName() {
        return this.devName;
    }

    public void setDevName(String newDevName) {
        this.devName = newDevName;
    }
    
    public String getDevCode() {
        return this.devCode;
    }

    public void setDevCode(String newDevCode) {
        this.devCode = newDevCode;
    }
}
