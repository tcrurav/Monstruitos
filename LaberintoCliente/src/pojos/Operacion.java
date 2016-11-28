/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;

/**
 *
 * @author Tiburcio
 */
public enum Operacion implements Serializable {
    IZQUIERDA("IZQUIERDA"), DERECHA("DERECHA"), ARRIBA("ARRIBA"), ABAJO("ABAJO"), VELOCIDAD("VELOCIDAD");
    
    private final String value;

    private Operacion(String value) {
        this.value = value;
    }

    
}
