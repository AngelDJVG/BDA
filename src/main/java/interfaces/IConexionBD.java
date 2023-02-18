/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author lv1013
 */
public interface IConexionBD {
    
    Connection crearConexion() throws SQLException;
    //Después se va a hacer la implementación
    
    
}
