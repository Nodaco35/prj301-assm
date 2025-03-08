/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NC PC
 */
public class Vehicle {
    private int vehicleId;
    private String ownerId;
    private String plateNumber;
    private String brand;
    private String model;
    private int manufactureYear;
    private String engineNumber;

    public Vehicle() {
    }

    public Vehicle(int vehicleId, String ownerId, String plateNumber, String brand, String model, int manufactureYear, String engineNumber) {
        this.vehicleId = vehicleId;
        this.ownerId = ownerId;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.engineNumber = engineNumber;
    }
    public Vehicle(String plateNumber, String brand, String model, int manufactureYear, String engineNumber) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.engineNumber = engineNumber;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle{");
        sb.append("vehicleId=").append(vehicleId);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", plateNumber=").append(plateNumber);
        sb.append(", brand=").append(brand);
        sb.append(", model=").append(model);
        sb.append(", manufactureYear=").append(manufactureYear);
        sb.append(", engineNumber=").append(engineNumber);
        sb.append('}');
        return sb.toString();
    }
    
    
}
