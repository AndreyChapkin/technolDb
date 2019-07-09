package app.entities;

import java.sql.Date;
import java.sql.Time;

public class Remark {
    private int remId;
    private int prodId;
    private Date remDate;
    private Time remTime;
    private String writer;
    private String post;
    private String remCompany;
    private String comment;
    private byte[] textFile;
    private String fileName;

    public int getRemId() {
        return remId;
    }

    public void setRemId(int newRemId) {
        this.remId = newRemId;
    }
    
    public int getProdId() {
        return prodId;
    }

    public void setProdId(int newProdId) {
        this.prodId = newProdId;
    }

    public Date getRemDate() {
        return this.remDate;
    }

    public void setRemDate(Date newRemDate) {
        this.remDate = newRemDate;
    }
    
    public Time getRemTime() {
        return this.remTime;
    }

    public void setRemTime(Time newRemTime) {
        this.remTime = newRemTime;
    }
    
    public String getWriter() {
        return this.writer;
    }

    public void setWriter(String newWriter) {
        this.writer = newWriter;
    }
    
    public String getPost() {
        return this.post;
    }

    public void setPost(String newPost) {
        this.post = newPost;
    }
    
    public String getRemCompany() {
        return this.remCompany;
    }

    public void setRemCompany(String newRemCompany) {
        this.remCompany = newRemCompany;
    }

    public String getComment() {
    	return this.comment;
    }

    public void setComment(String newComment) {
        this.comment = newComment;
    }
    
    public byte[] getTextFile() {
        return this.textFile;
    }
 
    public void setTextFile(byte[] newTextFile) {
        this.textFile = newTextFile;
    }
    
    public String getFileName() {
        return this.fileName;
    }
 
    public void setFileName(String newFileName) {
        this.fileName = newFileName;
    }
}
