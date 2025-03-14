package model;


public class InspectionRequest {
    private int requestId;
    private int vehicleId;
    private Integer stationId;
    private String requestedDate;
    private String preferredDate;
    private String inspectorId;
    private String status;
    private String comments;

    public InspectionRequest() {
    }

    public InspectionRequest(int requestId, int vehicleId, Integer stationId, String requestedDate, String preferredDate, String status, String comments) {
        this.requestId = requestId;
        this.vehicleId = vehicleId;
        this.stationId = stationId;
        this.requestedDate = requestedDate;
        this.preferredDate = preferredDate;
        this.status = status;
        this.comments = comments;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(String preferredDate) {
        this.preferredDate = preferredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "InspectionRequest{" + "requestId=" + requestId + ", vehicleId=" + vehicleId + ", stationId=" + stationId + ", requestedDate=" + requestedDate + ", preferredDate=" + preferredDate + ", status=" + status + ", comments=" + comments + '}';
    }
    
    
}
