package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.InspectionRequest;

public class InspectionRequestDAO {

    public static InspectionRequest addInspectionRequest(InspectionRequest request) {
        DBContext db = DBContext.getInstance();
        int result = 0;
        try {
            String sql = """
                        INSERT INTO InspectionRequests
                                                (VehicleID,StationID ,RequestedDate,Comments)
                                                VALUES (?, ?,GETDATE(),N'Yêu cầu đăng kí kiểm định')
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, request.getVehicleId());
            statement.setInt(2, request.getStationId());
            result = statement.executeUpdate();
        } catch (Exception e) {
            return null;
        }
        if(result!=0) return request;
        else return null;
    }

    public static InspectionRequest getInspectionRequestsByRequestID(int requestID) {
        InspectionRequest request = null;
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         select * from InspectionRequests where RequestID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, requestID);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                request = new InspectionRequest();
                request.setRequestId(rs.getInt("RequestID"));
                request.setVehicleId(rs.getInt("VehicleID"));
                request.setStationId(rs.getInt("StationID"));
                request.setRequestedDate(rs.getString("RequestedDate"));
                request.setPreferredDate(rs.getString("PreferredDate"));
                request.setStatus(rs.getString("Status"));
                request.setComments(rs.getString("Comments"));
            }
            return request;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public static ArrayList<InspectionRequest> getInspectionRequestsByUser(String userId) {
        DBContext db = DBContext.getInstance();
        ArrayList<InspectionRequest> requests = new ArrayList<>();
        try {
            String sql = """
                        SELECT ir.*, v.PlateNumber, v.Brand, v.Model
                        FROM InspectionRequests ir
                        JOIN Vehicles v ON ir.VehicleID = v.VehicleID
                        WHERE v.OwnerID = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                InspectionRequest request = new InspectionRequest();
                request.setRequestId(rs.getInt("RequestID"));
                request.setVehicleId(rs.getInt("VehicleID"));
                request.setStationId(rs.getInt("StationID"));
                request.setRequestedDate(rs.getString("RequestedDate"));
                request.setPreferredDate(rs.getString("PreferredDate"));
                request.setStatus(rs.getString("Status"));
                request.setComments(rs.getString("Comments"));
                requests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }
    public static void handleInspectionRequest(int requestId, String response) {
        if ("accept".equals(response)) {
            acceptInspectionRequest(requestId);
        } else if ("reject".equals(response)) {
            rejectInspectionRequest(requestId);
        }
    }

    private static void acceptInspectionRequest(int requestId) {
        updateRequestStatus(requestId, "Confirmed", "Bạn đã xác nhận lịch kiểm định");
        
        insertNewRequest(requestId, "Successfully", "Xác nhận lịch kiểm định thành công");
//        insertInspectionRecord(requestId);
    }

    private static void rejectInspectionRequest(int requestId) {
        updateRequestStatus(requestId, "Confirmed", "Bạn đã xác nhận lịch kiểm định");
        
        insertNewRequest(requestId, "Cancelled", "Huỷ lịch kiểm định thành công");
    }

    private static void updateRequestStatus(int requestId, String status, String message) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = "UPDATE InspectionRequests SET Status = ?, Comments = ? WHERE RequestID = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, status);
            statement.setString(2, message);
            statement.setInt(3, requestId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertNewRequest(int requestId, String status, String message) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                        INSERT INTO InspectionRequests (VehicleID, StationID, InspectorID, RequestedDate,PreferredDate ,Status, Comments)
                                                 SELECT VehicleID, StationID, InspectorID ,GETDATE(),    PreferredDate  , ?   , ?
                        FROM InspectionRequests
                        WHERE RequestID = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, status);
            statement.setString(2, message);
            statement.setInt(3, requestId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertInspectionRecord(int requestId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                        INSERT INTO InspectionRecords (RequestID, InspectionDate, Status)
                        VALUES (?, GETDATE(), 'Pending')
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, requestId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
