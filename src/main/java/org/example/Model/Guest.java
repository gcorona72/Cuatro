package org.example.Model;

public class Guest {
    private int id;
    private String nombre;
    private boolean acompanante;

    public Guest() {}

    public Guest(int id, String nombre, boolean acompanante) {
        this.id = id;
        this.nombre = nombre;
        this.acompanante = acompanante;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isAcompanante() { return acompanante; }
    public void setAcompanante(boolean acompanante) { this.acompanante = acompanante; }
}
