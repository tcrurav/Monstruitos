/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintocliente;

import pojos.Operacion;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.Laberinto;

/**
 *
 * @author Tiburcio
 */
public class ConexionClienteJugadorInteractorImpl {
    private Socket socket = null;
    private InputStream is = null;
    private ObjectInputStream ois = null;
    private OutputStream os = null;
    private ObjectOutputStream oos = null;
    private final int PUERTO = 45000;
    private final String HOST = "192.168.1.12";

    public Socket createConnection(){
        try {
            socket = new Socket(HOST, PUERTO);
            createStreams();
            return socket;
        } catch (IOException ex) {
            Logger.getLogger(ConexionClienteJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    private void createStreams(){
        try {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            
            oos.writeObject("inicializar"); //Inicializar porque sino se queda colgado en el paso 2 siguiente
            oos.flush();
            
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            
            ois.readObject(); //inicializado
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexionClienteJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void closeConnection(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexionClienteJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Laberinto recibirLaberintoDelServidor(){
        try {
            Laberinto laberinto = (Laberinto) ois.readObject();
            // el siguiente bucle está porque no se pasan bien los datos del ArrayList no sé porqué
            for(int i = 0; i < laberinto.getJugadores().size(); i++){
                laberinto.getJugadores().get(i).setX((int) ois.readObject());
                laberinto.getJugadores().get(i).setY((int) ois.readObject());
            }
            return laberinto;
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexionClienteJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int recibirIdJugadorDelServidor(){
        try {
            return (int) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexionClienteJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void enviarMovimientoAServidor(Operacion movimiento) {
        try {
            oos.writeObject(movimiento);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConexionClienteJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarVelocidadAServidor(int velocidad) {
        try {
            oos.writeObject(velocidad);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConexionClienteJugadorInteractorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
