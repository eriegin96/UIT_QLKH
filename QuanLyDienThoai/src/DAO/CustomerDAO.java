package DAO;
import Model.Customer;
import Database.ConnectionFactory;
import Util.ErrorHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;

// Data Access Object for Customers
public class CustomerDAO {
    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;

    public CustomerDAO() {
            // Use the Singleton instance to get the connection
            conn = ConnectionFactory.getInstance().getConnection();
            try {
                statement = conn.createStatement();
            } catch (SQLException e) {
                ErrorHandler.handleConnectionError(e);
            }
    }
    // Methods to add new custoemr
    public void addCustomerDAO(Customer customerDTO) {
        try {
            String query = "SELECT * FROM customers WHERE full_name='"
                    +customerDTO.getFullName()
                    + "' AND location='"
                    +customerDTO.getLocation()
                    + "' AND phone='"
                    +customerDTO.getPhone()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Khách hàng đã tồn tại.");
            else
                addFunction(customerDTO);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
    }
    public void addFunction(Customer customerDTO) {
        try {
            String query = "INSERT INTO customers VALUES(null,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, customerDTO.getCustCode());
            prepStatement.setString(2, customerDTO.getFullName());
            prepStatement.setString(3, customerDTO.getLocation());
            prepStatement.setString(4, customerDTO.getPhone());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Khách hàng đã được thêm thành công.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "thêm", "khách hàng");
        }

    }

    // Method to edit existing customer details
    public  void editCustomerDAO(Customer customerDTO) {
        try {
            String query = "UPDATE customers SET full_name=?,location=?,phone=? WHERE customer_code=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, customerDTO.getFullName());
            prepStatement.setString(2, customerDTO.getLocation());
            prepStatement.setString(3, customerDTO.getPhone());
            prepStatement.setString(4, customerDTO.getCustCode());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Chi tiết khách hàng đã được cập nhật.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "cập nhật", "khách hàng");
        }
    }
    
    // Overloaded method to edit customer details including customer code
    public void editCustomerDAO(Customer customerDTO, String originalCustCode) {
        try {
            String query = "UPDATE customers SET customer_code=?,full_name=?,location=?,phone=? WHERE customer_code=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, customerDTO.getCustCode());
            prepStatement.setString(2, customerDTO.getFullName());
            prepStatement.setString(3, customerDTO.getLocation());
            prepStatement.setString(4, customerDTO.getPhone());
            prepStatement.setString(5, originalCustCode);
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Chi tiết khách hàng đã được cập nhật.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "cập nhật", "khách hàng");
        }
    }

    // Method to delete existing customer
    public void deleteCustomerDAO(String custCode) {
        try {
            String query = "DELETE FROM customers WHERE customer_code='" +custCode+ "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Khách hàng đã được xóa.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "xóa", "khách hàng");
        }
    }

    // Method to retrieve data set to be displayed
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT customer_code,full_name,location,phone FROM customers";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    // Method to retrieve search data
    public ResultSet getCustomerSearch(String text) {
        try {
            String query = "SELECT customer_code,full_name,location,phone FROM customers " +
                    "WHERE customer_code LIKE '%"+text+"%' OR full_name LIKE '%"+text+"%' OR " +
                    "location LIKE '%"+text+"%' OR phone LIKE '%"+text+"%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public ResultSet getCustName(String custCode) {
        try {
            String query = "SELECT * FROM customers WHERE customer_code='" +custCode+ "'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public ResultSet getProdName(String prodCode) {
        try {
            String query = "SELECT product_name,cs.quantity,cs.cost_price FROM products p " +
                    "INNER JOIN inventory cs ON p.product_code=cs.product_code " +
                    "WHERE cs.product_code='" +prodCode+ "'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
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
    
    // Method to populate combo box with customer items
    public DefaultComboBoxModel<String> setComboItems(ResultSet resultSet) throws SQLException {
        Vector<String> customerItems = new Vector<>();
        customerItems.add("Chọn khách hàng");
        while (resultSet.next()){
            customerItems.add(resultSet.getString("customer_code") + " - " + resultSet.getString("full_name"));
        }
        return new DefaultComboBoxModel<>(customerItems);
    }
}
