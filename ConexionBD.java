
package com.transporte.gestion.global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static Connection connection;

    private static Properties loadProperties() {
        try {
            Properties props = new Properties();
            props.load(ConexionBD.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties"));
            return props;
        } catch (Exception e) {
            throw new RuntimeException("Error: No se pudo cargar db.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {

            Properties props = loadProperties();

            String url      = props.getProperty("db.url");
            String user     = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String driver   = props.getProperty("db.driver");

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Error: Driver PostgreSQL no encontrado.", e);
            }

            connection = DriverManager.getConnection(url, user, password);
        }

        return connection;
    }

    public static void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
