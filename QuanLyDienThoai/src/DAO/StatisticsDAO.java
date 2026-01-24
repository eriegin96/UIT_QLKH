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
    
    /**
     * Get monthly revenue data for the last 6 months
     * Returns array: [month1, month2, ..., month6, revenue1, revenue2, ..., revenue6]
     */
    public double[] getMonthlyRevenue(int months) {
        double[] data = new double[months];
        try {
            String query = "SELECT " +
                    "DATE_FORMAT(date, '%Y-%m') as month, " +
                    "SUM(revenue) as total " +
                    "FROM sales_info " +
                    "WHERE date >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) " +
                    "GROUP BY DATE_FORMAT(date, '%Y-%m') " +
                    "ORDER BY month";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, months);
            resultSet = prepStatement.executeQuery();
            int i = 0;
            while (resultSet.next() && i < months) {
                data[i] = resultSet.getDouble("total");
                i++;
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return data;
    }
    
    /**
     * Get top selling products
     * Returns ResultSet with columns: product_name, total_quantity, total_revenue
     */
    public ResultSet getTopSellingProducts(int limit) {
        try {
            String query = "SELECT p.product_name, " +
                    "COALESCE(SUM(si.quantity), 0) as total_quantity, " +
                    "COALESCE(SUM(si.revenue), 0) as total_revenue " +
                    "FROM products p " +
                    "LEFT JOIN sales_info si ON p.product_code = si.product_code " +
                    "GROUP BY p.pid, p.product_name " +
                    "ORDER BY total_quantity DESC " +
                    "LIMIT ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, limit);
            return prepStatement.executeQuery();
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
            return null;
        }
    }
    
    /**
     * Get revenue vs cost comparison for last 6 months
     */
    public ResultSet getRevenueVsCost(int months) {
        try {
            String query = "SELECT " +
                    "DATE_FORMAT(s.date, '%Y-%m') as month, " +
                    "SUM(s.revenue) as revenue, " +
                    "0 as cost " +
                    "FROM sales_info s " +
                    "WHERE s.date >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) " +
                    "GROUP BY DATE_FORMAT(s.date, '%Y-%m') " +
                    "ORDER BY month ASC " +
                    "LIMIT ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, months);
            prepStatement.setInt(2, months);
            return prepStatement.executeQuery();
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
            return null;
        }
    }
}
