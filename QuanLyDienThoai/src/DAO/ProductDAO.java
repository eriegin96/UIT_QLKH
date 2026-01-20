package DAO;

import Model.Product;
import Database.ConnectionFactory;
import Util.ErrorHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;
// Data Access Object for Products, Purchase, Stock and Sales
public class ProductDAO {

    Connection conn = null;
    PreparedStatement prepStatement = null;
    PreparedStatement prepStatement2 = null;
    Statement statement = null;
    Statement statement2 = null;
    ResultSet resultSet = null;

    public ProductDAO() {
        try {
            conn = ConnectionFactory.getInstance().getConnection();
            statement = conn.createStatement();
            statement2 = conn.createStatement();
        } catch (Exception ex) {
            ErrorHandler.handleConnectionError(ex);
        }
    }

    public ResultSet getSuppInfo() {
        try {
            String query = "SELECT * FROM suppliers";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public ResultSet getCustInfo() {
        try {
            String query = "SELECT * FROM customers";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public ResultSet getProdStock() {
        try {
            String query = "SELECT * FROM inventory";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public ResultSet getProdInfo() {
        try {
            String query = "SELECT * FROM products";
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public Double getProdCost(String prodCode) {
        Double costPrice = null;
        try {
            // Get cost price from latest purchase for this product
            String query = "SELECT cost_price FROM purchase_info WHERE product_code='" +prodCode+ "' ORDER BY purchase_id DESC LIMIT 1";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                costPrice = resultSet.getDouble("cost_price");
        } catch (Exception e) {
            ErrorHandler.handleError(e);
        }
        return costPrice;
    }

    public Double getProdSell(String prodCode) {
        Double sellPrice = null;
        try {
            // Get sell price from latest sale for this product
            String query = "SELECT sell_price FROM sales_info WHERE product_code='" +prodCode+ "' ORDER BY sales_id DESC LIMIT 1";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                sellPrice = resultSet.getDouble("sell_price");
        } catch (Exception e) {
            ErrorHandler.handleError(e);
        }
        return sellPrice;
    }

    String suppCode;
    public String getSuppCode(String suppName) {
        try {
            String query = "SELECT supplier_code FROM suppliers WHERE full_name='" +suppName+ "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                suppCode = resultSet.getString("supplier_code");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return suppCode;
    }

    String prodCode;
    public String getProdCode(String prodName) {
        try {
            String query = "SELECT product_code FROM products WHERE product_name='" +prodName+ "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                prodCode = resultSet.getString("product_code");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return prodCode;
    }

    String custCode;
    public String getCustCode(String custName) {
        try {
            String query = "SELECT customer_code FROM customers WHERE full_name='" +custName+ "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                custCode = resultSet.getString("customer_code");
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return custCode;
    }

    // Method to check for availability of stock in Inventory
    boolean flag = false;
    public boolean checkStock(String prodCode) {
        try {
            String query = "SELECT * FROM inventory WHERE product_code='" +prodCode+ "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return flag;
    }

    // Methods to add a new product
    public void addProductDAO(Product productDTO) {
        try {
            String query = "SELECT * FROM products WHERE product_name='"
                    + productDTO.getProdName()
                    + "' AND ram='"
                    + productDTO.getRam()
                    + "' AND rom='"
                    + productDTO.getRom()
                    + "' AND brand='"
                    + productDTO.getBrand()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại.");
            else
                addFunction(productDTO);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
    }
    public void addFunction(Product productDTO) {
        try {
            String query = "INSERT INTO products (product_code, product_name, ram, rom, screen_size, brand) VALUES(?,?,?,?,?,?)";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setString(1, productDTO.getProdCode());
            prepStatement.setString(2, productDTO.getProdName());
            prepStatement.setString(3, productDTO.getRam());
            prepStatement.setString(4, productDTO.getRom());
            prepStatement.setString(5, productDTO.getScreenSize());
            prepStatement.setString(6, productDTO.getBrand());

            String query2 = "INSERT INTO inventory (product_code, quantity, cost_price) VALUES(?,?,?)";
            prepStatement2 = conn.prepareStatement(query2);
            prepStatement2.setString(1, productDTO.getProdCode());
            prepStatement2.setInt(2, productDTO.getQuantity());
            prepStatement2.setDouble(3, 0.0);

            prepStatement.executeUpdate();
            prepStatement2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sản phẩm đã được thêm và sẵn sàng để bán.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "thêm", "sản phẩm");
        }
    }

    // Method to add a new purchase transaction
    public void addPurchaseDAO(Product productDTO) {
        try {
            String query = "INSERT INTO purchase_info (supplier_code, product_code, date, quantity, cost_price, total_cost, purchased_by) VALUES(?,?,?,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, productDTO.getSuppCode());
            prepStatement.setString(2, productDTO.getProdCode());
            // Use TIMESTAMP format for database
            prepStatement.setTimestamp(3, java.sql.Timestamp.valueOf(productDTO.getDate()));
            prepStatement.setInt(4, productDTO.getQuantity());
            prepStatement.setDouble(5, productDTO.getCostPrice());
            prepStatement.setDouble(6, productDTO.getTotalCost());
            prepStatement.setString(7, "admin");

            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nhật ký mua hàng đã được thêm.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "thêm", "giao dịch mua hàng");
        }

        String prodCode = productDTO.getProdCode();
        if(checkStock(prodCode)) {
            try {
                String query = "UPDATE inventory SET quantity=quantity+?, cost_price=? WHERE product_code=?";
                prepStatement = conn.prepareStatement(query);
                prepStatement.setInt(1, productDTO.getQuantity());
                prepStatement.setDouble(2, productDTO.getCostPrice());
                prepStatement.setString(3, prodCode);

                prepStatement.executeUpdate();
            } catch (SQLException e) {
                ErrorHandler.handleError(e);
            }
        }
        else if (!checkStock(prodCode)) {
            try {
                String query = "INSERT INTO inventory (product_code, quantity, cost_price) VALUES(?,?,?)";
                prepStatement = (PreparedStatement) conn.prepareStatement(query);
                prepStatement.setString(1, productDTO.getProdCode());
                prepStatement.setInt(2, productDTO.getQuantity());
                prepStatement.setDouble(3, productDTO.getCostPrice());

                prepStatement.executeUpdate();
            } catch (SQLException e) {
                ErrorHandler.handleError(e);
            }
        }
        deleteStock();
    }

    // Method to update existing product details
    public void editProdDAO(Product productDTO) {
        try {
            String query = "UPDATE products SET product_name=?,ram=?,rom=?,screen_size=?,brand=? WHERE product_code=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setString(1, productDTO.getProdName());
            prepStatement.setString(2, productDTO.getRam());
            prepStatement.setString(3, productDTO.getRom());
            prepStatement.setString(4, productDTO.getScreenSize());
            prepStatement.setString(5, productDTO.getBrand());
            prepStatement.setString(6, productDTO.getProdCode());

            String query2 = "UPDATE inventory SET quantity=? WHERE product_code=?";
            prepStatement2 = conn.prepareStatement(query2);
            prepStatement2.setInt(1, productDTO.getQuantity());
            prepStatement2.setString(2, productDTO.getProdCode());

            prepStatement.executeUpdate();
            prepStatement2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Chi tiết sản phẩm đã được cập nhật.");
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "cập nhật", "sản phẩm");
        }
    }

    // Methods to handle updating of stocks in Inventory upon any transaction made
    public void editPurchaseStock(String code, int quantity) {
        try {
            String query = "SELECT * FROM inventory WHERE product_code='" +code+ "'";
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                String query2 = "UPDATE inventory SET quantity=quantity-? WHERE product_code=?";
                prepStatement = conn.prepareStatement(query2);
                prepStatement.setInt(1, quantity);
                prepStatement.setString(2, code);
                prepStatement.executeUpdate();
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
    }
    public void editSoldStock(String code, int quantity) {
        try {
            String query = "SELECT * FROM inventory WHERE product_code='" +code+ "'";
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                String query2 = "UPDATE inventory SET quantity=quantity+? WHERE product_code=?";
                prepStatement = conn.prepareStatement(query2);
                prepStatement.setInt(1, quantity);
                prepStatement.setString(2, code);
                prepStatement.executeUpdate();
            }
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
    }
    public void deleteStock() {
        try {
            String query = "DELETE FROM inventory WHERE product_code NOT IN(SELECT product_code FROM purchase_info)";
            String query2 = "DELETE FROM sales_info WHERE product_code NOT IN(SELECT product_code FROM products)";
            statement.executeUpdate(query);
            statement.executeUpdate(query2);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
    }

    // Method to permanently delete a product from inventory
    public void deleteProductDAO(String code) {
        try {
            String query = "DELETE FROM products WHERE product_code=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, code);

            String query2 = "DELETE FROM inventory WHERE product_code=?";
            prepStatement2 = conn.prepareStatement(query2);
            prepStatement2.setString(1, code);

            prepStatement.executeUpdate();
            prepStatement2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Sản phẩm đã được xóa.");
        } catch (SQLException e){
            ErrorHandler.handleError(e);
        }
        deleteStock();
    }

    public void deletePurchaseDAO(int ID){
        try {
            // First, get the product_code and quantity from the purchase being deleted
            String selectQuery = "SELECT product_code, quantity FROM purchase_info WHERE purchase_id=?";
            prepStatement = conn.prepareStatement(selectQuery);
            prepStatement.setInt(1, ID);
            ResultSet rs = prepStatement.executeQuery();
            
            if (rs.next()) {
                String productCode = rs.getString("product_code");
                int quantity = rs.getInt("quantity");
                
                // Update stock: subtract the purchased quantity
                String updateStockQuery = "UPDATE inventory SET quantity = quantity - ? WHERE product_code = ?";
                prepStatement2 = conn.prepareStatement(updateStockQuery);
                prepStatement2.setInt(1, quantity);
                prepStatement2.setString(2, productCode);
                prepStatement2.executeUpdate();
                
                // Delete the purchase record
                String deleteQuery = "DELETE FROM purchase_info WHERE purchase_id=?";
                prepStatement = conn.prepareStatement(deleteQuery);
                prepStatement.setInt(1, ID);
                prepStatement.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Giao dịch đã được xóa và kho đã được cập nhật.");
            }
        } catch (SQLException e){
            ErrorHandler.handleError(e);
        }
        deleteStock();
    }

    public void deleteSaleDAO(int ID) {
        try {
            // First, get the product_code and quantity from the sale being deleted
            String selectQuery = "SELECT product_code, quantity FROM sales_info WHERE sales_id=?";
            prepStatement = conn.prepareStatement(selectQuery);
            prepStatement.setInt(1, ID);
            ResultSet rs = prepStatement.executeQuery();
            
            if (rs.next()) {
                String productCode = rs.getString("product_code");
                int quantity = rs.getInt("quantity");
                
                // Update stock: add back the sold quantity
                String updateStockQuery = "UPDATE inventory SET quantity = quantity + ? WHERE product_code = ?";
                prepStatement2 = conn.prepareStatement(updateStockQuery);
                prepStatement2.setInt(1, quantity);
                prepStatement2.setString(2, productCode);
                prepStatement2.executeUpdate();
                
                // Delete the sale record
                String deleteQuery = "DELETE FROM sales_info WHERE sales_id=?";
                prepStatement = conn.prepareStatement(deleteQuery);
                prepStatement.setInt(1, ID);
                prepStatement.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Giao dịch đã được xóa và kho đã được cập nhật.");
            }
        } catch (SQLException e){
            ErrorHandler.handleError(e);
        }
        deleteStock();
    }

    // Sales transaction handling
    public void sellProductDAO(Product productDTO, String username) {
        int quantity = 0;
        String prodCode = null;
        try {
            String query = "SELECT * FROM inventory WHERE product_code='" +productDTO.getProdCode()+ "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                prodCode = resultSet.getString("product_code");
                quantity = resultSet.getInt("quantity");
            }
            if (productDTO.getQuantity()>quantity)
                JOptionPane.showMessageDialog(null, "Không đủ hàng trong kho cho sản phẩm này.");
            else if (productDTO.getQuantity()<=0)
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ.");
            else {
                // Update stock using PreparedStatement
                String stockQuery = "UPDATE inventory SET quantity=quantity-? WHERE product_code=?";
                prepStatement = conn.prepareStatement(stockQuery);
                prepStatement.setInt(1, productDTO.getQuantity());
                prepStatement.setString(2, productDTO.getProdCode());
                prepStatement.executeUpdate();
                
                // Insert sales record using PreparedStatement with TIMESTAMP
                String salesQuery = "INSERT INTO sales_info(product_code,customer_code,date,quantity,sell_price,revenue,sold_by) VALUES(?,?,?,?,?,?,?)";
                prepStatement = conn.prepareStatement(salesQuery);
                prepStatement.setString(1, productDTO.getProdCode());
                prepStatement.setString(2, productDTO.getCustCode());
                prepStatement.setTimestamp(3, java.sql.Timestamp.valueOf(productDTO.getDate()));
                prepStatement.setInt(4, productDTO.getQuantity());
                prepStatement.setDouble(5, productDTO.getSellPrice());
                prepStatement.setDouble(6, productDTO.getTotalRevenue());
                prepStatement.setString(7, username);
                prepStatement.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Sản phẩm đã được bán.");
            }
        } catch (SQLException e) {
            ErrorHandler.handleDatabaseError(e, "bán", "sản phẩm");
        }
    }

    // Products data set retrieval for display
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT product_code,product_name,ram,rom,screen_size,brand FROM products ORDER BY pid";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    // Purchase table data set retrieval
    public ResultSet getPurchaseInfo() {
        try {
            String query = "SELECT purchase_id,pi.product_code,product_name,pi.date,quantity,pi.cost_price,total_cost,pi.supplier_code,s.full_name " +
                    "FROM purchase_info pi INNER JOIN products p " +
                    "ON p.product_code=pi.product_code " +
                    "INNER JOIN suppliers s " +
                    "ON pi.supplier_code=s.supplier_code " +
                    "ORDER BY purchase_id;";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    // Stock table data set retrieval
    public ResultSet getInventoryInfo() {
        try {
            String query = "SELECT cs.product_code,p.product_name," +
                    "cs.quantity,cs.cost_price,p.ram,p.rom,p.screen_size,p.brand " +
                    "FROM inventory cs INNER JOIN products p " +
                    "ON cs.product_code=p.product_code;";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    // Sales table data set retrieval
    public ResultSet getSalesInfo() {
        try {
            String query = "SELECT sales_id,si.product_code,product_name," +
                    "si.customer_code,c.full_name," +
                    "si.quantity,revenue,u.name AS Sold_by," +
                    "si.date,si.sell_price " +
                    "FROM sales_info si INNER JOIN products p " +
                    "ON si.product_code=p.product_code " +
                    "INNER JOIN users u " +
                    "ON si.sold_by=u.username " +
                    "INNER JOIN customers c " +
                    "ON si.customer_code=c.customer_code;";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }
    
    // Method to set combo box items for product selection
    public DefaultComboBoxModel<String> setComboItems(ResultSet resultSet) throws SQLException {
        Vector<String> productItems = new Vector<>();
        productItems.add("Chọn sản phẩm");
        while (resultSet.next()){
            String productCode = resultSet.getString("product_code");
            String productName = resultSet.getString("product_name");
            productItems.add(productCode + " - " + productName);
        }
        return new DefaultComboBoxModel<>(productItems);
    }

    // Search method for products
    public ResultSet getProductSearch(String text) {
        try {
            String query = "SELECT product_code,product_name,ram,rom,screen_size,brand FROM products " +
                    "WHERE product_code LIKE '%"+text+"%' OR product_name LIKE '%"+text+"%' OR ram LIKE '%"+text+"%' OR rom LIKE '%"+text+"%' OR screen_size LIKE '%"+text+"%' OR brand LIKE '%"+text+"%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public ResultSet getProdFromCode(String text) {
        try {
            String query = "SELECT product_code,product_name,ram,rom,screen_size,brand FROM products " +
                    "WHERE product_code='" +text+ "' LIMIT 1";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    // Search method for sales
    public ResultSet getSalesSearch(String text) {
        try {
            String query = "SELECT sales_id,si.product_code,product_name," +
                    "si.customer_code,c.full_name," +
                    "si.quantity,revenue,u.name AS Sold_by," +
                    "si.date,si.sell_price " +
                    "FROM sales_info si INNER JOIN products p " +
                    "ON si.product_code=p.product_code " +
                    "INNER JOIN users u " +
                    "ON si.sold_by=u.username " +
                    "INNER JOIN customers c " +
                    "ON si.customer_code=c.customer_code " +
                    "WHERE si.product_code LIKE '%"+text+"%' OR product_name LIKE '%"+text+"%' " +
                    "OR u.name LIKE '%"+text+"%' OR c.full_name LIKE '%"+text+"%' ORDER BY sales_id;";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    // Search method for purchase logs
    public ResultSet getPurchaseSearch(String text) {
        try {
            String query = "SELECT purchase_id,pi.product_code,p.product_name,pi.date,quantity,pi.cost_price,total_cost,pi.supplier_code,s.full_name " +
                    "FROM purchase_info pi INNER JOIN products p ON pi.product_code=p.product_code " +
                    "INNER JOIN suppliers s ON pi.supplier_code=s.supplier_code " +
                    "WHERE purchase_id LIKE '%"+text+"%' OR pi.product_code LIKE '%"+text+"%' OR p.product_name LIKE '%"+text+"%' " +
                    "OR s.full_name LIKE '%"+text+"%' OR pi.supplier_code LIKE '%"+text+"%' " +
                    "OR pi.date LIKE '%"+text+"%' ORDER BY purchase_id";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public ResultSet getProdName(String code) {
        try {
            String query = "SELECT product_name FROM products WHERE product_code='" +code+ "'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return resultSet;
    }

    public String getSuppName(int ID) {
        String name = null;
        try {
            String query = "SELECT full_name FROM suppliers " +
                    "INNER JOIN purchase_info ON suppliers.supplier_code=purchase_info.supplier_code " +
                    "WHERE purchase_id='" +ID+ "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                name = resultSet.getString("full_name");
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return name;
    }

    public String getCustName(int ID) {
        String name = null;
        try {
            String query = "SELECT full_name FROM customers " +
                    "INNER JOIN sales_info ON customers.customer_code=sales_info.customer_code " +
                    "WHERE sales_id='" +ID+ "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                name = resultSet.getString("full_name");
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return name;
    }

    public String getPurchaseDate(int ID) {
        String date = null;
        try {
            String query = "SELECT date FROM purchase_info WHERE purchase_id='" +ID+ "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                date = resultSet.getString("date");
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return date;
    }
    public String getSaleDate(int ID) {
        String date = null;
        try {
            String query = "SELECT date FROM sales_info WHERE sales_id='" +ID+ "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                date = resultSet.getString("date");
        } catch (SQLException e) {
            ErrorHandler.handleError(e);
        }
        return date;
    }


    // Method to display product-related data set in tabular form
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
