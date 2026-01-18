package Util;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Global Error Handler Utility
 * Provides centralized error handling and user-friendly error messages in Vietnamese
 */
public class ErrorHandler {
    
    /**
     * Show error dialog with custom message
     * @param e The exception that occurred
     * @param customMessage Custom message to display (can be null for default)
     */
    public static void handleError(Exception e, String customMessage) {
        String title = "Lỗi";
        String message;
        
        if (customMessage != null && !customMessage.isEmpty()) {
            message = customMessage + "\n\nChi tiết: " + e.getMessage();
        } else {
            // Default messages based on exception type
            if (e instanceof SQLException) {
                message = getDefaultSQLErrorMessage((SQLException) e);
            } else {
                message = "Đã xảy ra lỗi: " + e.getMessage();
            }
        }
        
        // Show error dialog
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title, 
            JOptionPane.ERROR_MESSAGE
        );
        
        // Print stack trace for debugging
        e.printStackTrace();
    }
    
    /**
     * Show error dialog with default message based on exception type
     * @param e The exception that occurred
     */
    public static void handleError(Exception e) {
        handleError(e, null);
    }
    
    /**
     * Get default SQL error message in Vietnamese
     * @param e SQLException
     * @return User-friendly error message
     */
    private static String getDefaultSQLErrorMessage(SQLException e) {
        String errorCode = String.valueOf(e.getErrorCode());
        String sqlState = e.getSQLState();
        
        // Common SQL error codes
        if (sqlState != null) {
            switch (sqlState) {
                case "23000": // Integrity constraint violation
                    if (e.getMessage().contains("Duplicate entry")) {
                        return "Dữ liệu bị trùng lặp. Mã này đã tồn tại trong hệ thống.";
                    } else if (e.getMessage().contains("foreign key constraint")) {
                        return "Không thể xóa dữ liệu này vì có dữ liệu liên quan khác đang sử dụng.";
                    }
                    return "Vi phạm ràng buộc dữ liệu: " + e.getMessage();
                    
                case "08S01": // Communication link failure
                case "08003": // Connection does not exist
                case "08006": // Connection failure
                    return "Mất kết nối với cơ sở dữ liệu. Vui lòng kiểm tra kết nối mạng.";
                    
                case "42000": // Syntax error or access violation
                    return "Lỗi cú pháp SQL hoặc quyền truy cập bị từ chối.";
                    
                case "42S02": // Table or view not found
                    return "Không tìm thấy bảng dữ liệu. Vui lòng kiểm tra cơ sở dữ liệu.";
                    
                case "22001": // String data right truncation
                    return "Dữ liệu quá dài. Vui lòng nhập ít ký tự hơn.";
                    
                case "22003": // Numeric value out of range
                    return "Giá trị số nằm ngoài phạm vi cho phép.";
                    
                case "HY000": // General error
                    if (e.getMessage().contains("Lock wait timeout")) {
                        return "Thao tác bị timeout. Vui lòng thử lại.";
                    }
                    return "Lỗi cơ sở dữ liệu: " + e.getMessage();
            }
        }
        
        // Default message
        return "Lỗi cơ sở dữ liệu: " + e.getMessage();
    }
    
    /**
     * Show connection error specifically
     * @param e The exception
     */
    public static void handleConnectionError(Exception e) {
        handleError(e, "Không thể kết nối đến cơ sở dữ liệu");
    }
    
    /**
     * Show database operation error
     * @param e The exception
     * @param operation The operation being performed (e.g., "thêm", "sửa", "xóa")
     * @param entity The entity being operated on (e.g., "khách hàng", "nhà cung cấp")
     */
    public static void handleDatabaseError(Exception e, String operation, String entity) {
        String message = String.format("Lỗi khi %s %s", operation, entity);
        handleError(e, message);
    }
}
