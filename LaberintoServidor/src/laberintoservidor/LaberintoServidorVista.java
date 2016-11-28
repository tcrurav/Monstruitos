/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintoservidor;

/**
 *
 * @author Tiburcio
 */
public interface LaberintoServidorVista {
    void setPresentador(LaberintoServidorPresentador presentador);
    void mostrarJugador(int x, int y, String imagen);
    void open();
    void limpiarCanvas();
    void setDimensiones(int ancho, int alto, int margen);
    void onRepaint();
}
