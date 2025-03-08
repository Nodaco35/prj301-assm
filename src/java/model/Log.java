/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NC PC
 */
public class Log {
    private int logId;
    private int userId;
    private String action;
    private String timestamp;

    public Log() {
    }

    public Log(int logId, int userId, String action, String timestamp) {
        this.logId = logId;
        this.userId = userId;
        this.action = action;
        this.timestamp = timestamp;
    }
    
    
}
