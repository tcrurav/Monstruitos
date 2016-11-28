/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintoservidor;

import pojos.Laberinto;

/**
 *
 * @author Tiburcio
 */
public class LaberintoServidorMain {
    static final int NUM_JUGADORES_MAX = 11;
    static final int ANCHO = 500;
    static final int ALTO = 500;
    static final int MARGEN = 20;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LaberintoServidorVistaJFrame laberintoVista = new LaberintoServidorVistaJFrame();
        laberintoVista.setDimensiones(ANCHO, ALTO, MARGEN);
        Laberinto laberinto = new Laberinto(NUM_JUGADORES_MAX, ANCHO, ALTO, MARGEN);
        
        LaberintoServidorPresentadorImpl laberintoPresentador = new LaberintoServidorPresentadorImpl();
        laberintoPresentador.setModelo(laberinto);
        laberintoPresentador.setVista(laberintoVista);
        
        laberintoVista.setPresentador(laberintoPresentador);
        
        laberintoPresentador.runServidor();
        
    }
    
}
