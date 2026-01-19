package DAO;

import Model.Supplier;
import Database.ConnectionFactory;
import Util.ErrorHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;

// Data Access Object for Suppliers
public class SupplierDAO {

    Connection conn = null;
    Statement statement = null;
    PreparedStatement prepStatement = null;
    ResultSet resultSet = null;

    public SupplierDAO() {
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            statement = conn.createStatement();
        } catch (SQLException e) {
            ErrorHandler.handleConnectionError(e);
        }
    }

    // Methods to add new supplier
    public void addSupplierDAO(Supplier supplierDTO) {
        try {
            String query = "SELECT * FROM suppliers WHERE full_name='"
                    +supplierDTO.getFullName()
                    + "' AND location='"
                    +supplierDTO.getLocation()
                    + "' AND mobile='"
                    +supplierDTO.getPhone()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Nhà cung cấp đã tồn tại.");
            else
                addFunction(supplierDTO);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
    }
    public void addFunction(Supplier supplierDTO) {
        try {
            String query = "INSERT INTO suppliers VALUES(null,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, supplierDTO.getSuppCode());
            prepStatement.setString(2, supplierDTO.getFullName());
            prepStatement.setString(3, supplierDTO.getLocation());
            prepStatement.setString(4, supplierDTO.getPhone());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nhà cung cấp đã được thêm thành công.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "thêm", "nhà cung cấp");
        }
    }

    // Method to edit existing suppleir details
    public void editSupplierDAO(Supplier supplierDTO, String originalSuppCode) {
        try {
            String query = "UPDATE suppliers SET supplier_code=?,full_name=?,location=?,mobile=? WHERE supplier_code=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, supplierDTO.getSuppCode());
            prepStatement.setString(2, supplierDTO.getFullName());
            prepStatement.setString(3, supplierDTO.getLocation());
            prepStatement.setString(4, supplierDTO.getPhone());
            prepStatement.setString(5, originalSuppCode);
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Chi tiết nhà cung cấp đã được cập nhật.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "cập nhật", "nhà cung cấp");
        }
    }

    // Method to delete existing supplier
    public void deleteSupplierDAO(String suppCode) {
        try {
            String query = "DELETE FROM suppliers WHERE supplier_code='" +suppCode+ "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Nhà cung cấp đã được xóa.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "xóa", "nhà cung cấp");
        }
    }

    // Supplier data set retrieval method
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT supplier_code, full_name, location, mobile FROM suppliers";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    // Search method
    public ResultSet getSearchResult(String searchText) {
        try {
            String query = "SELECT supplier_code, full_name, location, mobile FROM suppliers " +
                    "WHERE supplier_code LIKE '%"+searchText+"%' OR location LIKE '%"+searchText+"%' " +
                    "OR full_name LIKE '%"+searchText+"%' OR mobile LIKE '%"+searchText+"%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    // Method to set/update supplier combo box
    public DefaultComboBoxModel<String> setComboItems(ResultSet resultSet) throws SQLException {
        Vector<String> suppNames = new Vector<>();
        while (resultSet.next()){
            suppNames.add(resultSet.getString("full_name"));
        }
        return new DefaultComboBoxModel<>(suppNames);
    }

    // Method to display retrieved data set in tabular form
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
