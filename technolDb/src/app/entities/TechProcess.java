package app.entities;

public class TechProcess {
    private int tprId;
    private int prodId;
    private String company;
    private String developer;
    private String supervisor;
    private String approver;
    private String workpieceCode;
    private String workpieceSort;
    private String workpieceProfile;
    private String workpieceSizes;
    private float workpieceWeight;

    public int getTprId() {
        return tprId;
    }

    public void setTprId(int newTprId) {
        this.tprId = newTprId;
    }
    
    public int getProdId() {
        return prodId;
    }

    public void setProdId(int newProdId) {
        this.prodId = newProdId;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String newCompany) {
        this.company = newCompany;
    }
    
    public String getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(String newDeveloper) {
        this.developer = newDeveloper;
    }

    public String getSupervisor() {
    	return this.supervisor;
    }

    public void setSupervisor(String newSupervisor) {
        this.supervisor = newSupervisor;
    }
    
    public String getApprover() {
        return this.approver;
    }
 
    public void setApprover(String newApprover) {
        this.approver = newApprover;
    }
    
    public String getWorkpieceCode() {
        return this.workpieceCode;
    }
 
    public void setWorkpieceCode(String newWorkpieceCode) {
        this.workpieceCode = newWorkpieceCode;
    }
    
    public String getWorkpieceSort() {
        return this.workpieceSort;
    }
 
    public void setWorkpieceSort(String newWorkpieceSort) {
        this.workpieceSort = newWorkpieceSort;
    }
    
    public String getWorkpieceProfile() {
        return this.workpieceProfile;
    }
 
    public void setWorkpieceProfile(String newWorkpieceProfile) {
        this.workpieceProfile = newWorkpieceProfile;
    }
    
    public String getWorkpieceSizes() {
        return this.workpieceSizes;
    }
 
    public void setWorkpieceSizes(String newWorkpieceSizes) {
        this.workpieceSizes = newWorkpieceSizes;
    }
    
    public float getWorkpieceWeight() {
        return this.workpieceWeight;
    }
 
    public void setWorkpieceWeight(float newWorkpieceWeight) {
        this.workpieceWeight = newWorkpieceWeight;
    }
}