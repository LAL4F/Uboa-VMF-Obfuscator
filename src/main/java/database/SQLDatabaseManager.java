package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseManager {
    private static final String JDBC_URL = "jdbc:mysql://lonelys-site.com:3306/uboadb?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "uboa_user";
    private static final String PASSWORD = "1234";

    public static boolean checkConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            if (connection == null) {
                System.out.println("No se pudo conectar a la base de datos PostgreSQL. Asegúrate de que la URL y las credenciales sean correctas.");
                return false;
            }
            
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos PostgreSQL: Controlador no encontrado.");
        }
    }

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            if (connection == null) {
                System.out.println("No se pudo conectar a la base de datos PostgreSQL. Asegúrate de que la URL y las credenciales sean correctas.");
            }
            
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos PostgreSQL: Controlador no encontrado.");
        }
    }

    public static void disconnect(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}