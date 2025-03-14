package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.InspectionRecord;
import model.ShowRecord;

/**
 *
 * @author Dai Hoang
 */
public class InspectionRecordDAO {

    public static ArrayList<ShowRecord> getRecordByPlateNumber(String plateNumber) {
        DBContext db = DBContext.getInstance();
        ArrayList<ShowRecord> records = new ArrayList<>();
        try {
            String sql = """
                       SELECT FullName,InspectionDate,Result,CO2Emission,HCEmission,Comments
                       FROM dbo.InspectionRecords JOIN dbo.Vehicles ON Vehicles.VehicleID = InspectionRecords.VehicleID JOIN dbo.Users ON Users.UserID = InspectionRecords.InspectorID
                       WHERE PlateNumber=?
                       """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, plateNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ShowRecord record = new ShowRecord(rs.getString("FullName"), rs.getString("InspectionDate"), rs.getString("Result"), rs.getDouble("CO2Emission"), rs.getDouble("HCEmission"), rs.getString("Comments"));
                records.add(record);
            }
        } catch (Exception e) {
            return null;
        }
        return records;
    }

}
