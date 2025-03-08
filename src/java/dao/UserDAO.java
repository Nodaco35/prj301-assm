package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO {

    public static User getUserByUserID(String userID) {
        DBContext db = DBContext.getInstance();
        User user = null;

        try {
            String sql = """
                        select * from Users where UserID = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
            }
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static User getUserByEmailAndPassword(String email, String password) {
        DBContext db = DBContext.getInstance();
        User user = null;

        try {
            String sql = """
                        select * from Users where Email = ? and Password = ?
                        """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
            }
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
