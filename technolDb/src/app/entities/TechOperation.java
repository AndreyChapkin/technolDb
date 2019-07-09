package app.entities;

public class TechOperation {
    private int operId;
    private int tprId;
    private String operName;
    private String workshopNum;
    private String areaNum;
    private String operNum;
    private String equipment;
    private String cooling;
    private int numOfDetails;
    private float basicTime;
    private float subsidTime;
    private float pieceTime;
    private byte[] sketch;
    private String sketchName;

    public int getOperId() {
        return operId;
    }

    public void setOperId(int newOperId) {
        this.operId = newOperId;
    }
    
    public int getTprId() {
        return tprId;
    }

    public void setTprId(int newTprId) {
        this.tprId = newTprId;
    }

    public String getOperName() {
        return this.operName;
    }

    public void setOperName(String newOperName) {
        this.operName = newOperName;
    }
    
    public String getWorkshopNum() {
        return this.workshopNum;
    }

    public void setWorkshopNum(String newWorkshopNum) {
        this.workshopNum = newWorkshopNum;
    }

    public String getAreaNum() {
    	return this.areaNum;
    }

    public void setAreaNum(String newAreaNum) {
        this.areaNum = newAreaNum;
    }
    
    public String getOperNum() {
        return this.operNum;
    }
 
    public void setOperNum(String newOperNum) {
        this.operNum = newOperNum;
    }
    
    public String getEquipment() {
        return this.equipment;
    }
 
    public void setEquipment(String newEquipment) {
        this.equipment = newEquipment;
    }
    
    public String getCooling() {
        return this.cooling;
    }
 
    public void setCooling(String newCooling) {
        this.cooling = newCooling;
    }
    
    public int getNumOfDetails() {
        return this.numOfDetails;
    }
 
    public void setNumOfDetails(int newNumOfDetails) {
        this.numOfDetails = newNumOfDetails;
    }
    
    public float getBasicTime() {
        return this.basicTime;
    }
 
    public void setBasicTime(float newBasicTime) {
        this.basicTime = newBasicTime;
    }
    
    public float getSubsidTime() {
        return this.subsidTime;
    }
 
    public void setSubsidTime(float newSubsidTime) {
        this.subsidTime = newSubsidTime;
    }
    
    public float getPieceTime() {
        return this.pieceTime;
    }
 
    public void setPieceTime(float newPieceTime) {
        this.pieceTime = newPieceTime;
    }
    
    public byte[] getSketch() {
        return this.sketch;
    }
 
    public void setSketch(byte[] newSketch) {
        this.sketch = newSketch;
    }
    
    public String getSketchName() {
        return this.sketchName;
    }

    public void setSketchName(String newSketchName) {
        this.sketchName = newSketchName;
    }
}
