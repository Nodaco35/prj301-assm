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
                request.setStatus(rs.getString("Status"));
                request.setComments(rs.getString("Comments"));
                requests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }
}
