package dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import model.Notification;
import model.InspectionRecord;
import model.InspectionRequest;
import model.InspectionStation;
import dao.InspectionRequestDAO;
import dao.InspectionStationDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import model.Vehicle;

/**
 *
 * @author Snowy
 */
public class NotificationDAO {

    public static ArrayList<Notification> getAllNotifications() {// in tất cả ra
        ArrayList<Notification> notifications = new ArrayList<>();
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                     SELECT * FROM Notifications
                     """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Notification notification = new Notification(
                        resultSet.getInt("NotificationID"),
                        resultSet.getString("UserID"),
                        resultSet.getString("Message"),
                        resultSet.getTimestamp("SentDate"),
                        resultSet.getInt("IsRead"),
                        resultSet.getInt("RequestID"),
                        resultSet.getInt("RecordID")
                );
                notifications.add(notification);
            }

        } catch (Exception e) {
            return null;
        }
        return notifications;
    }

    // Phương thức lấy thông báo theo userId
    public static ArrayList<Notification> getAllNotificationsByUserId(String userId) {
        ArrayList<Notification> notifications = new ArrayList<>();
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                     SELECT * FROM Notifications WHERE UserID = ?
                         """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);

            statement.setString(1, userId); // Sử dụng setString cho userId kiểu String
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Notification notification = new Notification(
                        resultSet.getInt("NotificationID"),
                        resultSet.getString("UserID"),
                        resultSet.getString("Message"),
                        resultSet.getTimestamp("SentDate"),
                        resultSet.getInt("IsRead"),
                        resultSet.getInt("requestID"),
                        resultSet.getInt("recordID")
                );
                notifications.add(notification);
            }
        } catch (SQLException e) {
            return null;
        }
        return notifications;
    }

    // Phương thức thay đổi trạng thái thông báo đã đọc
    public static int changeIsReadToDone(String userId) {
        DBContext db = DBContext.getInstance();
        ArrayList<Notification> notifications = new ArrayList<>();

        try {
            String sql = """
                      UPDATE Notifications SET IsRead = 1 WHERE UserID = ? and IsRead = 0
                      """; // cập nhật những thông báo chưa đọc
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, userId); // Set the userId parameter
            int rows = statement.executeUpdate();
            return rows; // Trả về số dòng bị ảnh hưởng
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
            return 0; // Trả về 0 nếu có lỗi xảy ra
        }
    }

    // Phương thức thêm thông báo cho người dùng cho các role còn lại <trong tương lai>
    // Sau khi ktr xe có đạt không thì dùng phương thức này để add thông báo 
    public static Notification addNotification(Notification notification) {
        DBContext db = DBContext.getInstance();
        int result = 0;
        try {
            String sql = """
                     INSERT INTO Notifications (NotificationID, UserID, Message, SentDate, IsRead) 
                     VALUES (?, ?, ?, ?, ?)
                         """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, notification.getNotificationId());
            statement.setString(2, notification.getUserId());
            statement.setString(3, notification.getMessage());
            statement.setTimestamp(4,
                    new java.sql.Timestamp(notification.getSentDate().getTime()));
            statement.setInt(5, notification.getIsRead());
            result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (result == 0) {
            return null;
        } else {
            return notification;
        }
    }

    //Phương thức nhận thông tin đơn đang xử lí sau khi khách hàng gửi đơn lên Request
    public static Notification requestSendToNotification(Notification notification) {
        DBContext db = DBContext.getInstance();
        int result = 0;
        try {
            String sql = """
                       Insert into Notification(UserID, RequestID, Message)
                       values (?, SCOPE_IDENTITY(), N'Your inspection request is being processed.')
                       """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, notification.getUserId());
            statement.setInt(2, notification.getRequestId());
            statement.setString(3, notification.getMessage());

        } catch (Exception e) {
            return null;
        }
        if (result != 0) {
            return notification;
        } else {
            return null;
        }

        //
    }

    public static String getStatusByRequestId(InspectionRequest request) {
        DBContext db = DBContext.getInstance();
        String status = null;

        try {
            String sql = """
                     SELECT ir.Status 
                     FROM InspectionRequests ir
                     JOIN Vehicles v ON ir.VehicleID = v.VehicleID
                     WHERE ir.RequestID = ? 
                     """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, request.getRequestId()); // Lấy requestId từ InspectionRequest
          

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                status = rs.getString("Status");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

}

