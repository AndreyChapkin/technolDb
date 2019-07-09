package app.entities;

public class TechTransit {
    private int tranId;
    private int operId;
    private String transitNum;
    private String transitCont;
    private String subsidTool;
    private String cutTool;
    private String measTool;
    private float width;
    private float length;
    private float depth;
    private int numOfSteps;
    private float feed;
    private float rotSpeed;
    private float cutSpeed;

    public int getTranId() {
        return tranId;
    }

    public void setTranId(int newTranId) {
        this.tranId = newTranId;
    }
    
    public int getOperId() {
        return operId;
    }

    public void setOperId(int newOperId) {
        this.operId = newOperId;
    }

    public String getTransitNum() {
        return this.transitNum;
    }

    public void setTransitNum(String newTransitNum) {
        this.transitNum = newTransitNum;
    }
    
    public String getTransitCont() {
        return this.transitCont;
    }

    public void setTransitCont(String newTransitCont) {
        this.transitCont = newTransitCont;
    }

    public String getSubsidTool() {
    	return this.subsidTool;
    }

    public void setSubsidTool(String newSubsidTool) {
        this.subsidTool = newSubsidTool;
    }
    
    public String getCutTool() {
        return this.cutTool;
    }
 
    public void setCutTool(String newCutTool) {
        this.cutTool = newCutTool;
    }
    
    public String getMeasTool() {
        return this.measTool;
    }
 
    public void setMeasTool(String newMeasTool) {
        this.measTool = newMeasTool;
    }
    
    public float getWidth() {
        return this.width;
    }
 
    public void setWidth(float newWidth) {
        this.width = newWidth;
    }
    
    public float getLength() {
        return this.length;
    }
 
    public void setLength(float newLength) {
        this.length = newLength;
    }
    
    public float getDepth() {
        return this.depth;
    }
 
    public void setDepth(float newDepth) {
        this.depth = newDepth;
    }
    
    public int getNumOfSteps() {
        return this.numOfSteps;
    }
 
    public void setNumOfSteps(int newNumOfSteps) {
        this.numOfSteps = newNumOfSteps;
    }
    
    public float getFeed() {
        return this.feed;
    }
 
    public void setFeed(float newFeed) {
        this.feed = newFeed;
    }
    
    public float getRotSpeed() {
        return this.rotSpeed;
    }
 
    public void setRotSpeed(float newRotSpeed) {
        this.rotSpeed = newRotSpeed;
    }
    
    public float getCutSpeed() {
        return this.cutSpeed;
    }
 
    public void setCutSpeed(float newCutSpeed) {
        this.cutSpeed = newCutSpeed;
    }
}
