/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintocliente;

import pojos.Jugador;
import pojos.Laberinto;
import pojos.Operacion;

/**
 *
 * @author Tiburcio
 */
public class LaberintoClientePresentadorImpl extends Thread implements LaberintoClientePresentador {
    private LaberintoClienteVista laberintoVista;
    private Laberinto laberinto;
    private int idJugador;
    private ConexionClienteJugadorInteractorImpl conexion;
    
    @Override
    public void setVista(LaberintoClienteVista laberintoVista) {
        this.laberintoVista = laberintoVista;
    }

//    @Override
//    public void setModelo(Laberinto laberintoModelo) {
//        this.laberinto = laberintoModelo;
//    }   

    @Override
    public void run() {
        conexion = new ConexionClienteJugadorInteractorImpl();
        conexion.createConnection();
        
        laberinto = conexion.recibirLaberintoDelServidor();
        laberintoVista.setDimensiones(laberinto.getAncho(), laberinto.getAlto(), laberinto.getMargen());
        
        idJugador = conexion.recibirIdJugadorDelServidor();
        laberintoVista.open();
        
        laberintoVista.calibrarVelocidad(laberinto.getJugadores().get(idJugador).getVelocidad());
        
        //mostrarJugadores();
        laberintoVista.activarBotones();
        
        while(true){ //Falta determinar condición de finalización
            laberinto = conexion.recibirLaberintoDelServidor();
            mostrarJugadores();
            laberintoVista.activarBotones();
        }
        
        //conexion.closeConnection();
    }
    
    @Override
    public void realizarMovimientoJugador(Operacion movimiento) {
        laberintoVista.desactivarBotones();
        moverJugador(movimiento);
        //mostrarJugadores();
        conexion.enviarMovimientoAServidor(movimiento);
    }
    
    @Override
    public void mostrarJugadores() {
        laberintoVista.limpiarCanvas();

        for (Jugador jugador : laberinto.getJugadores()) {
            laberintoVista.mostrarJugador(jugador.getX(), jugador.getY(), jugador.getImagen());
        }
    }
    
    void moverJugador(Operacion movimiento){
        int velocidad = laberinto.getJugadores().get(idJugador).getVelocidad();
        switch(movimiento.name()){
            case "ARRIBA":
                laberinto.getJugadores().get(idJugador).setY(
                        laberinto.getJugadores().get(idJugador).getY() - velocidad);
                break;
            case "ABAJO":
                laberinto.getJugadores().get(idJugador).setY(
                        laberinto.getJugadores().get(idJugador).getY() + velocidad);
                break;
            case "DERECHA":
                laberinto.getJugadores().get(idJugador).setX(
                        laberinto.getJugadores().get(idJugador).getX() + velocidad);
                break;
            case "IZQUIERDA":
                laberinto.getJugadores().get(idJugador).setX(
                        laberinto.getJugadores().get(idJugador).getX() - velocidad);
                break;
        }
    }

    @Override
    public void cambiarVelocidad(int valor) {
        //laberintoVista.desactivarBotones();
        laberinto.getJugadores().get(idJugador).setVelocidad(valor);
        conexion.enviarMovimientoAServidor(Operacion.VELOCIDAD);
        conexion.enviarVelocidadAServidor(valor);
    }

}
