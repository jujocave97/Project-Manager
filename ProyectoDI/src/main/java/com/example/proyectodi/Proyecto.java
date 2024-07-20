package com.example.proyectodi;

public class Proyecto {
    private int id;
    private String nombre;
    private String fechaI;
    private String fechaF;

    public Proyecto(int id, String nombre, String fechaI) {
        this.id = id;
        this.nombre = nombre;
        this.fechaI = fechaI;
    }

    public Proyecto(int id, String nombre, String fechaI, String fechaF) {
        this.id = id;
        this.nombre = nombre;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }
}
