package com.example.proyectodi;

public enum Estado {
    ESPERA("EN ESPERA"),INICIADO("INICIADO"),FINALIZADO("FINALIZADO");

    private String estado;

    private Estado(String estado){
        this.estado=estado;
    }

    public String obtenerEstado(){
        return estado;
    }
}
