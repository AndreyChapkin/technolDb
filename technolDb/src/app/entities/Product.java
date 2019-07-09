package app.entities;

public class Product {
    private int id;
    private String name;
    private String signName;
    private String material;
    public String materialBrand;
    private float weight;
    private int batchSize;

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
    
    public String getSignName() {
        return this.signName;
    }

    public void setSignName(String newSignName) {
        this.signName = newSignName;
    }

    public String getMaterial() {
    	return this.material;
    }

    public void setMaterial(String newMaterial) {
        this.material = newMaterial;
    }
    
    public String getMaterialBrand() {
        return this.materialBrand;
    }
 
    public void setMaterialBrand(String newMaterialBrand) {
        materialBrand = newMaterialBrand;
    }
    
    public float getWeight() {
        return this.weight;
    }
 
    public void setWeight(float newWeight) {
        this.weight = newWeight;
    }
    
    public int getBatchSize() {
        return this.batchSize;
    }
 
    public void setBatchSize(int newBatchSize) {
        this.batchSize = newBatchSize;
    }
}