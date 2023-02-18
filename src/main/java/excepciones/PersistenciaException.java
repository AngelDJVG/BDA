/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author lv1013
 */
public class PersistenciaException extends Exception{

    public PersistenciaException() {
    }

    public PersistenciaException(String string) {
        super(string);
    }

    public PersistenciaException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public PersistenciaException(Throwable thrwbl) {
        super(thrwbl);
    }

    public PersistenciaException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
