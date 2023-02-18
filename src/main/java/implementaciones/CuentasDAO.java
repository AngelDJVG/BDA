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
                conexion.prepareStatement("select nombre, apellidopaterno, apellidomaterno,codigodireccion,fechaNacimiento,contrasena from cuentas WHERE numerocuenta=?");
        comando.setString(1, numeroCuenta);
        ResultSet resultado = comando.executeQuery();
        Cuenta cuenta = null;
        if(resultado.next()) {
            String nombre = resultado.getString("nombre");
            String apellido_paterno = resultado.getString("apellidopaterno");
            String apellido_materno = resultado.getString("apellidomaterno");
            String contrasena = resultado.getString("contrasena");
            String fechaNacimiento = resultado.getString("fechanacimiento");
            Integer id_direccion = resultado.getInt("codigodireccion");
            cuenta = new Cuenta();
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
            + "fechaapertura,saldo,estado,codigocliente)"
            + "values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ){
            comando.setString(1, cuenta.getNumeroCuenta());
            comando.setString(2, cuenta.getNombre());
            comando.setString(3, cuenta.getFechaApertura());
            comando.setFloat(4, cuenta.getSaldo());
            comando.setString(5, cuenta.getEstado());
            comando.setInt(6, cuenta.getIdCliente());
            comando.executeUpdate();
            System.out.println("Cuenta creada");
            ResultSet llaves = comando.getGeneratedKeys();
            if(llaves.next()){
                int posicionLlavePrimaria = 1;
                Integer id = llaves.getInt(posicionLlavePrimaria);

                return cuenta;
            }
            throw new PersistenciaException("No fue posible registrar una cuenta");
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
    public List<Cuenta> consultar(ConfiguracionPaginado configPaginado) throws PersistenciaException {
        List<Cuenta> listaCuentas = new LinkedList<>();
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("select cuentas.numeroCuenta,cuentas.nombre,cuentas.fechaApertura,cuentas.saldo,cuentas.estado,clientes.codigo from cuentas\n" +
                                          "inner join Clientes ON cuentas.codigoCliente = Clientes.codigo\n" +
                                          "having Clientes.codigo = ? limit ? offset ?;");
        comando.setInt(1, configPaginado.getElementosPorPagina());
        comando.setInt(2, configPaginado.getElementosSaltar());
        
        ResultSet resultado = comando.executeQuery();
        Cuenta cliente = null;
        while(resultado.next()) 
        {
            Integer id_cliente = resultado.getInt("id_cliente");
            String nombre = resultado.getString("nombre");
            String apellido_paterno = resultado.getString("apellido_paterno");
            String apellido_materno = resultado.getString("apellido_materno");
            Integer id_direccion = resultado.getInt("id_direccion");
            //cliente = new Cliente(id_cliente, id_direccion, nombre, apellido_paterno, apellido_materno);
            listaCuentas.add(cliente);
        }
        conexion.close();
        return listaCuentas;
        }catch(SQLException ex){
            LOG.log(Level.SEVERE,ex.getMessage());
            throw new PersistenciaException("No fue posible consultar la lista de clientes.");
        }
    }

    

}
