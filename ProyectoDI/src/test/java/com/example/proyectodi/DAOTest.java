package com.example.proyectodi;

import org.junit.jupiter.api.Test;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {

    @Test
    void testCrearProyecto() {
        // Configuración de la base de datos de prueba (asegúrate de tener una base de datos de prueba)
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String pass = "fernando";

        // Parámetros de prueba
        String nombreProyecto = "ProyectoZ";
        String fechaInicio = "2024-02-22";

        // Instancia de la clase que contiene el método a probar
        DAO dao = new DAO();

        // Ejecutar el método y verificar el resultado
        assertDoesNotThrow(() -> dao.crearProyecto(nombreProyecto, fechaInicio));

        // Verificar que el proyecto se ha creado correctamente consultando la base de datos
        try (Connection conn = DriverManager.getConnection(url, usuario, pass);
             Statement statement = conn.createStatement()) {

            String selectQuery = "SELECT * FROM proyecto WHERE nombreProyecto = '" + nombreProyecto + "'";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            assertTrue(resultSet.next());
            assertEquals(nombreProyecto, resultSet.getString("nombreProyecto"));
            assertEquals(fechaInicio, resultSet.getString("fechaI"));

        } catch (SQLException e) {
            fail("Error al consultar la base de datos: " + e.getMessage());
        }
    }

    @Test
    void testCrearProyecto2() {
        // Configuración de la base de datos de prueba (asegúrate de tener una base de datos de prueba)
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String pass = "fernando";

        // Parámetros de prueba
        String nombreProyecto = "Proyecto 2";
        String fechaInicio = "03";

        // Instancia de la clase que contiene el método a probar
        DAO dao = new DAO();

        // Ejecutar el método y verificar el resultado
        assertDoesNotThrow(() -> dao.crearProyecto(nombreProyecto, fechaInicio));

        // Verificar que el proyecto se ha creado correctamente consultando la base de datos
        try (Connection conn = DriverManager.getConnection(url, usuario, pass);
             Statement statement = conn.createStatement()) {

            String selectQuery = "SELECT * FROM proyecto WHERE nombreProyecto = '" + nombreProyecto + "'";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            assertTrue(resultSet.next());
            assertEquals(nombreProyecto, resultSet.getString("nombreProyecto"));
            assertEquals(fechaInicio, resultSet.getString("fechaI"));

        } catch (SQLException e) {
            fail("Error al consultar la base de datos: " + e.getMessage());
        }
    }

    @Test
    void testCrearProyecto3() {
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String pass = "fernando";

        // Parámetros de prueba
        String nombreProyecto = "proyecto para parametrizar parámetros";
        String fechaInicio = "2024-05-28";

        // Instancia de la clase que contiene el método a probar
        DAO dao = new DAO();

        // Ejecutar el método y verificar el resultado
        assertDoesNotThrow(() -> dao.crearProyecto(nombreProyecto, fechaInicio));

        // Verificar que el proyecto se ha creado correctamente consultando la base de datos
        try (Connection conn = DriverManager.getConnection(url, usuario, pass);
             Statement statement = conn.createStatement()) {

            String selectQuery = "SELECT * FROM proyecto WHERE nombreProyecto = '" + nombreProyecto + "'";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            assertTrue(resultSet.next());
            assertEquals(nombreProyecto, resultSet.getString("nombreProyecto"));
            assertEquals(fechaInicio, resultSet.getString("fechaI"));

        } catch (SQLException e) {
            fail("Error al consultar la base de datos: " + e.getMessage());
        }
    }

    @Test
    void testCrearProyecto4() {
        // Configuración de la base de datos de prueba (asegúrate de tener una base de datos de prueba)
        String url = "jdbc:mysql://localhost:3306/proyecto";
        String usuario = "root";
        String pass = "fernando";

        // Parámetros de prueba
        String nombreProyecto = null;
        String fechaInicio = "2024-05-28";

        // Instancia de la clase que contiene el método a probar
        DAO dao = new DAO();

        // Ejecutar el método y verificar el resultado
        assertDoesNotThrow(() -> dao.crearProyecto(nombreProyecto, fechaInicio));

        // Verificar que el proyecto se ha creado correctamente consultando la base de datos
        try (Connection conn = DriverManager.getConnection(url, usuario, pass);
             Statement statement = conn.createStatement()) {

            String selectQuery = "SELECT * FROM proyecto WHERE nombreProyecto = '" + nombreProyecto + "'";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            assertTrue(resultSet.next());
            assertEquals(nombreProyecto, resultSet.getString("nombreProyecto"));
            assertEquals(fechaInicio, resultSet.getString("fechaI"));

        } catch (SQLException e) {
            fail("Error al consultar la base de datos: " + e.getMessage());
        }
    }
}