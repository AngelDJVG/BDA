/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Excepciones.PersistenciaException;
import Utilidades.ConfiguracionPaginado;
import dominio.Cliente;
import dominio.Cuenta;
import java.util.List;

/**
 *
 * @author lv1013
 */
public interface ICuentaDAO {
    
    Cuenta consultar(String numeroCuenta);
    Cuenta insertar(Cuenta cuenta)throws PersistenciaException;
    Cuenta eliminar(String numeroCuenta);
    List<Cuenta> consultar(ConfiguracionPaginado configPaginado) throws PersistenciaException;
}
