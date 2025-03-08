package dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Vehicle;

public class VehicleDAO {

    public static Vehicle getVehicleByPlateNumber(String plateNumber) {
        DBContext db = DBContext.getInstance();
        Vehicle vehicle = null;

        try {
            String sql = """
                        select * from Vehicles where PlateNumber = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, plateNumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("VehicleID"));
                vehicle.setOwnerId(rs.getString("OwnerID"));
                vehicle.setPlateNumber(rs.getString("PlateNumber"));
                vehicle.setBrand(rs.getString("Brand"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setManufactureYear(rs.getInt("ManufactureYear"));
                vehicle.setEngineNumber(rs.getString("EngineNumber"));
            }
            return vehicle;
        } catch (Exception e) {
            return null;
        }
    }

    public static Vehicle addVehicle(Vehicle vehicle) {
        DBContext db = DBContext.getInstance();
        int n =0;
        try {
            String sql = """
                        INSERT INTO [dbo].[Vehicles]
                                   ([OwnerID]
                                   ,[PlateNumber]
                                   ,[Brand]
                                   ,[Model]
                                   ,[ManufactureYear]
                                   ,[EngineNumber])
                             VALUES
                                   (?,?,?,?,?,?)
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, vehicle.getOwnerId());
            statement.setString(2, vehicle.getPlateNumber());
            statement.setString(3, vehicle.getBrand());
            statement.setString(4, vehicle.getModel());
            statement.setInt(5, vehicle.getManufactureYear());
            statement.setString(6, vehicle.getEngineNumber());
            n = statement.executeUpdate();

        } catch (Exception e) {
            return null;
        }
        if(n==0) return null;
        else return vehicle;
    }

    public static ArrayList<Vehicle> getAllVehicleByUserID(String userID) {
        DBContext db = DBContext.getInstance();
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try {
            String sql = """
                        select * from Vehicles where OwnerID = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("VehicleID"));
                vehicle.setOwnerId(rs.getString("OwnerID"));
                vehicle.setPlateNumber(rs.getString("PlateNumber"));
                vehicle.setBrand(rs.getString("Brand"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setManufactureYear(rs.getInt("ManufactureYear"));
                vehicle.setEngineNumber(rs.getString("EngineNumber"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            return null;
        }
        return vehicles;
    }
} 
