/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementaciones;

import Excepciones.PersistenciaException;
import Utilidades.ConfiguracionPaginado;
import dominio.Cliente;
import interfaces.IClientesDAO;
import interfaces.IConexionBD;
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
public class ClientesDAO implements IClientesDAO {

    private final IConexionBD GENERADOR_CONEXIONES;
    private static final Logger LOG = Logger.getLogger(ClientesDAO.class.getName());
    /*Recibirlo en el constructor, no crearlo aquí mismo en el constructor,
    de esta manera desde donde se cree el DAO se define a que bd va a apuntar
    */
    public ClientesDAO(IConexionBD generadorConexiones){
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }
    
    @Override
    public Cliente consultar(Integer idCliente) {
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("select nombre, apellidopaterno, apellidomaterno,codigodireccion,fechaNacimiento,contrasena from clientes WHERE codigo=?");
        comando.setInt(1, idCliente);
        ResultSet resultado = comando.executeQuery();
        Cliente cliente = null;
        if(resultado.next()) {
            String nombre = resultado.getString("nombre");
            String apellido_paterno = resultado.getString("apellidopaterno");
            String apellido_materno = resultado.getString("apellidomaterno");
            String contrasena = resultado.getString("contrasena");
            String fechaNacimiento = resultado.getString("fechanacimiento");
            Integer id_direccion = resultado.getInt("codigodireccion");
            cliente = new Cliente(idCliente, id_direccion, nombre, apellido_paterno, apellido_materno,contrasena,fechaNacimiento);
        }
        conexion.close();
        return cliente;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    @Override
    public Cliente insertar(Cliente cliente) throws PersistenciaException{
        /*De esta forma en vez de cerrar las conexiones en un finally, las conexiones
        se cierran en automatico
        */
        try(
            Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
            PreparedStatement comando = conexion.prepareStatement("insert into clientes(nombre, "
            + "apellidopaterno,apellidomaterno, codigodireccion,contrasena,fechaNacimiento)"
            + "values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ){
            comando.setString(1, cliente.getNombre());
            comando.setString(2, cliente.getApellidoPaterno());
            comando.setString(3, cliente.getApellidoMaterno());
            comando.setInt(4, cliente.getIdDireccion());
            comando.setString(5, cliente.getContrasena());
            comando.setString(6, cliente.getFechaNacimiento());
            comando.executeUpdate();
            System.out.println("Cliente creado");
            ResultSet llaves = comando.getGeneratedKeys();
            if(llaves.next()){
                int posicionLlavePrimaria = 1;
                Integer id = llaves.getInt(posicionLlavePrimaria);
                cliente.setId(id);
                return cliente;
            }
            throw new PersistenciaException("No fue posible registrar un cliente");
        }catch(SQLException ex){
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("No fue posible registrar un cliente");
        }
    }

    @Override
    public Cliente eliminar(Integer idCliente) {
        Cliente clienteEliminado = null;
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("delete from clientes where id=?");
        comando.setInt(1, idCliente);
        clienteEliminado = this.consultar(idCliente);
        int numeroClientesEliminados = comando.executeUpdate();
        
        if(clienteEliminado !=null && numeroClientesEliminados>0){
           System.out.println("Cliente eliminado"); 
        }
        conexion.close();
        return clienteEliminado;
        }catch(SQLException ex){
            LOG.log(Level.SEVERE, ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> consultar(ConfiguracionPaginado configPaginado) throws PersistenciaException {
        List<Cliente> listaClientes = new LinkedList<>();
        try{
        Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
        PreparedStatement comando = 
                conexion.prepareStatement("select id_cliente,nombre, apellido_paterno, apellido_materno,id_direccion from clientes limit ? offset ?;");
        comando.setInt(1, configPaginado.getElementosPorPagina());
        comando.setInt(2, configPaginado.getElementosSaltar());
        
        ResultSet resultado = comando.executeQuery();
        Cliente cliente = null;
        while(resultado.next()) 
        {
            Integer id_cliente = resultado.getInt("id_cliente");
            String nombre = resultado.getString("nombre");
            String apellido_paterno = resultado.getString("apellido_paterno");
            String apellido_materno = resultado.getString("apellido_materno");
            Integer id_direccion = resultado.getInt("id_direccion");
            //cliente = new Cliente(id_cliente, id_direccion, nombre, apellido_paterno, apellido_materno);
            listaClientes.add(cliente);
        }
        conexion.close();
        return listaClientes;
        }catch(SQLException ex){
            LOG.log(Level.SEVERE,ex.getMessage());
            throw new PersistenciaException("No fue posible consultar la lista de clientes.");
        }
    }

    

}
