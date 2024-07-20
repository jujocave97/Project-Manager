package com.example.proyectodi;

import java.sql.*;
import java.util.*;

public class DAO {
    static String driver = "com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/proyecto";
    String usuario="root";
    String pass="fernando";

    public void crearProyecto(String nombre, String fechaI){
        try(Connection conn = DriverManager.getConnection(url, usuario, pass)){
            String insert="INSERT INTO proyecto(nombreProyecto, fechaI) VALUES (?,?)";

            PreparedStatement ps= conn.prepareStatement(insert);
            ps.setString(1,nombre);
            ps.setString(2,fechaI);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearTarea(String nombreTarea, String fechaI, String fechaF, Prioridad p, String e, int id){
        try(Connection conn = DriverManager.getConnection(url, usuario, pass)){
            String insert="INSERT INTO tarea(nombretare, fechaI,fechaF, prioridad,estado,idProyecto) VALUES (?,?,?,?,?,?)";

            PreparedStatement ps= conn.prepareStatement(insert);
            ps.setString(1,nombreTarea);
            ps.setString(2,fechaI);
            ps.setString(3,fechaF);
            ps.setString(4,p.toString());
            ps.setString(5,e);
            ps.setInt(6,id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Tarea> obtenerTareas(Proyecto p){
        List<Tarea> lt= new LinkedList<>();

        try(Connection conn = DriverManager.getConnection(url, usuario, pass)){
            String query="SELECT * FROM tarea WHERE idProyecto= ?";
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1,p.getId());

            ResultSet rs= ps.executeQuery();

            while(rs.next()){
                int idTarea= rs.getInt(1);
                String nombreTarea= rs.getString(2);
                String fechaI= rs.getString(3);
                String fechaF= rs.getString(4);
                String prioridad= rs.getString(5).toUpperCase();
                String estado= rs.getString(6).toUpperCase();
                int proyecto= rs.getInt(7);

                lt.add(new Tarea(idTarea,nombreTarea,fechaI,fechaF,Prioridad.valueOf(prioridad),estado,p));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lt;
    }

    public List<Proyecto> obtenerProyectos(){
        List<Proyecto> lt= new LinkedList<>();

        try(Connection conn = DriverManager.getConnection(url, usuario, pass)){
            String query="SELECT * FROM proyecto";
            PreparedStatement ps= conn.prepareStatement(query);
            ResultSet rs= ps.executeQuery();

            while(rs.next()){
                int idProyecto= rs.getInt(1);
                String nombreProyecto= rs.getString(2);
                String fechaI= rs.getString(3);
                String fechaF= rs.getString(4);

                lt.add(new Proyecto(idProyecto,nombreProyecto,fechaI,fechaF));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lt;
    }

    public int obtenerUnProyecto(String nombre){
        int id=0;
        try(Connection conn= DriverManager.getConnection(url,usuario,pass)){
            String query="SELECT idProyecto, nombreProyecto, fechaI, fechaF FROM proyecto WHERE nombreProyecto=?";

            PreparedStatement ps= conn.prepareStatement(query);
            ps.setString(1,nombre);
            ResultSet rs= ps.executeQuery();

            if(rs.next()){
                id= rs.getInt(1);
            }else {
                id=0;
            }


        } catch (SQLException e) {
            System.out.println("No se ha encontrado el proyecto");;
        }
        return id;
    }

    public Proyecto obtenerUnProyectoAPartirDelID(int id){
        try(Connection conn= DriverManager.getConnection(url,usuario,pass)){
            String query= "SELECT * FROM proyecto WHERE idProyecto=?";
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();

            String nombre="";
            String fechaI="";
            String fechaF="";

            if(rs.next()) {


                nombre = rs.getString(2);
                fechaI = rs.getString(3);
                if (rs.getString(4) == null) {
                    fechaF = "--";
                } else {
                    fechaF = rs.getString(4);
                }
            }

            Proyecto p= new Proyecto(id,nombre,fechaI,fechaF);

            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarTarea(Tarea t, String nombre, String fechaF, String prio, String estado){
        try(Connection conn= DriverManager.getConnection(url,usuario,pass)) {
            String update="UPDATE tarea SET nombreTare=?, fechaF=?, prioridad=?, estado=? WHERE idTarea=?";
            PreparedStatement ps= conn.prepareStatement(update);
            ps.setString(1,nombre);
            ps.setString(2,fechaF);
            ps.setString(3,prio);
            ps.setString(4,estado);
            ps.setInt(5,t.getId());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarTarea(Tarea t){
        try(Connection conn= DriverManager.getConnection(url,usuario,pass)){
            String delete="DELETE FROM tarea WHERE idTarea=?";
            PreparedStatement ps= conn.prepareStatement(delete);
            ps.setInt(1,t.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String actualizarFechaFinalProyecto(String nombre){
        int id= obtenerUnProyecto(nombre);
        String select="SELECT fechaF FROM tarea WHERE idProyecto=?";
        String fecha="";
        try(Connection conn= DriverManager.getConnection(url,usuario,pass)){
            PreparedStatement ps= conn.prepareStatement(select);
            ps.setInt(1,id);

            ResultSet rs= ps.executeQuery();

            List<String> fechas= new LinkedList<>();
            while (rs.next()){
                fechas.add(rs.getString(1));
            }
            Collections.sort(fechas, Comparator.reverseOrder());
            if(fechas.get(0)==null){
                fecha="2050-01-01";
            }else{
                fecha=fechas.get(0);
            }

            String update="UPDATE proyecto SET fechaF=? WHERE idProyecto=?";

            PreparedStatement psUpdate=conn.prepareStatement(update);
            psUpdate.setString(1,fecha);
            psUpdate.setInt(2,id);

            psUpdate.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ya está actualizado");
        }
        return fecha;
    }


}