//Phương thức cập nhật 
// Phương thức sửa thông báo cũ <!>
// Nó sẽ lấy thông báo cũ đã tồn tại sau sửa
//    public static Notification updateNotification(Notification notification) {
//        //đây là fix update nếu muốn có
//        DBContext db = DBContext.getInstance();
//        Notification taolatuong = null;
//        try {
//            // Lấy thông báo cũ từ cơ sở dữ liệu
//            String sql = """
//                         SELECT *
//                         FROM Notifications WHERE UserID = ?
//                         """;
//            PreparedStatement selectStatement = db.getConnection().prepareStatement(sql);
//            selectStatement.setString(1, notification.getUserId());
//            ResultSet resultSet = selectStatement.executeQuery();
//            if (resultSet.next()) {
//                // Nếu thông báo tồn tại, lấy thông tin
//                taolatuong = new Notification(
//                        resultSet.getInt("NotificationID"),
//                        resultSet.getString("UserID"),
//                        resultSet.getString("Message"),
//                        resultSet.getTimestamp("SentDate"),
//                        resultSet.getInt("IsRead")
//                );
//                // Cập nhật thông tin mới
//                String update = """
//                UPDATE Notifications 
//                SET UserID = ?, Message = ?, SentDate = ?, IsRead = ?
//                WHERE NotificationID = ?
//                                """;
//                PreparedStatement updateStatement = db.getConnection().prepareStatement(update);
//                updateStatement.setString(1, notification.getUserId());
//                updateStatement.setString(2, notification.getMessage());
//                updateStatement.setTimestamp(3,
//                        new java.sql.Timestamp(notification.
//                                getSentDate().getTime()));
//                updateStatement.setInt(4, notification.getIsRead());
//                updateStatement.setInt(5, notification.getNotificationId());
//
//                int result = updateStatement.executeUpdate(); // Cập nhật
//                // Kiểm tra kết quả
//                if (result > 0) {
//                    return notification; // Trả về thông báo đã cập nhật
//                }
//            } else {
//                System.out.println("anh chịu chú rồi .");
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // In ra lỗi nếu có
//        }
//        return notification;
//    }
// 
//    public static Notification addNotificationFromRequest(String userId) {
//        DBContext db = DBContext.getInstance();
//        int result = 0;
//
//        try {
//            // Truy vấn để lấy thông tin yêu cầu kiểm định
//            String sqlRequest = """
//            SELECT
//                n.UserID,
//                n.Message,
//                n.IsRead,
//                ir.InspectorID,
//                ir.Status,
//                ir.Comments
//            FROM
//                Notifications n
//            JOIN
//                InspectionRequests ir ON n.RequestID = ir.RequestID
//            WHERE
//                n.UserID = ?
//            """;
//
//            PreparedStatement statement = db.getConnection().prepareStatement(sqlRequest);
//            statement.setString(1, get); 
//            ResultSet resultRequest = statement.executeQuery();
//            while (resultRequest.next()) {
//                Notification newNotification = new Notification();
//                newNotification.setUserId(resultRequest.getString("UserID"));
//                newNotification.setMessage(resultRequest.getString("Message"));
//                newNotification.setIsRead(resultRequest.getInt("IsRead"));
//                // Thêm thông báo mới vào CSDL
////                String sqlInsert = """
////                INSERT INTO Notifications (UserID, Message, SentDate, IsRead) 
////                VALUES (?, ?, ?, ?)
////                """;
////
////                PreparedStatement insertStatement
////                        = db.getConnection().
////                                prepareStatement(sqlInsert);
////                insertStatement.setString(1, newNotification.getUserId());
////                insertStatement.setString(2, newNotification.getMessage());
////                insertStatement.setTimestamp(3,
////                        new java.sql.Timestamp(System.currentTimeMillis())); // Ngày giờ hiện tại
////                insertStatement.setInt(4, newNotification.getIsRead());
////
////                result = insertStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return  notification; 
//    }
//}
