/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Tiburcio
 */
public class Laberinto implements Serializable {
    private int numJugadoresMax;
    private ArrayList<Jugador> jugadores;
    private int ancho;
    private int alto;
    private int margen;

    public Laberinto() {
    }
    
    public int getMargen() {
        return margen;
    }

    public void setMargen(int margen) {
        this.margen = margen;
    }

    public Laberinto(int numJugadoresMax, int ancho, int alto, int margen) {
        this.jugadores = new ArrayList<>();
        this.numJugadoresMax = numJugadoresMax;
        this.ancho = ancho;
        this.alto = alto;
        this.margen = margen;
    }
    
    public int getNumJugadoresMax() {
        return numJugadoresMax;
    }

    public void setNumJugadoresMax(int numJugadoresMax) {
        this.numJugadoresMax = numJugadoresMax;
    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
  
    
}
