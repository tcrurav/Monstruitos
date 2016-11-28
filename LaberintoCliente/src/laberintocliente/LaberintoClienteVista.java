/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintocliente;

/**
 *
 * @author Tiburcio
 */
public interface LaberintoClienteVista {    
    // Exclusivo del Cliente
    void activarBotones();
    void desactivarBotones();
    void calibrarVelocidad(int velocidad);
    
    // Común con el Servidor 
    void setPresentador(LaberintoClientePresentador presentador);
    void mostrarJugador(int x, int y, String imagen);
    void open();
    void limpiarCanvas();
    void setDimensiones(int ancho, int alto, int margen);
    void onRepaint();
}
