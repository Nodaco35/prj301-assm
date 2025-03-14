package model;

import java.util.Date;

/**
 *
 * @author NC PC
 */
public class Notification {

    private int notificationId;
    private String userId; // code cũ đang khai báo int
    private String message;
    private Date sentDate;// ĐỔI thành Date
    private int isRead; // đổi thành int
    //thêm requestId từ bảng Inse Request
    private  int requestId;
    private  int recordId;
    public Notification() {
    }

    public Notification(int notificationId, String userId, String message, Date sentDate, int isRead, int requestId, int recordId) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.sentDate = sentDate;
        this.isRead = isRead;
        this.requestId = requestId;
        this.recordId = this.recordId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
   
    
    
    
    
    
}
