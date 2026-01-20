package Database;

import Util.PasswordHasher;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionFactory {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/quanlydienthoai";
    private static ConnectionFactory instance;
    private Properties prop;
    private Connection conn;
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    // Private constructor to prevent external instantiation
    private ConnectionFactory() {
        try {
            // Username and Password saved as configurable properties
            prop = new Properties();
            // Load from classpath instead of file system
            java.io.InputStream is = getClass().getClassLoader().getResourceAsStream("Database/DBCredentials.xml");
            if (is == null) {
                throw new IOException("Không tìm thấy Database/DBCredentials.xml trong classpath");
            }
            prop.loadFromXML(is);
            Class.forName(driver);
            conn = DriverManager.getConnection(url, prop.getProperty("username"), prop.getProperty("password"));
            statement = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace(); // Print the full stack trace for debugging
            throw new RuntimeException("Lỗi khi khởi tạo ConnectionFactory: " + e.getMessage(), e);
        }
    }

    // Global access point to get the instance of the ConnectionFactory
    public static ConnectionFactory getInstance() {
        if (instance == null) {
            synchronized (ConnectionFactory.class) {
                if (instance == null) {
                    instance = new ConnectionFactory();
                }
            }
        }
        return instance;
    }

    // Method to get connection
    public Connection getConnection() {
        return conn;
    }

    // Login verification method with hashed password support
    public boolean checkLogin(String username, String password) {
        String query = "SELECT password FROM users WHERE username=? LIMIT 1";
        try {
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, username);
            resultSet = prepStatement.executeQuery();
            
            if (resultSet.next()) {
                String storedHash = resultSet.getString("password");
                // Verify the password using PasswordHasher
                return PasswordHasher.verifyPassword(password, storedHash);
            }
            return false;
        } catch (Exception ex) {
            throw new RuntimeException("Đăng nhập thất bại", ex);
        }
    }
}
