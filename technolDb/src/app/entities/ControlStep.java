package app.entities;

public class ControlStep {
    private int stepId;
    private int ctrId;
    private String ctrParam;
    private String techEqName;
    private String techEqCode;
    private String baseSubTime;
    private int ctrValue;

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int newStepId) {
        this.stepId = newStepId;
    }
    
    public int getCtrId() {
        return ctrId;
    }

    public void setCtrId(int newCtrId) {
        this.ctrId = newCtrId;
    }

    public String getCtrParam() {
        return this.ctrParam;
    }

    public void setCtrParam(String newCtrParam) {
        this.ctrParam = newCtrParam;
    }
    
    public String getTechEqName() {
        return this.techEqName;
    }

    public void setTechEqName(String newTechEqName) {
        this.techEqName = newTechEqName;
    }

    public String getTechEqCode() {
    	return this.techEqCode;
    }

    public void setTechEqCode(String newTechEqCode) {
        this.techEqCode = newTechEqCode;
    }
    
    public String getBaseSubTime() {
        return this.baseSubTime;
    }
 
    public void setBaseSubTime(String newBaseSubTime) {
        this.baseSubTime = newBaseSubTime;
    }
    
    public int getCtrValue() {
        return this.ctrValue;
    }
 
    public void setCtrValue(int newCtrValue) {
        this.ctrValue = newCtrValue;
    }
}
