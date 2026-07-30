package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlconnection {
    static final String url = "jdbc:mysql://localhost:3306/library";
    static final String username = "root";
    static final String password = "gayatri@7";

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connection done");
        } catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
}
