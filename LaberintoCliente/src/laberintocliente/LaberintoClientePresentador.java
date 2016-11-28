/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintocliente;

import pojos.Operacion;

/**
 *
 * @author Tiburcio
 */
public interface LaberintoClientePresentador {
    void setVista(LaberintoClienteVista vista);
//    void setModelo(Laberinto modelo);
    void realizarMovimientoJugador(Operacion movimiento);
    void mostrarJugadores();
    void cambiarVelocidad(int valor);
}
