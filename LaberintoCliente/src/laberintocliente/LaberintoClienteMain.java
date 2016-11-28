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
public class LaberintoClienteMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        LaberintoClienteVistaJFrame laberintoVista = new LaberintoClienteVistaJFrame();
        
        LaberintoClientePresentadorImpl presentador = new LaberintoClientePresentadorImpl();
        presentador.setVista(laberintoVista);
        
        laberintoVista.setPresentador(presentador);
        
        presentador.start();
    }
}
