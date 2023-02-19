/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Excepciones.PersistenciaException;
import dominio.Transaccion;
import interfaces.IConexionBD;
import interfaces.ITransaccionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angel
 */
public class TransaccionDAO implements ITransaccionDAO{
    private final IConexionBD GENERADOR_CONEXIONES;
    private static final Logger LOG = Logger.getLogger(ClientesDAO.class.getName());

    public TransaccionDAO(IConexionBD GENERADOR_CONEXIONES) {
        this.GENERADOR_CONEXIONES = GENERADOR_CONEXIONES;
    }

    @Override
    public Transaccion consultar(int codigo) {
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("SELECT * FROM TRANSACCIONES WHERE CODIGO=?");
        comando.setInt(1, codigo);
        ResultSet resultado = comando.executeQuery();
        Transaccion transaccion = null;
        if(resultado.next()) {
            String fechaOperacion = resultado.getString("fechaOperacion");
            String descripcion = resultado.getString("descripcion");
            float monto = resultado.getFloat("monto");
            String codigoCuentaOrigen = resultado.getString("codigoCuentaOrigen");
            String codigoCuentaDestino = resultado.getString("codigoCuentaDestino");

            transaccion = new Transaccion(codigo, fechaOperacion, descripcion, monto, codigoCuentaOrigen, codigoCuentaDestino);
        }
        conexion.close();
        return transaccion;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Transaccion insertar(Transaccion transaccion) throws PersistenciaException {
        try(
            Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("INSERT INTO OPERACIONES(DESCRIPCION,"
            + "MONTO,CODIGOCUENTAORIGEN, CODIGOCUENTADESTINO)"
            + "values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ){
            comando.setString(1, transaccion.getDescripcion());
            comando.setFloat  (2, transaccion.getMonto());
            comando.setString(3, transaccion.getCodigoCuentaOrigen());
            comando.setString(4, transaccion.getCodigoCuentaDestino());

            comando.executeUpdate();
            System.out.println("Transacción exitosa");
            ResultSet llaves = comando.getGeneratedKeys();
            if(llaves.next()){
                int posicionLlavePrimaria = 1;
                Integer codigo = llaves.getInt(posicionLlavePrimaria);
                transaccion.setCodigo(codigo);
                return transaccion;
            }
            throw new PersistenciaException("No fue posible registrar un cliente");
        }catch(SQLException ex){
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("No fue posible registrar un cliente");
        }
    }

    @Override
    public List<Transaccion> consultarTodos() throws PersistenciaException {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
    }
    
    
    
    
}
