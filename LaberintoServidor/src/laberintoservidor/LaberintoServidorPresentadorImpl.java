/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintoservidor;

import pojos.Laberinto;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Jugador;
import pojos.Operacion;

/**
 *
 * @author Tiburcio
 */
public class LaberintoServidorPresentadorImpl implements LaberintoServidorPresentador {
    private final ArrayList<JugadorServidorPresentadorImpl> jugadoresServidorPresentadorImpl = new ArrayList<>();
    private ServerSocket sk;
    private final int PUERTO = 45000;
    private LaberintoServidorVista laberintoVista;
    private Laberinto laberinto;

    @Override
    public Laberinto getLaberinto() {
        return laberinto;
    }
    
    @Override
    public void setVista(LaberintoServidorVista laberintoVista) {
        this.laberintoVista = laberintoVista;
    }

    @Override
    public void setModelo(Laberinto laberinto) {
        this.laberinto = laberinto;
    }   

    @Override
    public void runServidor() {
        
        laberintoVista.open();
        
        try {
            sk = new ServerSocket(PUERTO);

            for(int i = 0; i < laberinto.getNumJugadoresMax(); i++){
                
                Jugador jugador = new Jugador(
                        new Random().nextInt(laberinto.getAncho()),
                        new Random().nextInt(laberinto.getAlto()),
                        "jugador"+i+".png");
                laberinto.getJugadores().add(jugador);
                JugadorServidorPresentadorImpl jugadorServidorPresentadorImpl = 
                        new JugadorServidorPresentadorImpl(sk, this, i);
                jugadoresServidorPresentadorImpl.add(jugadorServidorPresentadorImpl);
                
                jugadorServidorPresentadorImpl.start();

                laberintoVista.mostrarJugador(jugador.getX(), jugador.getY(), jugador.getImagen());
            }            
        } catch (IOException ex) {
            Logger.getLogger(LaberintoServidorPresentadorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void onMovimientoJugador(Operacion movimiento, int idJugador) {
       
        mostrarJugadores();
        
        // Actualizar todos los clientes
        for (JugadorServidorPresentadorImpl j: jugadoresServidorPresentadorImpl) {
            j.enviarLaberintoACliente(laberinto);
        }

    }

    @Override
    public void mostrarJugadores() {
        laberintoVista.limpiarCanvas();
        for (Jugador jugador : laberinto.getJugadores()) {
            laberintoVista.mostrarJugador(jugador.getX(), jugador.getY(), jugador.getImagen());
        }
    }
    
    

}
