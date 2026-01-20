package DAO;

import Database.ConnectionFactory;
import Util.ErrorHandler;

import java.sql.*;

/**
 * Data Access Object for fetching statistics and dashboard data
 */
public class StatisticsDAO {
    
    Connection conn = null;
    PreparedStatement prepStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    public StatisticsDAO() {
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            statement = conn.createStatement();
        } catch (SQLException ex) {
            ErrorHandler.handleError(ex);
        }
    }
    
    /**
     * Get total revenue from all sales
     */
    public double getTotalRevenue() {
        double total = 0.0;
        try {
            String query = "SELECT COALESCE(SUM(revenue), 0) as total FROM sales_info";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                total = resultSet.getDouble("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return total;
    }
    
    /**
     * Get total purchase cost
     */
    public double getTotalPurchaseCost() {
        double total = 0.0;
        try {
            String query = "SELECT COALESCE(SUM(total_cost), 0) as total FROM purchase_info";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                total = resultSet.getDouble("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return total;
    }
    
    /**
     * Get total profit (revenue - cost)
     */
    public double getTotalProfit() {
        return getTotalRevenue() - getTotalPurchaseCost();
    }
    
    /**
     * Get total number of products in inventory
     */
    public int getTotalProducts() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) as total FROM products";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return count;
    }
    
    /**
     * Get total inventory value
     */
    public double getTotalInventoryValue() {
        double total = 0.0;
        try {
            String query = "SELECT COALESCE(SUM(quantity * cost_price), 0) as total FROM inventory";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                total = resultSet.getDouble("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return total;
    }
    
    /**
     * Get total quantity of products in stock
     */
    public int getTotalStock() {
        int total = 0;
        try {
            String query = "SELECT COALESCE(SUM(quantity), 0) as total FROM inventory";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return total;
    }
    
    /**
     * Get number of customers
     */
    public int getTotalCustomers() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) as total FROM customers";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return count;
    }
    
    /**
     * Get number of suppliers
     */
    public int getTotalSuppliers() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) as total FROM suppliers";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return count;
    }
    
    /**
     * Get number of sales transactions
     */
    public int getTotalSales() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) as total FROM sales_info";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return count;
    }
    
    /**
     * Get number of purchase transactions
     */
    public int getTotalPurchases() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) as total FROM purchase_info";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return count;
    }
    
    /**
     * Get number of low stock products (quantity < 20)
     */
    public int getLowStockCount() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) as total FROM inventory WHERE quantity < 20";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return count;
    }
}
