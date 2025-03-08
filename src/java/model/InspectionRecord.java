/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NC PC
 */
public class InspectionRecord {
    private int recordId;
    private int vehicleId;
    private int stationId;
    private int inspectorId;
    private String inspectionDate;
    private String resultId;
    private double co2Emission;
    private double hcEmission;
    private String comments;

    public InspectionRecord() {
    }

    public InspectionRecord(int recordId, int vehicleId, int stationId, int inspectorId, String inspectionDate, String resultId, double co2Emission, double hcEmission, String comments) {
        this.recordId = recordId;
        this.vehicleId = vehicleId;
        this.stationId = stationId;
        this.inspectorId = inspectorId;
        this.inspectionDate = inspectionDate;
        this.resultId = resultId;
        this.co2Emission = co2Emission;
        this.hcEmission = hcEmission;
        this.comments = comments;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(int inspectorId) {
        this.inspectorId = inspectorId;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public double getCo2Emission() {
        return co2Emission;
    }

    public void setCo2Emission(double co2Emission) {
        this.co2Emission = co2Emission;
    }

    public double getHcEmission() {
        return hcEmission;
    }

    public void setHcEmission(double hcEmission) {
        this.hcEmission = hcEmission;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
