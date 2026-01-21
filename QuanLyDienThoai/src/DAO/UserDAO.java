package DAO;

import Model.User;
import Database.ConnectionFactory;
import UI.UsersPage;
import Util.PasswordHasher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;
// Data Access Object class for Users

public class UserDAO {
    Connection conn = null;
    PreparedStatement prepStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // Constructor method
    public UserDAO() {
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            statement = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // Methods to add new user
    public void addUserDAO(User userDTO, String userType) {
        try {
            String query = "SELECT * FROM users WHERE name=? AND location=? AND phone=? AND user_type=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, userDTO.getFullName());
            prepStatement.setString(2, userDTO.getLocation());
            prepStatement.setString(3, userDTO.getPhone());
            prepStatement.setString(4, userDTO.getUserType());
            resultSet = prepStatement.executeQuery();
            if(resultSet.next())
                JOptionPane.showMessageDialog(null, "User already exists");
            else
                addFunction(userDTO, userType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void addFunction(User userDTO, String userType) {
        try {
            String username = null;
            String password = null;
            String oldUsername = null;
            String resQuery = "SELECT * FROM users";
            resultSet = statement.executeQuery(resQuery);

            if(!resultSet.next()){
                username = "root";
                password = "root";
            }

            // Hash the password before storing
            String hashedPassword = PasswordHasher.hashPassword(userDTO.getPassword());

            String query = "INSERT INTO users (name,location,phone,username,password,user_type) " +
                    "VALUES(?,?,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, userDTO.getFullName());
            prepStatement.setString(2, userDTO.getLocation());
            prepStatement.setString(3, userDTO.getPhone());
            prepStatement.setString(4, userDTO.getUsername());
            prepStatement.setString(5, hashedPassword);
            prepStatement.setString(6, userDTO.getUserType());
            prepStatement.executeUpdate();

            if("ADMIN".equals(userType))
                JOptionPane.showMessageDialog(null, "Thêm tài khoản quản trị viên thành công.");
            else JOptionPane.showMessageDialog(null, "Thêm tài khoản nhân viên thành công.");

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to edit existing user
    public void editUserDAO(User userDTO) {

        try {
            // Check if password should be updated
            String query;
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                // Update with password
                query = "UPDATE users SET name=?,location=?,phone=?,password=?,user_type=? WHERE username=?";
                prepStatement = conn.prepareStatement(query);
                prepStatement.setString(1, userDTO.getFullName());
                prepStatement.setString(2, userDTO.getLocation());
                prepStatement.setString(3, userDTO.getPhone());
                
                // Hash the password before storing
                String hashedPassword = PasswordHasher.hashPassword(userDTO.getPassword());
                prepStatement.setString(4, hashedPassword);
                prepStatement.setString(5, userDTO.getUserType());
                prepStatement.setString(6, userDTO.getUsername());
            } else {
                // Update without password
                query = "UPDATE users SET name=?,location=?,phone=?,user_type=? WHERE username=?";
                prepStatement = conn.prepareStatement(query);
                prepStatement.setString(1, userDTO.getFullName());
                prepStatement.setString(2, userDTO.getLocation());
                prepStatement.setString(3, userDTO.getPhone());
                prepStatement.setString(4, userDTO.getUserType());
                prepStatement.setString(5, userDTO.getUsername());
            }
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công.");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Method to delete existing user
    public void deleteUserDAO(String username) {
        try {
            String query = "DELETE FROM users WHERE username=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setString(1, username);
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        new UsersPage().loadDataSet();
    }

    // Method to retrieve data set to display in table
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT * FROM users";
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getUserDAO(String username) {
        try {
            String query = "SELECT * FROM users WHERE username=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, username);
            resultSet = prepStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }
    public void getFullName(User userDTO, String username) {
        try {
            String query = "SELECT * FROM users WHERE username=? LIMIT 1";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, username);
            resultSet = prepStatement.executeQuery();
            String fullName = null;
            if(resultSet.next()) fullName = resultSet.getString(2);
            userDTO.setFullName(fullName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet getUserLogsDAO() {
        try {
            String query = "SELECT users.name,userlogs.username,in_time,out_time,location FROM userlogs" +
                    " INNER JOIN users on userlogs.username=users.username";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean verifyPasswordDAO(String username, String password){
        try {
            String query = "SELECT password FROM users WHERE username=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, username);
            resultSet = prepStatement.executeQuery();
            
            if (resultSet.next()) {
                String storedHash = resultSet.getString("password");
                return PasswordHasher.verifyPassword(password, storedHash);
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    // Deprecated: Use verifyPasswordDAO instead
    @Deprecated
    public ResultSet getPassDAO(String username, String password){
        try {
            String query = "SELECT password FROM users WHERE username=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, username);
            resultSet = prepStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public void changePass(String username, String password) {
        try {
            // Hash the password before storing
            String hashedPassword = PasswordHasher.hashPassword(password);
            
            String query = "UPDATE users SET password=? WHERE username=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setString(1, hashedPassword);
            prepStatement.setString(2, username);
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Mật khẩu đã được thay đổi.");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    // Method to display data set in tabular form
    public DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int colCount = metaData.getColumnCount();

        for (int col=1; col <= colCount; col++){
            columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int col=1; col<=colCount; col++) {
                vector.add(resultSet.getObject(col));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

}
