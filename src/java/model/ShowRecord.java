package model;

public class ShowRecord {
    // class java nay duoc tao de hien thi record theo yeu cau cua techlead lay thuoc tinh cua 2 bang user va inspection record
    private String fullName; // thuoc tinh cua bang user
    private String inspectionDate;
    private String result;
    private double co2Emission;
    private double hcEmission;
    private String comments;

    public ShowRecord() {
    }

    public ShowRecord(String fullName, String inspectionDate,
            String result, double co2Emission,
            double hcEmission, String comments) {
        this.fullName = fullName;
        this.inspectionDate = inspectionDate;
        this.result = result;
        this.co2Emission = co2Emission;
        this.hcEmission = hcEmission;
        this.comments = comments;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    @Override
    public String toString() {
        return "ShowRecord{"
                + "fullName="
                + fullName + ","
                + " inspectionDate="
                + inspectionDate + ", "
                + "result=" + result + ","
                + " co2Emission=" + co2Emission
                + ", hcEmission=" + hcEmission
                + ", comments=" + comments
                + '}';
    }

}
