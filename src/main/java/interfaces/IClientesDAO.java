/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Excepciones.PersistenciaException;
import Utilidades.ConfiguracionPaginado;
import dominio.Cliente;
import java.util.List;

/**
 *
 * @author Ángel Valenzuela, Luis Ayón
 */
public interface IClientesDAO {
    
    Cliente consultar(Integer idCliente);
    Cliente insertar(Cliente cliente)throws PersistenciaException;
    Cliente eliminar(Integer idCliente);
    List<Cliente> consultar(ConfiguracionPaginado configPaginado) throws PersistenciaException;
}
