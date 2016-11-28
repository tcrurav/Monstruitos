/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;

/**
 *
 * @author Tiburcio
 */
public class Jugador implements Serializable{
    private int x;
    private int y;
    private int velocidad = 1; //Velocidad por defecto: 1px por click
    private String imagen;

    public Jugador() {
    }
    
    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Jugador(int x, int y, String imagen) {
        this.x = x;
        this.y = y;
        this.imagen = imagen;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
   
}
