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

    public static User addUser(User user) {
        DBContext db = DBContext.getInstance();
        int n=0;
        try {
            String sql = """
                         INSERT INTO [dbo].[Users]
                                    ([UserID]
                                    ,[FullName]
                                    ,[Email]
                                    ,[Password]
                                    ,[Role]
                                    ,[Phone]
                                    ,[Address]
                                    )
                              VALUES
                                    (? ,? ,? ,? ,N'Khách hàng' ,?,?)
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getFullName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getAddress());
            n = statement.executeUpdate();
            if(n!=0) return user;
            else return null;
            
        } catch (Exception e) {
            return null;
        }

    }
    
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
    //Them
    public static User getUserByEmail(String email) {
        DBContext db = DBContext.getInstance();
        User user = null;
        try {
            String sql = "select * from Users where Email = ? ";
            Connection conn = db.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
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
