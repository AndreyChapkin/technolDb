package app.entities;

public class TechControl {
    private int ctrId;
    private int operId;
    private String ctrName;
    private String ctrDeveloper;
    private String ctrSupervisor;
    private String ctrEquipment;
    private String safeInstr;
    private float ctrBasicTime;
    private float ctrSubsidTime;

    public int getCtrId() {
        return ctrId;
    }

    public void setCtrId(int newCtrId) {
        this.ctrId = newCtrId;
    }
    
    public int getOperId() {
        return operId;
    }

    public void setOperId(int newOperId) {
        this.operId = newOperId;
    }

    public String getCtrName() {
        return this.ctrName;
    }

    public void setCtrName(String newCtrName) {
        this.ctrName = newCtrName;
    }
    
    public String getCtrDeveloper() {
        return this.ctrDeveloper;
    }

    public void setCtrDeveloper(String newCtrDeveloper) {
        this.ctrDeveloper = newCtrDeveloper;
    }

    public String getCtrSupervisor() {
    	return this.ctrSupervisor;
    }

    public void setCtrSupervisor(String newCtrSupervisor) {
        this.ctrSupervisor = newCtrSupervisor;
    }
    
    public String getCtrEquipment() {
        return this.ctrEquipment;
    }
 
    public void setCtrEquipment(String newCtrEquipment) {
        this.ctrEquipment = newCtrEquipment;
    }
    
    public String getSafeInstr() {
        return this.safeInstr;
    }
 
    public void setSafeInstr(String newSafeInstr) {
        this.safeInstr = newSafeInstr;
    }
    
    public float getCtrBasicTime() {
        return this.ctrBasicTime;
    }
 
    public void setCtrBasicTime(float newCtrBasicTime) {
        this.ctrBasicTime = newCtrBasicTime;
    }
    
    public float getCtrSubsidTime() {
        return this.ctrSubsidTime;
    }
 
    public void setCtrSubsidTime(float newCtrSubsidTime) {
        this.ctrSubsidTime = newCtrSubsidTime;
    }
}
