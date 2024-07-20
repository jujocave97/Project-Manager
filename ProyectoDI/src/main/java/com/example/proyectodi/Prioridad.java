package com.example.proyectodi;

public enum Prioridad {
    ALTA("ALTA"),MEDIA("MEDIA"),BAJA("BAJA"),URGENTE("URGENTE");

    private String prioridad;

    private Prioridad(String prioridad){
        this.prioridad=prioridad;
    }

    public String obtenerPrioridad(){
        return this.prioridad;
    }


}
