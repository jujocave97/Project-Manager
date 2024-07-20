package com.example.proyectodi;

public class Tarea {
    private int id;
    private String nombre;
    private String fechaI;
    private String fechaF;
    private Prioridad prioridad;
    private String estado;
    private Proyecto p;

    public Tarea (int id, String nombre, String fechaI, String fechaF, Prioridad prioridad, String estado,Proyecto p){
        this.id=id;
        this.nombre=nombre;
        this.fechaI=fechaI;
        this.fechaF=fechaF;
        this.prioridad=prioridad;
        this.estado=estado;
        this.p=p;
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

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proyecto getP() {
        return p;
    }

    public void setP(Proyecto p) {
        this.p = p;
    }
}
