package app.entities;

public class DesignDocument {
    private int desId;
    private int prodId;
    private String docType;
    private String docSign;
    private byte[] document;
    private String docFileName;
    private String docDeveloper;
    private String docSupervisor;
    private String docApprover;
    private String companyName;

    public int getDesId() {
        return desId;
    }

    public void setDesId(int newDesId) {
        this.desId = newDesId;
    }
    
    public int getProdId() {
        return prodId;
    }

    public void setProdId(int newProdId) {
        this.prodId = newProdId;
    }

    public String getDocType() {
        return this.docType;
    }

    public void setDocType(String newDocType) {
        this.docType = newDocType;
    }
    
    public String getDocSign() {
        return this.docSign;
    }

    public void setDocSign(String newDocSign) {
        this.docSign = newDocSign;
    }

    public byte[] getDocument() {
    	return this.document;
    }

    public void setDocument(byte[] newDocument) {
        this.document = newDocument;
    }
    
    public String getDocFileName() {
        return this.docFileName;
    }
 
    public void setDocFileName(String newDocFileName) {
        this.docFileName = newDocFileName;
    }
    
    public String getDocDeveloper() {
        return this.docDeveloper;
    }
 
    public void setDocDeveloper(String newDocDeveloper) {
        this.docDeveloper = newDocDeveloper;
    }
    
    public String getDocSupervisor() {
        return this.docSupervisor;
    }
 
    public void setDocSupervisor(String newDocSupervisor) {
        this.docSupervisor = newDocSupervisor;
    }
    
    public String getDocApprover() {
        return this.docApprover;
    }
 
    public void setDocApprover(String newDocApprover) {
        this.docApprover = newDocApprover;
    }
    
    public String getCompanyName() {
        return this.companyName;
    }
 
    public void setCompanyName(String newCompanyName) {
        this.companyName = newCompanyName;
    }
}