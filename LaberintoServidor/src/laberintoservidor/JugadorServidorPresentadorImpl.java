package laberintoservidor;

import java.net.ServerSocket;
import pojos.Laberinto;
import pojos.Operacion;

/**
 *
 * @author Tiburcio
 */
public class JugadorServidorPresentadorImpl extends Thread {
    private ConexionServidorJugadorInteractor conexion = null;
    private final ServerSocket sk;
    private final LaberintoServidorPresentador listener;
    private final int idJugador;

    public JugadorServidorPresentadorImpl(ServerSocket sk, LaberintoServidorPresentador listener, int idJugador) {
        this.sk = sk;
        this.listener = listener;
        this.idJugador = idJugador;
    }

    @Override
    public void run() {
        conexion = new ConexionServidorJugadorInteractorImpl();
        conexion.aceptarConexion(sk);

        conexion.enviarLaberintoACliente(listener.getLaberinto());
        conexion.enviarIdJugadorACliente(idJugador);
        
        while(true){ //Falta indicar la condición de parada
            Operacion operacion = conexion.recibirMovimientoDelCliente();

            if(operacion.equals(Operacion.VELOCIDAD)){
                int valor = conexion.recibirVelocidadDelCliente();
                listener.getLaberinto().getJugadores().get(idJugador).setVelocidad(valor);
            } else {
                moverJugador(operacion);
                listener.onMovimientoJugador(operacion, idJugador);
            }   
        }
        //conexion.closeConnection();
    }

    public int getIdJugador() {
        return idJugador;
    }
    
    public ConexionServidorJugadorInteractor getConexion() {
        return conexion;
    }
    
    public void enviarLaberintoACliente(Laberinto laberinto){
        if(conexion.getSocket() != null){
            conexion.enviarLaberintoACliente(laberinto);
        }
    }
    
    void moverJugador(Operacion movimiento){
        int velocidad = listener.getLaberinto().getJugadores().get(idJugador).getVelocidad();
        switch(movimiento.name()){
            case "ARRIBA":
                listener.getLaberinto().getJugadores().get(idJugador).setY(
                        listener.getLaberinto().getJugadores().get(idJugador).getY() - velocidad);
                break;
            case "ABAJO":
                listener.getLaberinto().getJugadores().get(idJugador).setY(
                        listener.getLaberinto().getJugadores().get(idJugador).getY() + velocidad);
                break;
            case "DERECHA":
                listener.getLaberinto().getJugadores().get(idJugador).setX(
                        listener.getLaberinto().getJugadores().get(idJugador).getX() + velocidad);
                break;
            case "IZQUIERDA":
                listener.getLaberinto().getJugadores().get(idJugador).setX(
                        listener.getLaberinto().getJugadores().get(idJugador).getX() - velocidad);
                break;
        }
    }
    
}
