/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintoservidor;

import java.net.ServerSocket;
import java.net.Socket;
import pojos.Laberinto;
import pojos.Operacion;

/**
 *
 * @author Tiburcio
 */
public interface ConexionServidorJugadorInteractor {
    void enviarLaberintoACliente(Laberinto laberinto);
    void aceptarConexion(ServerSocket sk);
    Operacion recibirMovimientoDelCliente();
    void enviarIdJugadorACliente(int idJugador);
    int recibirVelocidadDelCliente();
    Socket getSocket();
}
