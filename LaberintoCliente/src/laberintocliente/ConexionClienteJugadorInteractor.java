/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintocliente;

import pojos.Laberinto;
import pojos.Operacion;

/**
 *
 * @author Tiburcio
 */
public interface ConexionClienteJugadorInteractor {
    void onLaberintoRecibido(Laberinto laberinto);
    void enviarMovimientoAServidor(Operacion movimiento);
}
