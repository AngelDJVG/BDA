/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Excepciones.PersistenciaException;
import dominio.Transaccion;
import java.util.List;


/**
 *
 * @author angel
 */
public interface ITransaccionDAO {
    Transaccion consultar(int codigo);
    Transaccion insertar(Transaccion transaccion)throws PersistenciaException;
    List<Transaccion> consultarTodos(/**ConfiguracionPaginado configPaginado*/) throws PersistenciaException;
}
