/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import interfaces.IConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ángel, Luis Pablo
 */
public class ConexionBD implements IConexionBD{
    
    /*También a las interfaces se les dice contratos ya que al implementar una interfaz
    se está obligado en implementar todos sus métodos*/
    
    private final String USUARIO, PASSWORD, CADENA_CONEXION;

    public ConexionBD(String cadena_Conexion, String usuario, String password) {
        this.CADENA_CONEXION = cadena_Conexion;
        this.USUARIO = usuario;
        this.PASSWORD = password;
    }
    
    @Override
    public Connection crearConexion() throws SQLException{
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
        return conexion;
    }
    
}
