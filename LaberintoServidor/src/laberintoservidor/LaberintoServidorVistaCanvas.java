/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintoservidor;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author Tiburcio
 */
public class LaberintoServidorVistaCanvas extends Canvas {
    
    LaberintoServidorVista listener;

    public void setListener(LaberintoServidorVista listener) {
        this.listener = listener;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        listener.onRepaint();
    }  
}
