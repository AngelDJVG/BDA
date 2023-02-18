/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Excepciones.PersistenciaException;
import Utilidades.ConfiguracionPaginado;
import dominio.Direccion;
import java.util.List;

/**
 *
 * @author Zaurus
 */
public interface IDireccionesDAO {
    Direccion consultar(Integer idDireccion);
    Direccion insertar(Direccion direccion)throws PersistenciaException;
    Direccion eliminar(Integer idDireccion);
}
