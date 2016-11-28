/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintoservidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Jugador;
import pojos.Laberinto;
import pojos.Operacion;

/**
 *
 * @author Tiburcio
 */
public class ConexionServidorJugadorInteractorImpl extends Thread implements ConexionServidorJugadorInteractor {
    private Socket socket;

    @Override
    public Socket getSocket() {
        return socket;
    }
    private InputStream is = null;
    private ObjectInputStream ois = null;
    private OutputStream os = null;
    private ObjectOutputStream oos = null;

    @Override
    public void aceptarConexion(ServerSocket sk){
        try {
            socket = sk.accept();
            createStreams();
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidorJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createStreams() {
        try {         
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            
            oos.writeObject("inicializar"); //Inicializar porque sino se queda colgado en el paso 2 siguiente
            oos.flush();
            
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            
            ois.readObject(); //inicializacion
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexionServidorJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    void closeConnection(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidorJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void enviarLaberintoACliente(Laberinto laberinto) {
        try {
            oos.writeObject(laberinto);
            oos.flush();
            
            // el siguiente bucle está porque no se pasan bien los datos del ArrayList no sé porqué
            for(Jugador jugador : laberinto.getJugadores()){
                oos.writeObject(jugador.getX());
                oos.flush();
                oos.writeObject(jugador.getY());
                oos.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(ConexionServidorJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void enviarIdJugadorACliente(int idJugador) {
        try {
            oos.writeObject(idJugador);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidorJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Operacion recibirMovimientoDelCliente(){
        try {
            Operacion movimiento = (Operacion) ois.readObject();
            return movimiento;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexionServidorJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public int recibirVelocidadDelCliente(){
        try {
            int velocidad = (int) ois.readObject();
            return velocidad;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexionServidorJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
