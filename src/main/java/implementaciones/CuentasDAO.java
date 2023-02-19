/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import Excepciones.PersistenciaException;
import Utilidades.ConfiguracionPaginado;
import dominio.Cliente;
import dominio.Cuenta;
import interfaces.IConexionBD;
import interfaces.ICuentaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lv1013
 */
public class CuentasDAO implements ICuentaDAO {

    private final IConexionBD GENERADOR_CONEXIONES;
    private static final Logger LOG = Logger.getLogger(CuentasDAO.class.getName());
    /*Recibirlo en el constructor, no crearlo aquí mismo en el constructor,
    de esta manera desde donde se cree el DAO se define a que bd va a apuntar
    */
    public CuentasDAO(IConexionBD generadorConexiones){
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }
    
    @Override
    public Cuenta consultar(String numeroCuenta) {
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("select nombre,fechaApertura,saldo,estado,codigoCliente from cuentas WHERE numerocuenta=?");
        comando.setString(1, numeroCuenta);
        ResultSet resultado = comando.executeQuery();
        Cuenta cuenta = null;
        if(resultado.next()) {
            String nombre = resultado.getString("nombre");
            String fechaApertura = resultado.getString("fechaApertura");
            Float saldo = resultado.getFloat("saldo");
            String estado = resultado.getString("estado");
            Integer codigoCliente = resultado.getInt("codigoCliente");
            cuenta = new Cuenta(codigoCliente, saldo, numeroCuenta, nombre, fechaApertura, estado);
        }
        conexion.close();
        return cuenta;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    @Override
    public Cuenta insertar(Cuenta cuenta) throws PersistenciaException{

        try(
            Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("insert into cuentas(numerocuenta,nombre, "
            + "saldo,estado,codigocliente)"
            + "values(?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            ){
            comando.setString(1, cuenta.getNumeroCuenta());
            comando.setString(2, cuenta.getNombre());
            comando.setFloat(3, cuenta.getSaldo());
            comando.setString(4, cuenta.getEstado());
            comando.setInt(5, cuenta.getIdCliente());
            comando.executeUpdate();
            System.out.println("Cuenta creada");
            ResultSet llaves = comando.getGeneratedKeys();
            if(llaves.next()){
                int posicionLlavePrimaria = 1;
                String s = llaves.getString(posicionLlavePrimaria);

                return cuenta;
            }
            return cuenta;
        }catch(SQLException ex){
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("No fue posible registrar una cuenta");
        }
    }

    @Override
    public Cuenta eliminar(String numeroCuenta) {
        Cuenta cuentaEliminada = null;
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("delete from cuentas where numeroCuenta=?");
        comando.setString(1, numeroCuenta);
        cuentaEliminada = this.consultar(numeroCuenta);
        int numeroCuentasEliminados = comando.executeUpdate();
        
        if(cuentaEliminada !=null && numeroCuentasEliminados>0){
           System.out.println("Cuenta eliminado"); 
        }
        conexion.close();
        return cuentaEliminada;
        }catch(SQLException ex){
            LOG.log(Level.SEVERE, ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Cuenta> consultar(Integer idCliente) throws PersistenciaException {
        List<Cuenta> listaCuentas = new LinkedList<>();
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("select cuentas.numeroCuenta,cuentas.nombre,cuentas.fechaApertura,cuentas.saldo,cuentas.estado,clientes.codigo from cuentas\n" +
                                          "inner join Clientes ON cuentas.codigoCliente = Clientes.codigo\n" +
                                          "having Clientes.codigo = ? ;");
        comando.setInt(1, idCliente);
        ResultSet resultado = comando.executeQuery();
        Cuenta cuenta = null;
        while(resultado.next()) 
        {
            String numeroCuenta = resultado.getString("cuentas.numeroCuenta");
            String nombre = resultado.getString("cuentas.nombre");
            String fechaApertura = resultado.getString("cuentas.fechaApertura");
            Float saldo = resultado.getFloat("cuentas.saldo");
            String estado = resultado.getString("cuentas.estado");
            cuenta = new Cuenta(idCliente, saldo, numeroCuenta, nombre, fechaApertura, estado);
            //cliente = new Cliente(id_cliente, id_direccion, nombre, apellido_paterno, apellido_materno);
            listaCuentas.add(cuenta);
        }
        conexion.close();
        return listaCuentas;
        }catch(SQLException ex){
            LOG.log(Level.SEVERE,ex.getMessage());
            throw new PersistenciaException("No fue posible consultar la lista de clientes.");
        }
    }

    

}
