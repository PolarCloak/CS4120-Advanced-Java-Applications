package Repository;

import Repository.DatabaseItems.MenuItem;
import Repository.DatabaseItems.OrderItem;

import java.sql.*;
import java.util.ArrayList;

public class SQLRepository {

    private Statement sqlGet;
    private Connection sqlPost;

    public SQLRepository() throws SQLException, ClassNotFoundException {
        connectToSQL();
    }


    private void connectToSQL() throws ClassNotFoundException, SQLException {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        // Connect to a database
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/restaurant" , "root", "root");
        System.out.println("Database connected");
        // Create a statement
        setSqlGet(connection.createStatement());
        setSqlPost(connection);
    }

    public ResultSet sendGetQuery(String query) throws SQLException {
        ResultSet results = getSqlGet().executeQuery(query);
        return results;
    }

    public boolean sendPostQuery(String query, String[] values) throws SQLException {
        PreparedStatement prep = getSqlPost().prepareStatement(query);
        for(int x = 0; x<values.length; x++){
            prep.setString(x+1,values[x]);
        }
        return prep.execute();
    }

    public ResultSet getAll(String tableName) throws SQLException {
        String query = ("select * from restaurant." + tableName);
        return sendGetQuery(query);
    }


    public ArrayList<MenuItem> getAllMenuItems() throws SQLException {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        ResultSet results = getAll("menu_item");
        while (results.next()){
            String idS = results.getString(1);
            String nameS = results.getString(2);
            String descriptionS = results.getString(3);
            String picture_filenameS = results.getString(4);
            String priceS = results.getString(5);
            String included = results.getString(6);

            MenuItem item = new MenuItem();

            item.setId(Long.parseLong(idS));
            item.setName(nameS);
            item.setDescription(descriptionS);
            item.setPictureFilename(picture_filenameS);
            item.setPrice(Double.parseDouble(priceS));
            item.setIncluded(toBoolean(included));

            menuItems.add(item);
        }
        return menuItems;
    }

    public MenuItem getMenuItem(long id) throws SQLException {
        ResultSet results = getAll("menu_item");
        while (results.next()){
            String idS = results.getString(1);
            if(Long.parseLong(idS) == id){
                String nameS = results.getString(2);
                String descriptionS = results.getString(3);
                String picture_filenameS = results.getString(4);
                String priceS = results.getString(5);
                String included = results.getString(6);

                MenuItem item = new MenuItem();

                item.setId(Long.parseLong(idS));
                item.setName(nameS);
                item.setDescription(descriptionS);
                item.setPictureFilename(picture_filenameS);
                item.setPrice(Double.parseDouble(priceS));
                item.setIncluded(toBoolean(included));

                return item;
            }
        }
        return null;
    }

    public long getMenuItemMaxId() throws SQLException {
        long max = -1;
        for(MenuItem item : getAllMenuItems()){
            if(item.getId() > max){
                max = item.getId();
            }
        }
        return max;
    }

    public ArrayList<OrderItem> getOrderItems(long orderId) throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ResultSet results = getAll("order_item where order_id = " + orderId);
        while (results.next()){
            String idS = results.getString(1);
            String menuItemIdS = results.getString(2);
            String orderIdS = results.getString(3);
            String stateS = results.getString(4);

            OrderItem order = new OrderItem();

            order.setId(Long.parseLong(idS));
            order.setMenuItemId(Long.parseLong(menuItemIdS));
            order.setOrderId(Long.parseLong(orderIdS));
            order.setState(stateS);

            orderItems.add(order);
        }
        return orderItems;
    }
    public long getOrderMaxId() throws SQLException {
        long max = -1;
        for(OrderItem item : getAllOrderItems()){
            if(item.getOrderId() > max){
                max = item.getOrderId();
            }
        }
        return max;
    }

    public void saveOrderItem(OrderItem orderItem) throws SQLException {
        String query = "insert into order_item (menu_item_id, order_id, state) values (?,?,?)";
        String[] args = new String[3];
        args[0] = orderItem.getMenuItemId() + "";
        args[1] = orderItem.getOrderId() + "";
        args[2] = orderItem.getState() + "";
        sendPostQuery(query,args);
    }

    public ArrayList<OrderItem> getAllOrderItems() throws SQLException {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        ResultSet results = getAll("order_item");
        while (results.next()){
            String idS = results.getString(1);
            String menuItemIdS = results.getString(2);
            String orderIdS = results.getString(3);
            String stateS = results.getString(4);

            OrderItem order = new OrderItem();

            order.setId(Long.parseLong(idS));
            order.setMenuItemId(Long.parseLong(menuItemIdS));
            order.setOrderId(Long.parseLong(orderIdS));
            order.setState(stateS);

            orderItems.add(order);
        }
        return orderItems;
    }



    public static boolean toBoolean(String smallInt){
        switch (smallInt){
            case("1"):{
                return true;
            }
            default:{
                return false;
            }
        }
    }


    public Statement getSqlGet() {
        return sqlGet;
    }

    public void setSqlGet(Statement sqlGet) {
        this.sqlGet = sqlGet;
    }

    public Connection getSqlPost() {
        return sqlPost;
    }

    public void setSqlPost(Connection sqlPost) {
        this.sqlPost = sqlPost;
    }
}
