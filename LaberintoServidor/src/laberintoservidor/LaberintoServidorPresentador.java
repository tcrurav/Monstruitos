/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintoservidor;

import pojos.Laberinto;
import pojos.Operacion;

/**
 *
 * @author Tiburcio
 */
public interface LaberintoServidorPresentador {
    void setVista(LaberintoServidorVista vista);
    void setModelo(Laberinto modelo);
    void runServidor();
    void onMovimientoJugador(Operacion movimiento, int idJugador);
    void mostrarJugadores();
    Laberinto getLaberinto();
}
