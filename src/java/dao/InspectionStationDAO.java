package dao;

import model.InspectionStation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InspectionStationDAO {

    public static ArrayList<InspectionStation> getAllStations() {
        ArrayList<InspectionStation> stationList = new ArrayList<>();
        DBContext db = DBContext.getInstance();

        try {
            String sql = "SELECT * FROM InspectionStations";
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                InspectionStation station = new InspectionStation();
                station.setStationId(rs.getInt("StationID"));
                station.setName(rs.getString("Name"));
                station.setAddress(rs.getString("Address"));
                station.setPhone(rs.getString("Phone"));
                station.setEmail(rs.getString("Email"));
                stationList.add(station);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stationList;
    }
} 
